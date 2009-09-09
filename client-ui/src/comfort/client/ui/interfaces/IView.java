package comfort.client.ui.interfaces;


import interfaces.IEntity;

/**
 * Created by IntelliJ IDEA.
 * User: mormih
 * Date: 21.01.2008
 * Time: 16:55:28
 * To change this template use File | Settings | File Templates.
 */

/**
 * РџСЂРёР·РјР° СЃСѓС‰РЅРѕСЃС‚Рё. Р�СЃРїРѕР»СЊР·СѓРµС‚СЃСЏ РґР»СЏ РІС‹Р±РѕСЂРєРё, РїРµСЂРµС‡РёСЃР»РµРЅРёСЏ Рё РїСЂРµРѕР±СЂР°Р·РѕРІР°РЅРёСЏ СЃРІРѕР№СЃС‚РІ
 * СЃСѓС‰РЅРѕСЃС‚Рё {@link interfaces.IEntity} Р»РёР±Рѕ РІ СЃС‚СЂРѕРєСѓ, Р»РёР±Рѕ РІ РѕР±СЉРµРєС‚ РїРѕ РїСЂР°РІРёР»Р°Рј
 * Р·Р°РґР°РЅРЅС‹Рј С‡РµСЂРµР· РІ РІС‹Р±РѕСЂРєРµ{@link comfort.client.ui.interfaces.ISelect}
 */
public interface IView extends INamed, IGetter<ISelect> {
    /**
     * РџСЂРѕС†РµСЃСЃ РІС‹Р±РѕСЂРєРё Рё РїСЂРµРѕР±СЂР°Р·РѕРІР°РЅРёСЏ СЃРІРѕР№СЃС‚РІР° СЃСѓС‰РЅРѕСЃС‚Рё РІ СѓРЅРёРІРµСЂСЃР°Р»СЊРЅСѓСЋ С„РѕСЂРјСѓ С‚РёРїР° Object
     * @param entity СЃСѓС‰РЅРѕСЃС‚СЊ РґР»СЏ РїСЂРёР·РјС‹
     * @param select СЃРµР»РµРєС‚РѕСЂ СЃРІРѕР№СЃС‚РІР° СЃСѓС‰РЅРѕСЃС‚Рё {@link comfort.client.ui.interfaces.ISelect}
     * @param params РїР°СЂР°РјРµС‚СЂС‹ РґР»СЏ СЃРµР»РµРєС‚РѕСЂР°
     * @return РѕР±СЉРµРєС‚
     */
    Object getObject(IEntity entity, ISelect select, Object... params);

    /**
     * Р�С‰РµС‚ РІ СЃРµР»РµРєС‚РѕСЂРµ Р°С‚СЂРёР±СѓС‚С‹ С„РѕСЂРјР°С‚РёСЂРѕРІР°РЅРёСЏ. Р¤РѕСЂРјР°С‚РёСЂСѓРµС‚ СЃРІРѕР№СЃС‚РІРѕ СЃСѓС‰РЅРѕСЃС‚Рё
     * Рё РІРѕР·РІСЂР°С‰Р°РµС‚ СЃС‚СЂРѕРєСѓ
     * @param entity СЃСѓС‰РЅРѕСЃС‚СЊ РґР»СЏ РїСЂРёР·РјС‹
     * @param select СЃРµР»РµРєС‚РѕСЂ СЃРІРѕР№СЃС‚РІР° СЃСѓС‰РЅРѕСЃС‚Рё {@link comfort.client.ui.interfaces.ISelect}
     * @param params РїР°СЂР°РјРµС‚СЂС‹ РґР»СЏ СЃРµР»РµРєС‚РѕСЂР°
     * @return СЃС‚СЂРѕРєР°
     */
    String getString(IEntity entity, ISelect select, Object... params);
}
