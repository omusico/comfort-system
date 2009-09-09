package comfort.persistence.front;

/**
 * Свойство {@link IComfortClass класса}
 */
public interface IComfortProperty extends INamedEntity<IComfortProperty> {
    /**
     * Класс предполагаемого значение свойства
     * @return класс значения
     */
    IComfortType getType();
    IComfortProperty setType(IComfortType type);
    Class toJavaClass();
}
