package comfort.client.ui.binding;

import comfort.client.ui.interfaces.IProperty;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Author: Michael Morozov
 * Date: 20.01.2008
 * Time: 16:51:12
 */
public class PropertyElement extends NamedElement implements IProperty {
    @XmlAttribute
    private String value;
    @XmlAttribute
    private String ref;

    public String getValue() {
        return value;
    }

    public String getRef() {
        return ref;
    }


}
