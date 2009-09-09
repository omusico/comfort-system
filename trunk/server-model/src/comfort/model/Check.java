package comfort.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Author: Michael Morozov
 * Date: 01.12.2007
 * Time: 13:32:23
 */
@Entity
@Table(name = "Checks")
public class Check extends IdentitedEntity{


    public Check(int id) {
        super(id);
    }
}
