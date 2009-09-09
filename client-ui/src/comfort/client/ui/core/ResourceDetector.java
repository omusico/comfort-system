package comfort.client.ui.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: Michael Morozov
 * Date: 20.02.2008
 * Time: 23:22:45
 */

/**
 * РћРїСЂРµРґРµР»СЏРµС‚ СЃСЃС‹Р»РѕС‡РЅС‹Р№ РЅР° СЂРµСЃСѓСЂСЃ С‚РёРї СЃС‚СЂРѕРєРё Р·Р°РґР°РЅРЅС‹Р№ РІ С„РѕСЂРјР°С‚Рµ [{<РїР°РєРµС‚>.}[,...n]]<РёРјСЏ_РєР»Р°СЃСЃР°>
 */
public class ResourceDetector {
    public static boolean isResourceName(String s){
        return s.matches("([A-Za-z]+\\w)|([A-Za-z]+\\w(\\.[A-Za-z]+\\w)+)");
    }
}
