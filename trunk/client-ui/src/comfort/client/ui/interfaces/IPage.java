package comfort.client.ui.interfaces;

/**
 * Created by IntelliJ IDEA.
 * User: mormih
 * Date: 21.01.2008
 * Time: 16:55:54
 * To change this template use File | Settings | File Templates.
 */

/**
 * IPage РѕРїРёСЃС‹РІР°РµС‚ СЃРµРјР°РЅС‚РёС‡РµРєСѓСЋ РµРґРёРЅРёС†Сѓ СЃС‚СЂР°РЅРёС†Сѓ РІ РєРѕРЅС†РµРїС†РёРё "РєРЅРёРіР°".
 * РЎС‚СЂР°РЅРёС†Р° СЏРІР»СЏРµС‚СЃСЏ РєРѕРЅС‚РµР№РЅРµСЂРѕРј РґР»СЏ РґР°РЅРЅС‹С… РїСЂРё СЃРѕР·РґР°РЅРёРё UI
 */
public interface IPage extends INamed{
    /**
     * РџСЂРёР·РјС‹ РѕР±СЉСЏРІР»РµРЅРЅС‹Рµ РЅР° СЃС‚СЂР°РЅРёС†Рµ
     * @return
     */
    IView[] getViews();

    /**
     * РЁР°Р±Р»РѕРЅС‹ РѕР±СЉСЏРІР»РµРЅРЅС‹Рµ РЅР° СЃС‚СЂР°РЅРёС†Рµ
     * @return
     */
    IType[] getTypes();

    /**
     * 
     * @return
     */
    ITarget[] getTargets();
    IPageController getController();
    
}
