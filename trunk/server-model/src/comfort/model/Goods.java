package comfort.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToOne;

/**
 * Author: Michael Morozov
 * Date: 29.11.2007
 * Time: 20:42:35
 *
 * РЎСѓС‰РЅРѕСЃС‚СЊ С‚РѕРІР°СЂР°
 */
@Entity
@Table(name="Goods")
public class Goods extends NamedEntity{
   private double price;
   @ManyToOne
   private Department department;


    public Goods(int id, String name, double price, Department department) {
        super(id, name);
        this.price = price;
        this.department = department;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
