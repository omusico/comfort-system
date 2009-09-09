package comfort.persistence.hibernate;

import comfort.persistence.hibernate.JpaDAO;
import comfort.persistence.front.IEntity;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.ejb.EntityManagerImpl;

import javax.persistence.PersistenceContextType;
import javax.persistence.spi.PersistenceUnitTransactionType;
import java.util.List;
import java.net.URL;

/**
 * Реализация DAO для чистого hibernate, без JPA.
 * Необходим для тестирования или других целей, когда
 * нет надобности в сервере приложений.
 * Мапинг сущностей и настройки на базу в {@link comfort/persistence/hibernate/hibernate.cfg.xml}
 */

public class PureHibernateDAO extends JpaDAO {
    public PureHibernateDAO(URL config) {
        try {
            SessionFactory sessionFactory = new AnnotationConfiguration().
                    configure(config).
                    buildSessionFactory();
            em = new EntityManagerImpl(
                    sessionFactory,
                    PersistenceContextType.TRANSACTION,
                    PersistenceUnitTransactionType.RESOURCE_LOCAL, true);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    @Override
    public void createDB() {
        beginTrans();
        try {
            super.createDB();
            commit();
        } catch (Exception e) {
            rollback();
        }
    }

    @Override
    public <T extends IEntity> void save(List<T> entities) {
        beginTrans();
        try {
            super.save(entities);
            commit();
        } catch (Exception e) {
            rollback();
        }
    }

    @Override
    public <T extends IEntity> void save(T entity) {
        beginTrans();
        try {
            super.save(entity);
            commit();
        } catch (Exception e) {
            rollback();
        }
    }

    @Override
    public <T extends IEntity> void delete(List<T> entities) {
        beginTrans();
        try {
            super.delete(entities);
            commit();
        } catch (Exception e) {
            rollback();
        }
    }

    @Override
    public <T extends IEntity> void delete(T entity) {
        beginTrans();
        try {
            super.delete(entity);
            commit();
        } catch (Exception e) {
            rollback();
        }
    }
}
