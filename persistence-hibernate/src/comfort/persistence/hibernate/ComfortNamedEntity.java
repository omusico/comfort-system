package comfort.persistence.hibernate;

import comfort.persistence.front.INamedEntity;
import comfort.persistence.front.DAO;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Реализация "именованной сущности".
 */
@MappedSuperclass
public abstract class ComfortNamedEntity<T extends INamedEntity> extends ComfortEntity implements INamedEntity<T> {
    @Column(name = "NAME")
    private String name;

    public String getName() {
        return name;
    }    

    public T fillName(String name) {
        this.name = name;
        return (T) this;
    }

    public void setName(String name) {
        this.name = name;
    }
}
