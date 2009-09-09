package comfort.client.ui.core;

import comfort.exceptions.ProgrammerError;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

/**
 * Author: Michael Morozov
 * Date: 21.02.2008
 * Time: 22:34:43
 */

/**
 * РќР°Р±РѕСЂ СѓС‚РёР»РёС‚ РїРѕР·РІРѕР»СЏСЋС‰РёС… СѓРїСЂРѕСЃС‚РёС‚СЊ СЂР°Р±РѕС‚Сѓ СЃ СЂРµС„Р»РµРєСЃРёРµР№
 */
public class ReflectionUtils {
    private static Object toValue(String s, Class c){
        if (c == Integer.class || c == int.class)
            return new Integer(s);
        else if (c == BigDecimal.class)
            return new BigDecimal(s);
        else if (c == Double.class || c == Float.class || c == double.class)
            return new Double(s);
        else if (c == String.class)
            return s;
        else if (s == "null")
            return null;
        else
            throw ProgrammerError.notSupported("converting value from String for class", c.getName());
        
    }

    public static String firstUpperName(String name){
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public static void setProperty(String propertyName, String value, Object obj){
        Class clazz = obj.getClass();
        Class[] params = null;
        String setter = "set" + firstUpperName(propertyName);
        for (Method m: clazz.getMethods()){
            if (setter.equals(m.getName())){
                params = m.getParameterTypes();
                if (params.length == 1)
                    try {
                        m.invoke(obj, toValue(value, params[0]));
                        break;
                    } catch (Exception e) {
                        throw ProgrammerError.general("Can't set property " + propertyName, e);
                    }

            }
        }

    }
}
