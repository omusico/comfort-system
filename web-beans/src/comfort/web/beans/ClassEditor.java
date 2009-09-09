package comfort.web.beans;

import comfort.persistence.front.DAO;
import comfort.persistence.front.IComfortClass;
import org.jboss.seam.ScopeType;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.*;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.richfaces.component.html.HtmlTree;
import org.richfaces.event.NodeSelectedEvent;

import java.util.List;

@Name("classEditor")
@Scope(ScopeType.SESSION)
public class ClassEditor //implements IClassEditor
{
    @Logger
    private Log log;
    @In(create = true)
    private DAO dao;
    private List<IComfortClass> roots;
    @In(required = false)
    @Out(required = false)
    private IComfortClass selectedClass;
    private List<IComfortClass> planeList;
    @In(required = false)
    @Out(required = false)
    private String presentationTabName = "treeTab";

    public List<IComfortClass> getRootClasses()
    {
        if (roots == null) roots = dao.findList("from IComfortClass where parent is null");
        return roots;
    }

    @Begin
    @End
    public void save()
    {
        log.debug("BEGIN");
        dao.save(selectedClass);
        log.debug("END");
    }

    public void search(String text)
    {
        presentationTabName = "planeListTab";
        planeList = dao.findList("from IComfortClass where name like ?", "%" + text + "%");
        System.out.println("planeList.size = " + planeList.size());
    }

//    @Remove
//    public void destroy()
//    {
//    }

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
