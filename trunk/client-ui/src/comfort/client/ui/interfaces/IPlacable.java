package comfort.client.ui.interfaces;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Michael Morozov
 * Date: 28.01.2008
 * Time: 23:57:52
 */

/**
 * Р­С‚РёРј РёРЅС‚РµСЂС„РµР№СЃРѕРј РїРѕРјРµС‡Р°СЋС‚СЃСЏ С‚Рµ РєР°Р»Р°СЃСЃС‹, РєРѕС‚РѕСЂС‹Рµ СЃРѕРґРµСЂР¶Р°С‚ UI СЃРѕРґРµСЂР¶РёРјРѕРµ,
 * РЅР°РїСЂРёРјРµСЂ РєРѕРјРїРѕРЅРµРЅС‚С‹ {@link comfort.client.ui.interfaces.IComponent}
 * РёР»Рё РѕР±Р»Р°СЃС‚Рё {@link comfort.client.ui.interfaces.IArea}
 */

public interface IPlacable {
    /**
     * Layout РѕРіСЂР°РЅРёС‡РµРЅРёРµ РїСЂРёРјРµРЅСЏРµРјРѕРµ РїСЂРё С„РѕСЂРјРёСЂРѕРІР°РЅРёРё UI
     * @return
     */
    String getLayout();

    /**
     * Р Р°Р·РјРµСЂС‹
     * @return
     */
    Dimension getSize();

    /**
     * Swing РєРѕРјРїРѕРЅРµРЅС‚
     * @return
     */
    JComponent getJComponent();
}
