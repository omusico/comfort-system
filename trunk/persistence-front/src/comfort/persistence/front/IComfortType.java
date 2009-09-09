package comfort.persistence.front;

/**
 * Тип {@link IComfortProperty свойства}
 */
public interface IComfortType extends INamedEntity<IComfortType> {
    /**
     * Встроенный тип: целое
     */
    final static int INTEGER = 1;
    /**
     * Встроенный тип: строка
     */
    final static int STRING = 2;
    /**
     * Встроенный тип: числовой, дробный тип
     */
    final static int DECIMAL = 3;
    /**
     * Встроенный тип: дата и время
     */
    final static int DATETIME = 4;
    /**
     * Встроенный тип: ссылка на другие объекты
     */
    final static int REFERENCE = 5;
}
