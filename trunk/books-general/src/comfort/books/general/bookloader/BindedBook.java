package comfort.books.general.bookloader;

import comfort.books.general.IGeneralBook;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

class BindedBook implements IGeneralBook {
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "caption")
    private String caption;
    @XmlElement(name = "initial-process")
    private String initialProcess;
    @XmlElement(name = "action-handler")
    private String actionHandler;
    @XmlElement(name = "class-path")
    private String classPath;

    public String getName() {
        return name;
    }

    public String getCaption() {
        return caption;
    }

    public String getInitialProcess() {
        return initialProcess;
    }

    public String getActionHandler() {
        return actionHandler;
    }

    public String getClassPath() {
        return classPath;
    }


}