package comfort.ui;

import java.io.Reader;

/**
 * Предоставляет механизм создания и размещения элементов.
 * Его реализация зависит от технологии UI.
 */
public interface IBridge {
    /**
     * Размещает элемент внутри другого элемента-контейнера
     *
     * @param what      что разместить
     * @param where     где разместить (контейнер)
     * @param alignment определяет выравнивание внутри контейнера.
     * Может быть {@code null}, если выравнивание не учитывается,
     * например, в случае, когда елемент {@code where} имеет {@code layout = null}.
     */
    void place(IElement what, IElement where, Object alignment);

    /**
     * Удаляет элемент из контейнера
     *
     * @param what что удалить
     * @param from откуда (контейнер)
     */
    void remove(IElement what, IElement from);

    /**
     * Удаляет все элементы из контейнера
     *
     * @param container контейнер
     */
    void clear(IElement container);

    /**
     * Создает пустую панель.
     *
     * @return созданный элемент-контейнер.
     */
    IElement createPane();

    /**
     * Создает элемент.
     * @param code код, определяющий, какой элемент должен быть создан.
     * @return созданный элемент.
     */
    IElement createElement(String code);

    /**
     * Создает элемент.
     * @param code {@link Reader} для чтения кода.
     * @return созданный элемент.
     */
    IElement createElement(Reader code);

    /**
     * Устанавливает элементу способ расположения вложенных элементов.
     *
     * @param container элемент-контейнер.
     * @param layout    определяет расположение.
     *                  Может быть null, в этом случае может быть только один вложенный элемент,
     *                  который будет занимать всю область контейнера.
     */
    void setLayout(IElement container, Object layout);

    /**
     * Устанавливает предпочтительные размеры элемента.
     *
     * @param component компонент.
     * @param width ширина. Если равна 0, то ширина компонента будет зависеть от содержащихся в нем элементах.
     * @param height высота. Если равна 0, то высота компонента будет зависеть от содержащихся в нем элементах.
     */
    void setSize(IElement component, int width, int height);
}