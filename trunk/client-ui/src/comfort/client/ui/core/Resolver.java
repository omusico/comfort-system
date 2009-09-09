package comfort.client.ui.core;

import comfort.client.ui.interfaces.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Р Р°Р·СЂРµС€Р°С‚РµР»СЊ РёРјРµРЅ РІ РѕР±СЉРµРєС‚С‹. Р’С‹РїРѕР»РЅРµРЅ РІ РІРёРґРµ СЃРёРЅРіР»РµС‚РѕРЅР°.
 */
public class Resolver {
    private static Resolver instance;
    private Map<String, IView> viewMap;
    private Map<String, IType> typeMap;
    private Map<String, IComponent> componentMap;

    {
        instance = new Resolver();
    }


    public static synchronized Resolver getInstance() {
        return instance;
    }

    private Resolver() {
        viewMap = new HashMap<String, IView>();
        typeMap = new HashMap<String, IType>();
        componentMap = new HashMap<String, IComponent>();
    }

    /**
     * Р’С‹РїРѕР»РЅСЏРµС‚ СЂР°Р·СЂРµС€РµРЅРёРµ РёРјРµРЅРё РІ С‚РёРї РѕР±С‰РµРј РїСЂРѕСЃС‚СЂР°РЅС‚СЃРІРµ РёРјРµРЅ
     *
     * @param name
     * @return
     * @throws ResolveNameException
     *
     */

    public IType resolveType(String name) {
        return null;
    }

    public IType resolveType(String name, IPage page) {
        return null;
    }

    /**
     * Р Р°Р·СЂРµС€Р°РµС‚ РёРјСЏ РІ РїСЂРѕСЃРјРѕС‚СЂ СЃСѓС‰РЅРѕСЃС‚Рё
     *
     * @param name
     * @return
     * @throws ResolveNameException
     */
    public IView resolveView(String name) {
        return null;
    }

    /**
     * Р Р°Р·СЂРµС€Р°РµС‚ РёРјСЏ РІ РѕР±Р»Р°СЃС‚СЊ
     *
     * @param name
     * @return
     * @throws ResolveNameException
     */
    public IArea resolveArea(String name) {
        return null;
    }

    /**
     * Р”РѕР±Р°РІР»СЏРµС‚ СЃС‚СЂР°РЅРёС†Сѓ РІ РѕР±Р»Р°СЃС‚СЊ РІРёРґРёРјРѕСЃС‚Рё РґСЂСѓРіРёРј РїРѕСЃР»РµРґСѓС‰РёРј СЃС‚СЂР°РЅРёС†Р°Рј
     *
     * @param page
     */
    public void addPage(IPage page) {
        // Views
    }

    /**
     * РЈРґР°Р»РµРЅРёРµ СЃС‚СЂР°РЅРёС†С‹ Рё РµРµ РґР°РЅРЅС‹С… РёР· РѕР±Р»Р°СЃС‚Рё РІРёРґРёРјРѕСЃС‚Рё
     *
     * @param page
     */
    public void removePage(IPage page) {

    }

}
