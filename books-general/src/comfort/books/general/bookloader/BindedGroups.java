package comfort.books.general.bookloader;

import comfort.books.IBookGroup;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

class BindedGroups {
    @XmlElement(name = "group")
    private List<IBookGroup> groups;

    public List<IBookGroup> getGroups() {
        return groups;
    }
}