package comfort.persistence.hibernate;

import comfort.persistence.front.IEntity;
import comfort.persistence.front.DAO;

import javax.persistence.*;

/**
 * Реализация "базовой сущности" с генерацией ID.
 */
@MappedSuperclass
public abstract class ComfortEntity implements IEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    protected int id;

    public int getId() {
        return this.id;
    }



}
