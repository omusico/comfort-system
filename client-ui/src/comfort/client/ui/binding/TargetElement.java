package comfort.client.ui.binding;

import comfort.client.ui.core.Resolver;
import comfort.client.ui.interfaces.IArea;
import comfort.client.ui.interfaces.IComponent;
import comfort.client.ui.interfaces.ITarget;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

/**
 * Author: Michael Morozov
 * Date: 20.01.2008
 * Time: 16:41:14
 */

// todo: РЎРґРµР»Р°С‚СЊ СЂРµР°Р»РёР·Р°С†РёСЋ TargetElement
public class TargetElement extends NamedGetter<IComponent> implements ITarget {
    @XmlAttribute(name = "for")
    private String _forArea;
    @XmlElement(name = "component")
    private List<ComponentElement> components;
    @XmlTransient
    private IArea forArea;

    protected List getInternalList() {
        return components;
    }

    public IArea getFor() {
        if (forArea == null)
        {
            forArea = Resolver.getInstance().resolveArea(_forArea);
        }
        return forArea;
    }

    public void validate() {
        super.validate();

        getFor();
        for (IComponent comp: this){
            comp.validate();
        }
    }
}
