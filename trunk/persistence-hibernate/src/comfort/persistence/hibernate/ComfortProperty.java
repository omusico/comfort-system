package comfort.persistence.hibernate;

import comfort.persistence.front.IComfortProperty;
import comfort.persistence.front.IComfortType;
import comfort.persistence.front.IComfortInstance;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Реализация сущности "свойство".
 */
@Entity
@Table(name = "CMT_PROPERTIES")
public class ComfortProperty extends ComfortNamedEntity<IComfortProperty> implements IComfortProperty {
    @ManyToOne(targetEntity = ComfortType.class)
    @JoinColumn(name = "type_id")
    private IComfortType type;

    public IComfortType getType() {
        return this.type;   
    };

    public IComfortProperty setType(IComfortType type){
        this.type = type;
        return this;
    }

    public Class toJavaClass() {
        switch (type.getId()){
            case IComfortType.DATETIME: return java.sql.Date.class;
            case IComfortType.DECIMAL: return BigDecimal.class;
            case IComfortType.INTEGER: return Integer.class;
            case IComfortType.REFERENCE: return IComfortInstance.class;
            case IComfortType.STRING: return String.class;
            default: return Object.class;
        }
    }

}
