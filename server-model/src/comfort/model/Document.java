package comfort.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Author: Michael Morozov
 * Date: 01.12.2007
 * Time: 9:35:51
 */

@Entity
@Table(name = "Documents")
public class Document extends IdentitedEntity {
    @ManyToOne
    // Р�РЅС„РѕСЂРјР°С†РёСЏ Рѕ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏС…
    private User createdBy;
    private Date createTime;
    private User servedBy;
    // Р‘Р»РѕРєРёСЂРѕРІРєРё
    private Date lockedTime;
    private User lockedBy;
    private Station lockedOn;
    // Р‘РёР·РЅРµСЃ РґР°РЅРЅС‹Рµ
    private double amount;
    private double discount;
    private boolean payed;
    private boolean modified;


    public Document(int id, User createdBy, Date createTime, User servedBy, Date lockedTime, User lockedBy, Station lockedOn, double amount, double discount, boolean payed, boolean modified) {
        super(id);
        this.createdBy = createdBy;
        this.createTime = createTime;
        this.servedBy = servedBy;
        this.lockedTime = lockedTime;
        this.lockedBy = lockedBy;
        this.lockedOn = lockedOn;
        this.amount = amount;
        this.discount = discount;
        this.payed = payed;
        this.modified = modified;
    }
}
