package comfort.config;

import junit.framework.TestCase;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: mormih
 * Date: 09.01.2008
 * Time: 12:45:11
 * To change this template use File | Settings | File Templates.
 */
public class ConfigTest extends TestCase {
    public void testDirectoriesAccess() {
        File config = new File(ConfigManager.getExternalPath() + "config");
        System.out.println(config.getAbsolutePath());
        File books = new File(ConfigManager.getExternalPath() + "books");
        System.out.println(books.getAbsolutePath());

    }

}
