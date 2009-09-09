package comfort.ui;

/**
 * Страница.
 */
public interface IPage {
    /**
     * Имя страницы.
     *
     * @return имя.
     */
    String getName();

    /**
     * Возвращает элемент страницы, который находится в указанной области.
     *
     * @param area имя области.
     * @return имя элемента или null, если на этой странице указанная область пуста.
     */
    String getWidget(String area);
}