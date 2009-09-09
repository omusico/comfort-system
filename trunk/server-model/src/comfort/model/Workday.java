package comfort.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Author: Michael Morozov
 * Date: 01.12.2007
 * Time: 9:39:33
 */
@Entity
public class Workday extends IdentitedEntity{
    @ManyToOne
    private User createdBy;
    private Date openTime;
    private Date closeTime;


    public Workday(int id, User createdBy, Date openTime, Date closeTime) {
        super(id);
        this.createdBy = createdBy;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }
}
