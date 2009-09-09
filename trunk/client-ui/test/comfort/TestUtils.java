package comfort;

import comfort.client.ui.core.ReflectionUtils;
import comfort.client.ui.core.ResourceDetector;
import comfort.exceptions.ProgrammerError;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Author: Michael Morozov
 * Date: 21.02.2008
 * Time: 0:30:16
 */
public class TestUtils {
    public void testResourceDetector(){
        String resource1 = "group.name";
        String resource2 = "group";
        String resource3 = "1jkjsd";
        String resource4 = "test.res.format.type";
        String resource5 = "test.";

        Assert.assertTrue(ResourceDetector.isResourceName(resource1));
        Assert.assertTrue(ResourceDetector.isResourceName(resource2));
        Assert.assertFalse(ResourceDetector.isResourceName(resource3));
        Assert.assertTrue(ResourceDetector.isResourceName(resource4));
        Assert.assertFalse(ResourceDetector.isResourceName(resource5));

    }

    public void testReflection(){
        ForTestReflection obj = new ForTestReflection();
        ReflectionUtils.setProperty("myProp", "56", obj);
        ReflectionUtils.setProperty("str", "РџСЂРµРІРµРґ", obj);
        ReflectionUtils.setProperty("dbl", "60.89", obj);
        Assert.assertEquals(obj.getDbl(), 60.89, 0);
        Assert.assertEquals(obj.getMyProp(), 56);
        Assert.assertEquals(obj.getStr(), "РџСЂРµРІРµРґ");
        try{
            ReflectionUtils.setProperty("date", "10/10/2008", obj);
            Assert.fail(); 
        }
        catch (ProgrammerError e){
        }
    }
    @Test
    public void main(){
        testResourceDetector();
        testReflection();
    }
}
