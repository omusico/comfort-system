package comfort.persistence.hibernate;

import comfort.exceptions.ProgrammerError;
import comfort.persistence.front.*;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;

import javax.ejb.*;
import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Реализация DAO для Jpa.
 */
@Stateless
@Name("dao")
//@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class JpaDAO implements DAO {
    /**
     * em - инъекция для JPA, проставляющая ссылку на EntityManager
     */

//    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    @In
    protected EntityManager em;
    private Map<Class<? extends IEntity>, Class> classesMap;
    private EntityTransaction et;

    public Map<Class<? extends IEntity>, Class> getClassesMap() {
        if (classesMap == null) {
            classesMap = new HashMap<Class<? extends IEntity>, Class>();
            classesMap.put(IComfortClass.class, ComfortClass.class);
            classesMap.put(IComfortInstance.class, ComfortInstance.class);
            classesMap.put(IComfortProperty.class, ComfortProperty.class);
            classesMap.put(IComfortValue.class, ComfortValue.class);
            classesMap.put(IComfortType.class, ComfortType.class);
        }

        return classesMap;
    }

    public void beginTrans() {
        if (et != null && !et.isActive())
            et.begin();
        else {
            et = em.getTransaction();
            et.begin();
        }
    }

    public void commit() {
        if (et != null && et.isActive())
            et.commit();
    }

    public void rollback() {
        if (et != null && et.isActive())
            et.rollback();
    }

    public EntityTransaction getTransaction() {
        return (et != null && et.isActive()) ? et : (et = em.getTransaction());
    }

    private String replaceInterfaceNameToClassName(String sql) {
        String res = sql;
        for (Class<? extends IEntity> c : getClassesMap().keySet()) {
            res = res.replaceAll(c.getSimpleName(), classesMap.get(c).getCanonicalName());
        }
        return res;
    }

    private Query createQuery(String sql, Object... params) {
        Query query = em.createQuery(replaceInterfaceNameToClassName(sql));
        if (params != null && params.length > 0) {
            int i = 0;
            for (Object param : params) {
                i++;
                query.setParameter(i, param);
            }
        }
        return query;
    }

    public <T extends IEntity> List<T> findList(String sql, Object... params) {
        return (List<T>) createQuery(replaceInterfaceNameToClassName(sql), params).getResultList();
    }

    public <T extends IEntity> T findSingle(String sql, Object... params) {
        return (T) createQuery(replaceInterfaceNameToClassName(sql), params).getSingleResult();
    }

    public <T extends IEntity> T findByID(Class<T> entityClass, int id) {
        Class entity = getClassesMap().get(entityClass);
        T result = (T) em.getReference(entity, id);
        if (result == null)
            result = (T) em.find(entity, id);
        return result;
    }

    public <T extends IEntity> void save(List<T> entities) {
        for (T entity : entities) {
            try {
                em.persist(entity);
            } catch (Exception e) {
                em.merge(entity);
            }
        }
    }

    public <T extends IEntity> void save(T entity) {
        System.out.println("SAVE");
        try {
            em.persist(entity);
        } catch (Exception e) {
            em.merge(entity);
        }
        em.flush();
    }


    public <T extends IEntity> void delete(List<T> entities) {
        for (T entity : entities) {
            em.remove(entity);
        }
    }

    public <T extends IEntity> void delete(T entity) {
        em.remove(entity);
    }

    public <T extends IEntity> T create(Class<T> entityClass) {

        try {
            Object obj = getClassesMap().get(entityClass).newInstance();
            return (T) obj;
        } catch (Exception e) {
            ProgrammerError.general("Can't instanciate class that implements " + entityClass.getName());
            return null;
        }

    }

    public void createDB() {

        IComfortType type = create(IComfortType.class);
        ((ComfortType) type).setId(IComfortType.INTEGER);
        type.fillName("INTEGER");
        em.persist(type);

        type = create(IComfortType.class);
        ((ComfortType) type).setId(IComfortType.REFERENCE);
        type.fillName("REFERENCE");
        em.persist(type);

        type = create(IComfortType.class);
        ((ComfortType) type).setId(IComfortType.STRING);
        type.fillName("STRING");
        em.persist(type);

        type = create(IComfortType.class);
        ((ComfortType) type).setId(IComfortType.DECIMAL);
        type.fillName("DECIMAL");
        em.persist(type);

        type = create(IComfortType.class);
        ((ComfortType) type).setId(IComfortType.DATETIME);
        type.fillName("DATETIME");
        em.persist(type);
    }
}