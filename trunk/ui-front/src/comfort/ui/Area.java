package comfort.ui;

/**
 * Область.
 * Используется для организации макета страницы.
 */
public class Area extends Cradle<Area> {
    /**
     * Создает корневую область.
     * @param name имя области.
     * @param layout определяет расположение вложенных элементов.
     * @param children вложенные области.
     * @return область.
     * @see #getLayout()
     * @see #getChildren()
     */
    public static Area createRootArea(String name, Object layout, Area... children) {
        return new Area(name, layout, null, 0, 0, children);
    }

    /**
     * Создает область как контейнер для других областей.
     * @param name имя области.
     * @param layout определяет расположение вложенных элементов.
     * @param alignment определяет выравнивание этой области внутри своего контейнера.
     * @param width ширина.
     * @param height высота.
     * @param children вложенные области.
     * @return область.
     * @see #getLayout()
     * @see #getAlignment()
     * @see #getSize()
     * @see #getChildren()
     */
    public static Area createArea(String name, Object layout, Object alignment, int width, int height, Area... children) {
        return new Area(name, layout, alignment, width, height, children);
    }

    /**
     * Создает область без вложенных областей.
     * @param name имя области.
     * @param alignment определяет выравнивание внутри контейнера.
     * @param width ширина.
     * @param height высота.
     * @see #getAlignment()
     * @see #getSize()
     * @return область.
     */
    public static Area createEndArea(String name, Object alignment, int width, int height) {
        return new Area(name, null, alignment, width, height);
    }

    protected Area(String name, Object layout, Object alignment, int width, int height, Area... children) {
        super(layout, alignment, width, height, children);
        this.name = name;
    }

    protected Area() {
        super(null, null, 0, 0, null);
    }

    private String name;

    /**
     * Имя области.
     * @return имя или null, если область не имеет имени.
     */
    public String getName() {
        return name;
    }
}