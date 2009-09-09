package comfort.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

/**
 * РњРµРЅСЋ
 * Р�СЃРїРѕР»СЊР·СѓРµС‚СЃСЏ РјРЅРѕР¶РµСЃС‚РІРµРЅРЅР°СЏ РјРѕРґРµР»СЊ РґРµСЂРµРІР°
 */
@Entity
@Table(name="GoodsMenu")
public class GoodsMenu extends NamedEntity{
    /**
     * Р�РЅРґРµРєСЃ РїРѕ РїРѕСЂСЏРґРєСѓ РґР»СЏ РјРµРЅСЋ
     */
    private int menuIndex;
    /**
     * РќР°С‡Р°Р»СЊРЅРѕРµ Р·РЅР°С‡РµРЅРёРµ РґРёР°РїРѕР·РѕРЅР°
     */
    private int start;
    /**
     * РљРѕРЅРµС‡РЅРѕРµ Р·РЅР°С‡РµРЅРёРµ РґРёР°РїРѕР·РѕРЅР°
     */
    @Column(name = "[end]")
    private int end;


    public GoodsMenu(int id, String name, int menuIndex, int start, int end) {
        super(id, name);
        this.menuIndex = menuIndex;
        this.start = start;
        this.end = end;
    }
}
