package comfort.ui.swing;

import comfort.exceptions.*;
import comfort.ui.IBridge;
import comfort.ui.IElement;

import javax.swing.*;
import java.awt.*;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;

public class Bridge implements IBridge {
    private IElementCreator creator;

    public Bridge(IElementCreator creator) {
        if (creator == null)
            throw ProgrammerError.nullArgument("creator");
        this.creator = creator;
    }

    private static class SwingElement {
        public SwingElement(JComponent component) {
            this.component = component;
        }
        public SwingElement(Object element) {
            Object o;
            try {
                o = element.getClass().getMethod("getComponent").invoke(element);
            } catch (IllegalAccessException e) {
                throw UnexpectedException.exception(e);
            } catch (InvocationTargetException e) {
                throw UnexpectedException.exception(e);
            } catch (NoSuchMethodException e) {
                throw ProgrammerError.general("The method is not found: getComponent", e);
            }

            this.component = (JComponent) o;
        }

        private JComponent component;

        public JComponent getComponent() {
            return component;
        }
    }

    private static class Element implements IElement {
        public Element(SwingElement element) {
            this.element = element;
            nativeElement = null;
        }
        public Element(Object element) {
            this.element = new SwingElement(element);
            nativeElement = element;
        }

        public SwingElement element;
        public Object nativeElement;
        public boolean isNullLayout = true;
    }

    private static Element getElement(IElement what) {
        if (!(what instanceof Element))
            throw ProgrammerError.general("The object (%s) must be an instance of %s", what, Element.class.getName());

        return (Element) what;
    }

    private static JComponent getComponent(IElement what) {
        return getElement(what).element.getComponent();
    }

    private static JComponent getComponent(Element what) {
        return what.element.getComponent();
    }

    @Override
    public void setSize(IElement component, int width, int height) {
        if (width == 0)
            width = 1;

        if (height == 0)
            height = 1;

        getComponent(component).setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void setLayout(IElement container, Object layout) {
        LayoutManager mgr = null;

        if (layout == null || "border".equalsIgnoreCase(layout.toString()))
            mgr = new BorderLayout();

        Element el = getElement(container);
        getComponent(el).setLayout(mgr);
        el.isNullLayout = layout == null;
    }

    @Override
    public void place(IElement what, IElement where, Object alignment) {
        Element container = getElement(where);
        Element el = getElement(what);

        if (container.isNullLayout) {
            if (alignment != null)
                throw ProgrammerError.general("The alignment is '%s' but must be null if container have null layout", alignment);
            alignment = BorderLayout.CENTER;
        }

        getComponent(container).add(getComponent(el), alignment);
    }

    @Override
    public void remove(IElement what, IElement from) {
        getComponent(from).remove(getComponent(what));
    }

    @Override
    public void clear(IElement container) {
        getComponent(container).removeAll();
    }

    @Override
    public IElement createPane() {
        return new Element(new SwingElement(new JPanel()));
    }

    @Override
    public IElement createElement(String code) {
        return new Element(creator.create(code));
    }

    @Override
    public IElement createElement(Reader code) {
        return new Element(creator.create(code));
    }

    /**
     * Создает элемент для Swing компонента.
     * @param component Swing компонент.
     * @return новый элемент, соответствующий указанному компоненту.
     */
    public static IElement createSwingElement(JComponent component) {
        return new Element(new SwingElement(component));
    }

    /**
     * Возвращает объект, созданный с помощью {@link IElementCreator},
     * соответствующий указанному элементу.
     * @param element элемент.
     * @return объект, созданный с помощью {@link IElementCreator}.
     */
    public static Object getNativeElement(IElement element) {
        return getElement(element).nativeElement;
    }
}
