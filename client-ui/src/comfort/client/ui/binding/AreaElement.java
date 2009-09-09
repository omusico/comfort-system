package comfort.client.ui.binding;


import comfort.client.ui.interfaces.IArea;
import comfort.client.ui.interfaces.IPage;

import javax.swing.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.awt.*;
import java.util.List;

/**
 * Author: Michael Morozov
 * Date: 20.01.2008
 * Time: 17:25:18
 */
public class AreaElement extends NamedGetter<IArea> implements IArea {
    @XmlElement(name = "area")
    private List<AreaElement> children;
    @XmlAttribute(name = "layout")
    private String _layout;
    @XmlAttribute(name = "size")
    private String _size;
    @XmlTransient
    private JComponent jcomponent;
    @XmlTransient
    private Dimension size;
    @XmlTransient
    private String layout;
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
            validate();
            jcomponent = new JPanel(new BorderLayout());
            jcomponent.setPreferredSize(getSize());
            for (IArea area: this)
                this.jcomponent.add(area.getJComponent(), area.getLayout());
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

    }


}
