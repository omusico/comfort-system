package comfort.client.ui.core;

/**
 * Author: Michael Morozov
 * Date: 23.01.2008
 * Time: 23:09:28
 */

/**
 * РћС€РёР±РєР° РїСЂРё СЂР°Р·СЂРµС€РµРЅРёРё РёРјРµРЅ
 */
public class ResolveNameException extends Error {
    public ResolveNameException(String name, String type) {
        super(String.format("Can't resolve %s name %s", type, name));
    }
}
