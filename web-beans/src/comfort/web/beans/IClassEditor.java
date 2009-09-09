package comfort.web.beans;

import comfort.persistence.front.IComfortClass;

import javax.ejb.Local;
import javax.ejb.Remove;
import java.util.List;

import org.richfaces.event.NodeSelectedEvent;

@Local
public interface IClassEditor {
    List<IComfortClass> getRootClasses();
    void endChange(IComfortClass clazz);
    void search(String text);

    void destroy();

    List<IComfortClass> getPlaneList();
    void selectClass(NodeSelectedEvent event);

    String getSearchText();

    void setSearchText(String searchText);
}
