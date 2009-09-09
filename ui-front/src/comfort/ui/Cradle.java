package comfort.ui;

import java.awt.*;

public abstract class Cradle<E> {
    protected Cradle(Object layout, Object alignment, int width, int height, E[] children) {
        this.layout = layout;
        this.alignment = alignment;
        this.size = new Dimension(width, height);
        this.children = children;
    }

    private Object layout;

    /**
     * Определяет расположение вложенных элементов.
     * @return объект, определяющий расположение, или null, если расположение не учитывается.
     * @see IBridge#setLayout
     */
    public Object getLayout() {
        return layout;
    }

    private Object alignment;

    /**
     * Определяет выравнивание элемента внутри контейнера.
     * Вместе с размерами определяет положение элемента в контейнере.
     *
     * @return объект, определяющий выравнивание, или null, если выравнивание не учитывается.
     * @see IBridge#place
     */
    public Object getAlignment() {
        return alignment;
    }

    private Dimension size;

    /**
     * Определяет предпочтительные размеры элемента.
     * Размеры и выравнивание определяют положение элемента в контейнере.
     * Если какой-нибудь размер равен 0, то размер элемента будет зависеть от размеров вложенных элементов.
     *
     * @return размеры.
     */
    public Dimension getSize() {
        return size;
    }

    private E[] children;

    /**
     * Вложенные элементы.
     * @return массив элементов.
     */
    public E[] getChildren() {
        return children;
    }
}