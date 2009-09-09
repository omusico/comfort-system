package comfort.web.beans;

import comfort.persistence.front.DAO;
import comfort.persistence.front.IComfortClass;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.richfaces.event.NodeSelectedEvent;
import org.richfaces.component.html.HtmlTree;

import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.swing.tree.TreeNode;
import java.util.List;

@Stateful
@Name("classEditor")
@Scope(ScopeType.SESSION)
public class ClassEditor implements IClassEditor
{
    @EJB
    private DAO dao;
    private List<IComfortClass> roots;
    @In(required = false)
    @Out(required = false)
    private IComfortClass selectedClass;
    private List<IComfortClass> planeList;
    @In(required = false)
    @Out
    private String presentationTabName = "treeTab";

    @In(scope = ScopeType.EVENT)
    private String searchText;

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }


    public List<IComfortClass> getRootClasses()
    {
        if (roots == null) roots = dao.findList("from IComfortClass where parent is null");
        return roots;
    }

    @Begin
    @End
    public void endChange(IComfortClass clazz)
    {
        System.out.println("BEGIN");
        dao.save(clazz);
        System.out.println("END");
    }

    public void search(String text)
    {
        presentationTabName = "planeListTab";
        planeList = dao.findList("from IComfortClass where name like ?", "%" + text + "%");
        System.out.println("searchText = " + searchText);
    }

    @Remove
    public void destroy()
    {
    }

    public List<IComfortClass> getPlaneList()
    {
        return planeList;
    }

    public void selectClass(NodeSelectedEvent event)
    {
        HtmlTree tree = (HtmlTree) event.getComponent();
        selectedClass = (IComfortClass) tree.getRowData();
        System.out.println(selectedClass);
    }
}
