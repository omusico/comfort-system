package comfort.client.ui.binding;

import annotation.NotShow;
import comfort.client.ui.interfaces.IProperty;
import comfort.client.ui.interfaces.ISelect;
import comfort.client.ui.interfaces.IView;
import comfort.client.ui.interfaces.IPage;
import comfort.exceptions.ProgrammerError;
import interfaces.IEntity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mormih
 * Date: 18.01.2008
 * Time: 18:04:36
 * To change this template use File | Settings | File Templates.
 */
public class ViewElement extends NamedGetter<ISelect> implements IView {

    @XmlElement(name = "select")
    private List<SelectElement> selects;
    @XmlElement(name = "extends")
    private String extended;
    @XmlTransient
    private IPage page;

    public IPage getPage() {
        return page;
    }

    public void setPage(IPage page) {
        this.page = page;
    }

    public Object getObject(IEntity entity, ISelect select, Object... params) {


        String getMethod = "get" + select.getName().substring(0, 1).toUpperCase() + select.getName().substring(1);
        try {
            Method method;
            if (params.length > 0) {
                ArrayList<Class> classes = new ArrayList<Class>();
                for (Object param : params) {
                    classes.add(param.getClass());
                }
                Class[] carray = (Class[]) classes.toArray();
                method = entity.getClass().getMethod(getMethod, carray);
            } else
                method = entity.getClass().getMethod(getMethod);


            if (!method.isAnnotationPresent(NotShow.class))
                return method.invoke(entity);
            else
                throw ProgrammerError.general(String.format("Can't show property[%s]", select.getName()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public String getString(IEntity entity, ISelect select, Object... params) {
        IProperty property = select.get("format");
        if (property != null && property.getValue() != null) {
            return String.format(
                    property.getValue(),
                    getObject(entity, select, params));
        }

        return getObject(entity, select, params).toString();

    }

    public IView getExtended() {
        return null;
    }

    protected List getInternalList() {
        return selects;
    }
}
