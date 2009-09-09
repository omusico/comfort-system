package comfort.client.ui.binding;


import comfort.client.ui.core.ReflectionUtils;
import comfort.client.ui.core.Resolver;
import comfort.client.ui.interfaces.IComponent;
import comfort.client.ui.interfaces.IPage;
import comfort.client.ui.interfaces.IProperty;
import comfort.client.ui.interfaces.IType;
import comfort.exceptions.ProgrammerError;

import javax.swing.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.awt.*;
import java.util.List;

/**
 * Author: Michael Morozov
 * Date: 20.01.2008
 * Time: 16:42:39
 */
public class ComponentElement extends NamedGetter<IComponent> implements IComponent {
    @XmlElement(name = "component")
    private List<ComponentElement> children;
    @XmlAttribute(name = "layout")
    private String _layout;
    @XmlAttribute(name = "size")
    private String _size;
    @XmlAttribute(name = "type")
    private String _type;
    @XmlTransient
    private JComponent jcomponent;
    @XmlTransient
    private Dimension size;
    @XmlTransient
    private String layout;
    @XmlTransient
    private IType type;
    @XmlTransient
    private IPage page;

    public IPage getPage() {
        return page;
    }

    public void setPage(IPage page) {
        this.page = page;
    }


    public String getLayout() {
        return layout;
    }

    public Dimension getSize() {
        return size;
    }

    public JComponent getJComponent() {
        if (jcomponent == null) {
            try {
                jcomponent = (JComponent) type.getJavaClass().newInstance();
            } catch (Exception e) {
                ProgrammerError.general("Cant't create intstance", e);
            }

            jcomponent.setLayout(new BorderLayout());
            jcomponent.setPreferredSize(getSize());
            // Р�СЃРїРѕР»СЊР·СѓРµРј СЂРµС„Р»РµРєСЃРёСЋ РґР»СЏ РІС‹Р·РѕРІР° РјРµС‚РѕРґРѕРІ
            // СѓСЃС‚Р°РЅРѕРІРєРё СЃРІРѕР№СЃС‚РІ РєРѕРјРїРѕРЅРµРЅС‚Р°
            Class[] params = null;
            for (IProperty property : type) {
                ReflectionUtils.setProperty(property.getName(), property.getValue(), jcomponent);
            }
        }
        return jcomponent;
    }

    protected List getInternalList() {
        return children;
    }

    public void validate() {
        super.validate();
        layout = PlacableUtil.getLayout(_layout);
        size = PlacableUtil.getSize(_size);
        getType();

    }

    public IType getType() {
        if (type == null) {
            Resolver.getInstance().resolveType(_type);
        }
        return type;
    }
}
