package comfort.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToOne;

/**
 * Author: Michael Morozov
 * Date: 01.12.2007
 * Time: 0:18:13
 *
 * РЎСѓС‰РЅРѕСЃС‚СЊ РїСЂРёРІСЏР·РєРё РјРµРЅСЋ Рё С‚РѕРІР°СЂР°
 */
@Entity
@Table(name = "GoodsMenuLinker")
public class GoodsMenuLinker extends IdentitedEntity{

    @ManyToOne
    private Goods goods;
    @ManyToOne
    private GoodsMenu goodsMenu;


    public GoodsMenuLinker(int id, Goods goods, GoodsMenu goodsMenu) {
        super(id);
        this.goods = goods;
        this.goodsMenu = goodsMenu;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public GoodsMenu getGoodsMenu() {
        return goodsMenu;
    }

    public void setGoodsMenu(GoodsMenu goodsMenu) {
        this.goodsMenu = goodsMenu;
    }
}
