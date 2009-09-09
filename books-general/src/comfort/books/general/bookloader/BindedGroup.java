package comfort.books.general.bookloader;

import comfort.books.IBookGroup;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

class BindedGroup implements IBookGroup {
    @XmlAttribute(name = "caption")
    private String caption;
    public String getCaption() {
        return caption;
    }

    @XmlAttribute(name = "book")
    private String bookName;
    public String getBookName() {
        return bookName != null ? bookName : "";
    }

    private boolean isChildrenInitialized = false;
    @XmlElement(name = "group")
    private List<IBookGroup> children;
    public List<IBookGroup> getChildren() {
        if (!isChildrenInitialized) {
            for (IBookGroup g : children) {
                BindedGroup bg = (BindedGroup) g;
                bg.setParent(this);
            }
            isChildrenInitialized = true;
        }
        return children;
    }

    private IBookGroup parent;
    public IBookGroup getParent() {
        return parent;
    }

    public void setParent(IBookGroup parent) {
        this.parent = parent;
    }
}