package comfort.client.ui.interfaces;

/**
 * Author: Michael Morozov
 * Date: 12.01.2008
 * Time: 21:29:22
 */

/**
 * РљРѕРјРїРѕРЅРµРЅС‚.
 */
public interface IComponent extends INamed, IGetter<IComponent>, IPlacable {
    /**
     * РЁР°Р±Р»РѕРЅ РєРѕРјРїРѕРЅРµРЅС‚Р°
     * @return
     */
    IType getType();
}
