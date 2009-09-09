package comfort.client.ui.binding;

import comfort.client.ui.interfaces.INamed;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by IntelliJ IDEA.
 * User: mormih
 * Date: 18.01.2008
 * Time: 18:04:59
 * To change this template use File | Settings | File Templates.
 */
abstract class NamedElement implements INamed {
    @XmlAttribute(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void validate() {

    }
}
