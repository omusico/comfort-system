package comfort.client.ui.binding.adapters;

import comfort.client.ui.binding.TargetElement;
import comfort.client.ui.interfaces.ITarget;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class TargetAdapter extends XmlAdapter<TargetElement, ITarget> {
    public ITarget unmarshal(TargetElement v) throws Exception {
        return (ITarget) v;
    }

    public TargetElement marshal(ITarget v) throws Exception {
        return (TargetElement) v;
    }
}
