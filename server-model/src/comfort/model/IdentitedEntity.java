package comfort.model;

import javax.persistence.MappedSuperclass;
import javax.persistence.Id;
import java.io.Serializable;

/**
 *
 * Author: Michael Morozov
 * Date: 26.11.2007
 * Time: 23:15:21
 * 
 */
@MappedSuperclass
public class IdentitedEntity implements Serializable {
    @Id
    private int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public IdentitedEntity(int id) {
        this.id = id;
    }
}
