package comfort.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Author: Michael Morozov
 * Date: 01.12.2007
 * Time: 11:40:41
 */
@Entity
@Table(name = "PayTypes")
public class PayType extends NamedEntity{
    private double rate;

    public PayType(int id, String name, double rate) {
        super(id, name);
        this.rate = rate;
    }
}
