package comfort.persistence.hibernate;

import comfort.persistence.front.IComfortType;

import javax.persistence.*;

/**
 * Р РµР°Р»РёР·Р°С†РёСЏ СЃСѓС‰РЅРѕСЃС‚Рё "С‚РёРї".
 */
@Entity
@Table(name = "CMT_TYPES")
//@AttributeOverride(name="id", column=@Column(unique = true, updatable = true, insertable = true))
public class ComfortType implements IComfortType {

    @Id
    private int id;

    @Column(name = "name")
    private String name;

    protected IComfortType setId(int id){
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public IComfortType fillName(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

}
