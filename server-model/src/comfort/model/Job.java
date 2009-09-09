package comfort.model;

import javax.persistence.Table;
import javax.persistence.Entity;


/**
 *
 * Author: Michael Morozov
 * Date: 26.11.2007
 * Time: 20:37:27
 *
 */
@Entity
@Table(name = "Jobs")
public class Job extends NamedEntity{


    public Job(int id, String name) {
        super(id, name);
    }
}
