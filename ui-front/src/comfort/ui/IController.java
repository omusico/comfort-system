package comfort.ui;

/**
 * Контроллер
 */
public interface IController {
    /**
     * Вызывается каждый раз до отображении элемента.
     * @param page       активная страница, на которой будет отображен элемент.
     * @param areaName   имя области, где будет отображен элемент.
     * @param widgetName имя элемента.
     * @param widget     сам элемент, т. е. визуальный компонент.
     */
    void activate(IPage page, String areaName, String widgetName, IElement widget);

    /**
     * Вызывается каждый раз после скрытия элемента.
     * @param page       страница, на которой отображался элемент.
     * @param areaName   имя области, где отображался элемент.
     * @param widgetName имя элемента.
     * @param widget     сам элемент, т. е. визуальный компонент.
     */
    void deactivate(IPage page, String areaName, String widgetName, IElement widget);
}