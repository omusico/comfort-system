package comfort.client.ui.interfaces;

/**
 * Author: Michael Morozov
 * Date: 23.01.2008
 * Time: 22:48:19
 */

/**
 * РљРѕРЅС‚СЂРѕР»Р»РµСЂ СЃС‚СЂР°РЅРёС†С‹
 */
public interface IPageController {
    /**
     * Р”РµР№СЃС‚РІРёСЏ РїСЂРё Р°РєС‚РёРІР°С†РёРё СЃС‚СЂР°РЅРёС†С‹
     * @param page
     */
    void activate(IPage page);

    /**
     * Р”РµР№СЃС‚РІРёСЏ РїСЂРё РґРµР°РєС‚РёРІР°С†РёРё СЃС‚СЂР°РЅРёС†С‹
     * @param page
     */

    void deactivate(IPage page);

    /**
     * Р”РµР№СЃС‚РІРёСЏ РІРѕ РІСЂРµРјСЏ РїРѕРєР°Р·Р° СЃС‚СЂР°РЅРёС†С‹
     * @param page
     */
    void showing(IPage page);
}
