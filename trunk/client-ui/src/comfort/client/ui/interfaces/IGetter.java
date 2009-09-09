package comfort.client.ui.interfaces;

/**
 * Author: Michael Morozov
 * Date: 28.01.2008
 * Time: 2:04:52
 */

/**
 * РћРїРёСЃС‹РІР°РµС‚ РѕР±СЉРµРєС‚, РєРѕС‚РѕСЂС‹Р№ РїРѕР·РІРѕР»СЏРµС‚ РѕР±СЂР°С‰Р°С‚СЊСЃСЏ Рє РІРЅСѓС‚СЂРµРЅРЅРёРј СЃРїРёСЃРєР°Рј Рё РєР°СЂС‚Р°Рј С‡РµСЂРµР·
 * РїСЂРѕСЃС‚РѕР№ РјРµС‚РѕРґ get.
 */
public interface IGetter<T> extends Iterable<T> {
    /**
     * РћР±СЂР°С‰РµРЅРёРµ РєР°Рє Рє СЃРїРёСЃРєСѓ
     * @param index РёРЅРґРµРєСЃ РІ СЃРїРёСЃРєРµ
     * @return
     */
    T get(int index);

    /**
     * РћР±СЂР°С‰РµРЅРёРµ РєР°Рє Рє map
     * @param name
     * @return
     */
    T get(String name);

    /**
     * РљРѕР»-РІРѕ РІ СЌР»РµРјРµРЅС‚РѕРІ РІ СЃРїРёСЃРєРµ
     * @return
     */
    int getCount();
}
