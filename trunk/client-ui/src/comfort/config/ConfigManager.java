package comfort.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Author: Michael Morozov
 * Date: 20.12.2007
 * Time: 3:08:22
 */
public class ConfigManager {
    private static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.loadFromXML(new FileInputStream(getConfigsPath() + File.separator + "UI_default.xml"));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private ConfigManager() {
    }

    public static String getValue(ConfigCodes code) {


        Object value = (properties != null) ? properties.get(code.getCode()) : null;
        if (value == null)
            return code.getValue();
        else
            throw new RuntimeException(code.getCode());
    }

    public static String getExternalPath() {
        File file = new File("");
        return file.getAbsolutePath() + File.separator;
    }

    public static String getConfigsPath() {
        return getExternalPath() + "configs" + File.separator;
    }

    public static String getBooksPath() {
        return getExternalPath() + "books" + File.separator;
    }


}
