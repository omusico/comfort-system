package comfort.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Author: Michael Morozov
 * Date: 01.12.2007
 * Time: 13:34:15
 */
@Entity
@Table(name = "Shifts")
public class Shift extends IdentitedEntity{
    private Date openTime;
    private Date closeTime;
    private User openedBy;
    private User closedBy;
    private Station station;

    public Shift(int id, Date openTime, Date closeTime, User openedBy, User closedBy, Station station) {
        super(id);
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.openedBy = openedBy;
        this.closedBy = closedBy;
        this.station = station;
    }
}
