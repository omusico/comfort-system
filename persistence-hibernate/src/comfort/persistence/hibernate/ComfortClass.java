package comfort.persistence.hibernate;

import comfort.persistence.front.IComfortClass;
import comfort.persistence.front.IComfortInstance;
import comfort.persistence.front.IComfortProperty;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.Where;
import org.jboss.seam.annotations.Name;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Реализация сущности "класса".
 */
@Entity
@Table(name = "CMT_CLASSES")
public class ComfortClass extends ComfortNamedEntity<IComfortClass> implements IComfortClass {
    @ManyToOne(targetEntity = ComfortClass.class)
    @JoinColumn(name = "PARENT", nullable = true)
    private IComfortClass parent;
    @OneToMany(targetEntity = ComfortProperty.class)
    @JoinTable(
            name = "CMT_CLASSES_SCHEMA",
            joinColumns = @JoinColumn(name = "CLASS_ID"),
            inverseJoinColumns = @JoinColumn(name = "PROP_ID")
    )
    private List<IComfortProperty> properties;
    @OneToMany(targetEntity = ComfortClass.class)
    @JoinTable(
            name="CMT_CLASSES",
            joinColumns = @JoinColumn(name = "PARENT", nullable = true),
            inverseJoinColumns = @JoinColumn(name = "ID")
    )
    private List<IComfortClass> children;

    public IComfortInstance createInstance() {
        ComfortInstance ins = new ComfortInstance();
        ins.setComfortClass(this);
        return ins;
    }

    public List<IComfortProperty> getProperties() {
        if (properties == null) properties = new ArrayList<IComfortProperty>();
        return properties;
    }

    public IComfortClass getParent() {
        return parent;
    }

    public void setParent(IComfortClass parent) {
        this.parent = parent;
    }

    public IComfortClass fillParent(IComfortClass parent) {
        this.parent = parent;
        return this;
    }

    public IComfortClass addProperty(IComfortProperty property) {
        getProperties().add(property);
        return this;
    }

    public List<IComfortClass> getChildren() {
        if (children == null) children = new ArrayList<IComfortClass>();
        return children;
    }
}
