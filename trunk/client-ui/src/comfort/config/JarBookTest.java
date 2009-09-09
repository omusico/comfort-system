package comfort.config;

import comfort.client.ui.interfaces.IPageController;
import junit.framework.TestCase;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by IntelliJ IDEA.
 * User: mormih
 * Date: 11.01.2008
 * Time: 15:27:13
 * To change this template use File | Settings | File Templates.
 */
public class JarBookTest extends TestCase {
    public void testLoadingPageController() throws Exception {
        URL url = new File(ConfigManager.getBooksPath() + "retail-book.jar").toURI().toURL();
        URLClassLoader loader = new URLClassLoader(new URL[]{url});
        Object controller = loader.loadClass("controllers.MainPageController").newInstance();
        assertNotNull(controller);
        assertTrue(controller instanceof IPageController);
    }

    public void testLoadingPageDefinition() throws Exception {
        URL url = new File(ConfigManager.getBooksPath() + "retail-book.jar").toURI().toURL();
        JarFile jar = new JarFile(url.getFile());
        Enumeration<JarEntry> entries = jar.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            System.out.println(entry.getName());
        }

    }
}
