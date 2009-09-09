package comfort.persistence.front;

import java.util.List;

/**
 * Сущность представляющая из себя класс (шаблон) постороения объектов.
 * Содержит в себе необходимый набор свойств, которыми должен обладать {@link IComfortInstance экземпляр класса}.
 */
public interface IComfortClass extends INamedEntity<IComfortClass> {
    /**
     * Создает экземпляр класса
     * @return экземпляр класса
     */
    IComfortInstance createInstance();

    /**
     * Возвращает список свойств класса
     * @return список свойств
     */
    List<IComfortProperty> getProperties();

    /**
     * Возвращает родительский класс.
     * @return родительский класс
     */
    IComfortClass getParent();
    /**
     * Устанавливает взаимотношение родитель-ребенок.
     * @param parent родительский класс
     */
    void setParent(IComfortClass parent);

    /**
     * Устанавливает взаимотношение родитель-ребенок. Возвращает ссылку на себя.
     * @param parent родительский класс
     * @return ссылка на себя
     */
    IComfortClass fillParent(IComfortClass parent);
    IComfortClass addProperty(IComfortProperty property);

    /**
     * Возвращает массив классов, являющиеся детьми по отношению к классу
     * @return список классов
     */
    List<IComfortClass> getChildren();
}
