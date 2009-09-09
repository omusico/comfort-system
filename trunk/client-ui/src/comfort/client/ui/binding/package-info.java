@XmlJavaTypeAdapters(value = {
    @XmlJavaTypeAdapter(type = IView.class, value = ViewAdapter.class),
    @XmlJavaTypeAdapter(type = ITarget.class, value = TargetAdapter.class),
    @XmlJavaTypeAdapter(type = IType.class, value = TypeAdapter.class)
})
package comfort.client.ui.binding;

import comfort.client.ui.binding.adapters.TargetAdapter;
import comfort.client.ui.binding.adapters.TypeAdapter;
import comfort.client.ui.binding.adapters.ViewAdapter;
import comfort.client.ui.interfaces.ITarget;
import comfort.client.ui.interfaces.IType;
import comfort.client.ui.interfaces.IView;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
