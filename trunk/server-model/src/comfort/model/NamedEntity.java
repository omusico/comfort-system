package comfort.model;

import javax.persistence.MappedSuperclass;
import javax.persistence.Column;

/**
 *
 * Author: Michael Morozov
 * Date: 26.11.2007
 * Time: 23:17:27
 *
 * Р‘Р°Р·РѕРІР°СЏ СЃСѓС‰РЅРѕСЃС‚СЊ РІСЃРµС… РёРјРµРЅРѕРІР°РЅРЅС‹С… СЃСѓС‰РЅРѕСЃС‚РµР№
 */

@MappedSuperclass
public class NamedEntity extends IdentitedEntity{
    @Column(length = 50)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public NamedEntity(int id, String name) {
        super(id);
        this.name = name;
    }
}
