package comfort.persistence.front;

/**
 * Именованная сущность
 */
public interface INamedEntity<T extends INamedEntity> extends IEntity{
    /**
     * Возвращает имя сущности
     * @return имя
     */
    String getName();

    /**
     * Устанавливает новое имя для сущности. Возвращает себя,
     * используется при цепной установки свойств бина.
     * @param name имя
     * @return ссылка на себя
     */
    T fillName(String name);

    /**
     * Устанавливает имя сущности.
     * @param name
     */
    void setName(String name);
}
