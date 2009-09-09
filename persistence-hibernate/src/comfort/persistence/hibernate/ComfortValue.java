package comfort.persistence.hibernate;

import comfort.persistence.front.IComfortInstance;
import comfort.persistence.front.IComfortProperty;
import comfort.persistence.front.IComfortValue;

import javax.persistence.*;

/**
 * Реализация сущности "значение".
 */
@Entity
@Table(name = "CMT_VALUES")
public class ComfortValue extends ComfortEntity implements IComfortValue {
    @ManyToOne(targetEntity = ComfortProperty.class)
    @JoinColumn(name = "property_id")
    private IComfortProperty property;
    @ManyToOne(targetEntity = ComfortInstance.class)
    @JoinColumn(name = "instance_id")
    private IComfortInstance instance;
    @Column(name="integer_value")
    private int integerValue;
    @Column(name="string_value")
    private String stringValue;

    public IComfortProperty getProperty() {
        return property;
    }

    public IComfortInstance getInstance() {
        return instance;
    }

    public <T> T getValue(Class<T> valueClass) {
        if (valueClass == String.class)
          return  (T) stringValue;
        else if (valueClass == Integer.class)
          return (T) Integer.valueOf(integerValue);
        return null;
    }

    public <T> void setValue(T value) {
        if (value instanceof String)
          stringValue = (String) value;
        else if (value instanceof Integer)
          integerValue = (Integer) value;
    }

}
