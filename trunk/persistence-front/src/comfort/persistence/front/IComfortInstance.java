package comfort.persistence.front;

/**
 * Экземпляр класса. Объект.
 */
public interface IComfortInstance extends INamedEntity<IComfortInstance>{
    /**
     * Возвращает класс объекта
     * @return класс объекта
     */
    IComfortClass getComfortClass();

    /**
     * Возвращает значение свойства объекта
     * @param propertyName имя свойства
     * @return значение свойства
     */
    <T> T getPropertyValue(String propertyName);
    /**
     * Возвращает значение свойства объекта
     * @param property свойство
     * @return значение свойства
     */
    <T> T getPropertyValue(IComfortProperty property);

    /**
     * Устанавливает свойство объекта
     * @param property свойство
     * @param value значение
     * @return ссылка на себя
     */
    IComfortInstance setPropertyValue(IComfortProperty property, Object value);
    /**
     * Устанавливает свойство объекта
     * @param propertyName имя свойства
     * @param value значение
     * @return ссылка на себя
     */
    IComfortInstance setPropertyValue(String propertyName, Object value);
}
