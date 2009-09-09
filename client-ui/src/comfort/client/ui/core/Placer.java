package comfort.client.ui.core;

import comfort.client.ui.interfaces.IPlacable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Placer СѓРїСЂР°РІР»СЏРµС‚ СЂР°Р·РјРµС‰РµРЅРёРµРј РєРѕРјРїРѕРЅРµРЅС‚РѕРІ РјРµР¶РґСѓ СЃРѕР±РѕР№ Рё
 * СЂРµР°Р»РёР·СѓРµС‚ РѕС‡РµСЂРµРґСЊ СЂР°Р·РјРµС‰Р°РµРјС‹С… РєРѕРјРїРѕРЅРµРЅС‚РѕРІ. РЎРёРЅРіР»РµС‚РѕРЅ.
 */
public class Placer {
    private static Placer instance = new Placer();
    Map<IPlacable, LinkedList<IPlacable>> history;

    private Placer() {
        this.history = new HashMap<IPlacable, LinkedList<IPlacable>>();
    }

    public static Placer getInstance(){
        return instance;
    }

    /**
     * Р Р°Р·РјРµС‰Р°РµС‚ РєРѕРјРїРѕРЅРµРЅС‚ РІРЅСѓС‚СЂРё РґСЂСѓРіРѕРіРѕ РєРѕРјРїРѕРЅРµРЅС‚Р°-РєРѕРЅС‚РµР№РЅРµСЂР°, СѓРґР°Р»СЏРµС‚ РёР· РєРѕРЅС‚РµР№РЅРµСЂР°
     * РґСЂСѓРіРёРµ РєРѕРјРїРѕРЅРµРЅС‚С‹ СЃРѕС…СЂР°РЅСЏСЏ РёС… СЃСЃС‹Р»РєРё РІ РѕС‡РµСЂРµРґРё. 
     * @param what Р§С‚Рѕ СЂР°Р·РјРµСЃС‚РёС‚СЊ
     * @param where Р“РґРµ СЂР°Р·РјРµСЃС‚РёС‚СЊ (РєРѕРЅС‚РµР№РЅРµСЂ)
     */
    public void place(IPlacable what, IPlacable where) {
        LinkedList<IPlacable> queue = history.get(where);
        if (queue == null) {
            queue = new LinkedList<IPlacable>();
            history.put(where, queue);
        }
        queue.offerFirst(what);
        where.getJComponent().add(what.getJComponent(), what.getLayout());
        where.getJComponent().removeAll();
        where.getJComponent().paintAll(where.getJComponent().getGraphics());
    }

    /**
     * РћРїРµСЂР°С†РёСЏ РїРѕ СѓРґР°Р»РµРЅРёСЋ РєРѕРјРїРѕРЅРµРЅС‚Р° РёР· РєРѕРЅС‚РµР№РЅРµСЂР°, РїСЂРёС‡РµРј РїРѕСЃР»Рµ СѓРґР°Р»РµРЅРёСЏ
     * РІ РєРѕРЅС‚РµР№РЅРµСЂ РјРѕР¶РµС‚ СЂР°Р·РјРµСЃС‚РёС‚СЊСЃСЏ РґСЂСѓРіРѕР№ РєРѕРјРїРѕРЅРµРЅС‚ РёР· РѕС‡РµСЂРµРґРё, РєРѕС‚РѕСЂС‹Р№ СЂР°РЅРµРµ Р±С‹Р»
     * СЂР°Р·РјРµС‰РµРЅ РІ РґР°РЅРЅРѕРј РєРѕРЅС‚РµР№РЅРµСЂРµ
     * @param what Р§С‚Рѕ СѓРґР°Р»РёС‚СЊ
     * @param where РћС‚РєСѓРґР° СѓРґР°Р»РёС‚СЊ
     */
    public void remove(IPlacable what, IPlacable where) {
        LinkedList<IPlacable> queue = history.get(where);
        if (queue != null) {
            queue.remove(what);
            where.getJComponent().removeAll();
            IPlacable next = queue.peekFirst();
            if (next != null) {
                where.getJComponent().add(next.getJComponent(), next.getLayout());
                where.getJComponent().paintAll(where.getJComponent().getGraphics());
            }
        }

    }
}
