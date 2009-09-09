package comfort.client.ui.binding.adapters;

import comfort.client.ui.binding.ViewElement;
import comfort.client.ui.interfaces.IView;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class ViewAdapter extends XmlAdapter<ViewElement, IView> {
    public IView unmarshal(ViewElement v) throws Exception {
        return (IView) v;
    }

    public ViewElement marshal(IView v) throws Exception {
        return (ViewElement) v;
    }
}
