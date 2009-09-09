package comfort.client.ui.interfaces;

/**
 * Author: Michael Morozov
 * Date: 07.02.2008
 * Time: 0:55:35
 */

/**
 * Р¦РµР»СЊ. РћРїРёСЃС‹РІР°РµС‚ РїСЂРёРІСЏР·РєСѓ РѕР±Р»Р°СЃС‚Рё РєРѕРЅС‚РµР№РЅРµСЂР° {@link comfort.client.ui.interfaces.IArea}
 * СЃ РєРѕРјРїРѕРЅРµРЅС‚Р°РјРё {@link comfort.client.ui.interfaces.IComponent} Рё РІРЅСѓС‚СЂРµРЅРЅРёРјРё РѕР±Р»Р°СЃС‚СЏРјРё
 */
public interface ITarget extends INamed, IGetter<IComponent>{
    /**
     * РћРїРёСЃС‹РІР°РµС‚ РґР»СЏ РєР°РєРѕР№ РѕР±Р»Р°СЃС‚Рё Р±СѓРґРµС‚
     * РїСЂРѕРёСЃС…РѕРґРёС‚СЊ РїСЂРёРІСЏР·РєР° РєРѕРјРїРѕРЅРµРЅС‚РѕРІ
     * @return
     */
    IArea getFor();
}
