package comfort.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Author: Michael Morozov
 * Date: 01.12.2007
 * Time: 10:02:38
 */
@Entity
@Table(name = "Stations")
public class Station extends NamedEntity{
    private String address;


    public Station(int id, String name, String address) {
        super(id, name);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
