package comfort.config;

import comfort.client.ui.binding.PageElement;
import comfort.client.ui.interfaces.IPage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.io.Reader;

/**
 * Author: Michael Morozov
 * Date: 05.01.2008
 * Time: 11:35:56
 */
public class UIFactory {

    public static PageElement readPage(InputStream src) {
        try {
            JAXBContext jaxbContext
                    = JAXBContext.newInstance
                    (PageElement.class);
            Unmarshaller unmarsh = jaxbContext.createUnmarshaller();
            return (PageElement) unmarsh.unmarshal(src);
        } catch (JAXBException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    ;

    public static PageElement readPage(Reader src) {
        try {
            JAXBContext jaxbContext
                    = JAXBContext.newInstance
                    (PageElement.class);
            Unmarshaller unmarsh = jaxbContext.createUnmarshaller();
            return (PageElement) unmarsh.unmarshal(src);
        } catch (JAXBException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    ;

    public static IPage createPage(String pageName) {
//        try {
//            JAXBContext jaxbContext
//                    = JAXBContext.newInstance
//                    (Page.class);
//            Unmarshaller unmarsh = jaxbContext.createUnmarshaller();
//            return (Page) unmarsh.unmarshal(new File(pageName));
//        } catch (JAXBException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
        return null;
    }
}
