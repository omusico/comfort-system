package comfort.persistence.front;

/**
 * Значение хранящееся в базе для {@link IComfortInstance объектов}
 */
public interface IComfortValue extends IEntity{
    /**
     * Возвращает свойство объекта
     * @return свойство
     */
    IComfortProperty getProperty();

    /**
     * Возвращает объект, которому принадлежит это значение
     * @return объект
     */
    IComfortInstance getInstance();

    /**
     * Возвращает значение
     * @param valueClass тип(класс) значения
     * @return значение
     */
    <T>T getValue(Class<T> valueClass);

    /**
     * Устанавливает значение
     * @param value значение
     */
    <T>void setValue(T value);
    
}
