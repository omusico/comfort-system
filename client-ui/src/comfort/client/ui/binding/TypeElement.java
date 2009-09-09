package comfort.client.ui.binding;

import comfort.client.ui.interfaces.IPage;
import comfort.client.ui.interfaces.IProperty;
import comfort.client.ui.interfaces.IType;
import comfort.client.ui.core.Resolver;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

/**
 * Author: Michael Morozov
 * Date: 20.01.2008
 * Time: 16:45:14
 */
public class TypeElement extends NamedGetter<IProperty> implements IType {
    @XmlAttribute(name = "extends")
    private String extended;
    @XmlElement(name = "property")
    private List<PropertyElement> properties;
    @XmlTransient
    private IPage page;

    public IPage getPage() {
        return page;
    }

    public void setPage(IPage page) {
        this.page = page;
    }

    public IType getExtended() {
        if (extended != null)
            return Resolver.getInstance().resolveType(extended);
        else
            return null;
    }

    public Class getJavaClass() {
        return null;
    }

    protected List getInternalList() {
        return properties;
    }
}
