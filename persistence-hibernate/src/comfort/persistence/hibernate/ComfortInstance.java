package comfort.persistence.hibernate;

import comfort.persistence.front.IComfortClass;
import comfort.persistence.front.IComfortInstance;
import comfort.persistence.front.IComfortProperty;
import comfort.persistence.front.IComfortValue;

import javax.persistence.*;
import java.util.List;

/**
 * Реализация сущности "объект/экземпляр класса".
 */
@Entity
@Table(name = "CMT_INSTANCES")
public class ComfortInstance extends ComfortNamedEntity<IComfortInstance> implements IComfortInstance {
    @ManyToOne(targetEntity = ComfortClass.class)
    @JoinColumn(name = "class_id")
    private IComfortClass comfortClass;

    @Transient
    @OneToMany(targetEntity = ComfortValue.class, fetch = FetchType.LAZY)
    @JoinTable(name = "CMT_VALUES", joinColumns = {@JoinColumn(name = "instance_id")})
    private List<IComfortValue> values;

    public IComfortClass getComfortClass() {
        return comfortClass;
    }

    public <T> T getPropertyValue(String propertyName) {
        for (IComfortValue v : values) {
            if (v.getProperty().getName().equalsIgnoreCase(propertyName)) {
                return (T) v.getValue(v.getProperty().toJavaClass());
            }
        }
        return null;
    }

    public <T> T getPropertyValue(IComfortProperty property) {
        for (IComfortValue v : values) {
            if (v.getProperty() == property) {
                return (T) v.getValue(property.toJavaClass());
            }
        }
        return null;
    }

    public IComfortInstance setPropertyValue(IComfortProperty property, Object value) {
        return this;
    }

    public IComfortInstance setPropertyValue(String propertyName, Object value) {
        return this;
    }

    /**
     * Устанавливает класс
     * @param comfortClass класс
     */
    public void setComfortClass(IComfortClass comfortClass) {
        this.comfortClass = comfortClass;
    }
}
