package comfort.ui.core.unittest;

import comfort.exceptions.UnexpectedException;
import comfort.ui.IBridge;
import comfort.ui.IElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

class TestBridge implements IBridge {
    public static class TestElement implements IElement {
        public TestElement(String code) {
            this.list = new ArrayList<IElement>();
            this.code = code;
        }

        public List<IElement> list;
        public Object alignment;
        public Object layout;
        public int width;
        public int height;
        public String code;

    }

    public static List<IElement> getList(IElement el) {
        return ((TestElement) el).list;
    }

    public static IElement getListElement(IElement list, int index) {
        return getList(list).get(index);
    }

    public static TestElement getTestElement(IElement el) {
        return (TestElement) el;
    }

    public void place(IElement what, IElement where, Object alignment) {
        getTestElement(what).alignment = alignment;
        getList(where).add(what);
    }

    public void remove(IElement what, IElement from) {
        getList(from).remove(what);
    }

    public void clear(IElement container) {
        getList(container).clear();
    }

    public static final String testPanelCode = "Test Panel";

    public IElement createPane() {
        return new TestElement(testPanelCode);
    }

    public IElement createElement(String code) {
        return new TestElement(code);
    }

    public IElement createElement(Reader r) {
        BufferedReader br = new BufferedReader(r);

        String code = "";
        try
        {
            for (;;) {
                int c = br.read();

                if (c == -1) break;

                code += (char) c;
            }

        } catch (IOException e) {
            throw UnexpectedException.exception(e);
        }

        return createElement(code);
    }

    public void setLayout(IElement container, Object layout) {
        getTestElement(container).layout = layout;
    }

    public void setSize(IElement component, int w, int h) {
        getTestElement(component).width = w;
        getTestElement(component).height = h;
    }
}
