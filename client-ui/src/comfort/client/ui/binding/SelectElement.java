package comfort.client.ui.binding;

import comfort.client.ui.core.Resolver;
import comfort.client.ui.interfaces.IProperty;
import comfort.client.ui.interfaces.ISelect;
import comfort.client.ui.interfaces.IView;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mormih
 * Date: 18.01.2008
 * Time: 18:07:45
 * To change this template use File | Settings | File Templates.
 */
public class SelectElement extends NamedGetter<IProperty> implements ISelect {
    @XmlElement(name = "property")
    private List<PropertyElement> properties;
    @XmlElement(name = "view")
    private String _relatedView;
    @XmlTransient
    private IView relatedView;

    public List<PropertyElement> getProperties() {
        return properties;
    }

    public IView getRelatedView() {
        return null;
    }

    protected List getInternalList() {
        return properties;
    }

    public void validate() {
        super.validate();
        
        if(_relatedView != null){
            relatedView = Resolver.getInstance().resolveView(_relatedView);
        }

    }
}
