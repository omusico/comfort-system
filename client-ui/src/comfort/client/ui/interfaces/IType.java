package comfort.client.ui.interfaces;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: mormih
 * Date: 21.01.2008
 * Time: 16:55:12
 * To change this template use File | Settings | File Templates.
 */

/**
 * РЁР°Р±Р»РѕРЅ РґР»СЏ РєРѕРјРїРѕРЅРµРЅС‚РѕРІ {@link comfort.client.ui.interfaces.IComponent}.
 * РћРїРёСЃС‹РІР°РµС‚ РЅР°СЃС‚СЂРѕР№РєРё С‡РµСЂРµР· СЃРІРѕР№СЃС‚РІР° {@link comfort.client.ui.interfaces.IProperty}
 * РєРѕРјРїРѕРЅРµРЅС‚РѕРІ, Рё РёС… java РєР»Р°СЃСЃ
 */
public interface IType extends INamed, IGetter<IProperty> {
    /**
     * Java РєР»Р°СЃСЃ РґР»СЏ РїРѕР»СѓС‡РµРЅРёСЏ СЌРєР·РµРјРїР»СЏСЂР° РєРѕРјРїРѕРЅРµРЅС‚Р° 
     * @return
     */
    Class getJavaClass();
}
