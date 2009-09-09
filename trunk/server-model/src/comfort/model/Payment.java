package comfort.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToOne;

/**
 * Author: Michael Morozov
 * Date: 01.12.2007
 * Time: 11:40:02
 */
@Entity
@Table(name = "Payments")
public class Payment {
    @ManyToOne
    private PayType payType;
    private double amount;
    @ManyToOne
    private Document document;
}
