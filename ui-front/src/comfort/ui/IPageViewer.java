package comfort.ui;

import comfort.exceptions.ProgrammerError;

/**
 * Занимается отображением страниц.
 */
public interface IPageViewer {
    /**
     * Определяет области страницы.
     * @param root корневая область.
     */
    void defineAreas(Area root);

    /**
     * Задает {@link IWidgetResolver}.
     * Когда странице потребуется новый элемент,
     * этот {@link IWidgetResolver} будет использоваться для создания требуемого элемента.
     */
    void setWidgetResolver(IWidgetResolver widgetResolver);

    /**
     * Задает контроллер.
     * При отображении страницы вызываются соответствующие методы контроллера.
     * @param controller контроллер.
     * @see #show(IPage) 
     */
    void setController(IController controller);

    /**
     * Отображает страницу.
     * Перед отображением страницы, необходимо определить ее макет,
     * назначив используемые области методом {@link #defineAreas},
     * а также используемые элементы методом {@link #setWidgetResolver}
     * и контроллер методом {@link #setController(IController)}.
     * В противном случае будет вызван {@link comfort.exceptions.ProgrammerError}.
     * @param page страница.
     * @throws comfort.exceptions.ProgrammerError если не назначены области или элементы страницы.
     * @see #setController(IController) 
     */
    void show(IPage page) throws ProgrammerError;

    /**
     * Скрывает текущую страницу
     */
    void hide();
}
