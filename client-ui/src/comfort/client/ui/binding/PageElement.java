package comfort.client.ui.binding;

import comfort.client.ui.interfaces.*;
import comfort.exceptions.ProgrammerError;
import comfort.exceptions.UnexpectedException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.Reader;

/**
 * Created by IntelliJ IDEA.
 * User: mormih
 * Date: 18.01.2008
 * Time: 18:03:32
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "page")
public class PageElement extends NamedElement implements IPage {
    @XmlTransient
    @XmlElementWrapper(name = "views")
    @XmlElement(name = "view")
    private IView[] views;

    @XmlElementWrapper(name = "types")
    @XmlElement(name = "type")
    private IType[] types;

    @XmlElementWrapper(name = "targets")
    @XmlElement(name = "target")
    private ITarget[] targets;

    @XmlAttribute(name="controller")
    private String _controller;

    @XmlTransient
    private IPageController controller;
    @XmlTransient
    private ClassLoader classLoader;

    public IPageController getController() {
        if (controller == null)
            try {
                Class clazz = null;
                if (classLoader != null)
                    clazz = classLoader.loadClass(_controller);
                else
                    clazz = Class.forName(_controller);
                
                if (clazz.isAssignableFrom(IPageController.class)) {
                    controller = (IPageController) clazz.newInstance();
                }
            } catch (ClassNotFoundException e) {
                ProgrammerError.classNotFound(_controller);
            } catch (IllegalAccessException e) {
                ProgrammerError.classNotFound(_controller);
            } catch (InstantiationException e) {
                ProgrammerError.classNotFound(_controller);
            }
        return controller;
    }

    public IView[] getViews() {
        return views;
    }

    public IType[] getTypes() {
        return types;
    }

    public ITarget[] getTargets() {
        return targets;
    }

    public void validate() {
        super.validate();
        
        getController();
        for (IView view: views){
            view.validate();
        }
        for (ITarget target: targets){
            target.validate();
        }
        for (IView view: views){
            view.validate();
        }
    }

    public void setClassLoader(ClassLoader classLoader){
        this.classLoader = classLoader;
    }

    public static IPage create(Reader reader, ClassLoader classLoader){
        try {
            JAXBContext jaxbContext
                    = JAXBContext.newInstance
                    (PageElement.class);
            Unmarshaller um = jaxbContext.createUnmarshaller();
            final ClassLoader cl = classLoader;
            // Р”Рѕ РЅР°С‡Р°Р»Р° РїСЂРѕС†РµСЃСЃР° Р°РЅРјР°СЂС€Р°Р»РёРЅРіР° СѓСЃС‚Р°РЅРѕРІРёРј РЅРµРєРѕС‚СЂС‹Рµ СЃРІРѕР№СЃС‚РІР° РґР»СЏ PageElement
            um.setListener(new Unmarshaller.Listener(){
                public void beforeUnmarshal(Object target, Object parent) {
                    // РЈСЃС‚Р°РЅР°РІР»РёРІР°РµРј Р·Р°РіСЂСѓР·С‡РёРє РєР»Р°СЃСЃРѕРІ С‡С‚РѕР±С‹ РјРѕР¶РЅРѕ Р±С‹Р»Рѕ РЅР°Р№С‚Рё РєРѕРЅС‚СЂРѕР»Р»РµСЂ СЃС‚СЂР°РЅРёС†С‹
                    if (parent instanceof PageElement)
                        ((PageElement) parent).setClassLoader(cl);
                }
            });
            // Р’С‹РїРѕР»РЅСЏРµРј Р°РЅРјР°СЂС€Р°Р»РёРЅРі Рё РІРѕР·РІСЂР°С‰Р°РµРј СЂРµР·СѓР»СЊС‚Р°С‚
            return (IPage) um.unmarshal(reader);
        } catch (JAXBException e) {
            throw UnexpectedException.exception(e);
        }
    }
}
