package comfort.client.ui.binding.adapters;

import comfort.client.ui.binding.TypeElement;
import comfort.client.ui.interfaces.IType;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class TypeAdapter extends XmlAdapter<TypeElement, IType> {
    public IType unmarshal(TypeElement v) throws Exception {
        return (IType) v;
    }

    public TypeElement marshal(IType v) throws Exception {
        return (TypeElement) v;
    }
}
