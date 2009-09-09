package comfort.persistence.front;

import javax.ejb.Remote;
import javax.ejb.Local;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Map;

/**
 * Выполняет поиск, создание, сохранение, удаление и доступ
 * к постоянным (персистентным) данным
 */
@Local
public interface DAO {
    String MAPPED_NAME = "comfort.persistence.front.DAO";

    /**
     * Возвращает список сущностей по запросу
     * @param sql запрос
     * @param params параметры запроса
     * @return список сущностей
     */
    <T extends IEntity> List<T> findList(String sql, Object ... params);

    /**
     * Возвращает первую удовлетворяющую условиям выборки
     * сущностей
     * @param sql запрос на выборку
     * @param params параметры запроса
     * @return сущность
     */
    <T extends IEntity> T findSingle(String sql, Object ... params);

    /**
     * Возвращает сущность по ее идентификатору
     * @param entityClass класс интерфейса сущности
     * @param id известный идентификатор
     * @return сущность
     */
    <T extends IEntity> T findByID(Class<T> entityClass, int id);

    /**
     * Сохранение списка сущностей в СУБД
     * @param entities список сущностей
     */
    <T extends IEntity> void save(List<T> entities);

    /**
     * Сохранение одной сущности
     * @param entity сущность
     */
    <T extends IEntity> void save(T entity);

    /**
     * Удаление списка сущностей из СУБД
     * @param entities список сущностей
     */
    <T extends IEntity> void delete(List<T> entities);

    /**
     * Удаление сущности из СУБД
     * @param entity сущность
     */
    <T extends IEntity> void delete(T entity);

    /**
     * Создание сущности. Но не сохранение ее в СУБД.
     * Для сохранение используйте один из методов:
     * {@link #save(java.util.List)}, {@link #save(IEntity)}
     *
     * @param entityClass Класс интерфейса для сущности
     * @return Ссылка на сущность
     */
    <T extends IEntity> T create(Class<T> entityClass);

    /**
     * Построение базы данных. Все аспекты по созданию базы данных
     * (прогон скриптов, индексирование таблиц и прочее)
     */
    void createDB();

    /**
     * Возвращает карту соотвествия Интефрфейса сущности и
     * класом реализацией в данном DAO
     *
     * @return карта <Интерфейс, класс реализации интерфейса>
     */
    Map<Class<? extends IEntity>, Class> getClassesMap();

    void beginTrans();
    void commit();
    void rollback();
    
    EntityTransaction getTransaction();
}
