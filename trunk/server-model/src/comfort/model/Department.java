package comfort.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Author: Michael Morozov
 * Date: 01.12.2007
 * Time: 12:11:09
 */
@Entity
@Table(name = "Departments")
public class Department extends NamedEntity{

    public Department(int id, String name) {
        super(id, name);
    }
}
