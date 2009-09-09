package comfort.persistence.entitygenerator;

import org.junit.Test;
import org.junit.Before;
import javassist.*;
import javassist.expr.ExprEditor;

import java.lang.reflect.InvocationTargetException;

/**
 *
 * Date: 05.06.2009
 * Time: 0:50:41
 * @author Michael Morozov
 */

public class GeneratorTest {

    @Test
    public void generateTestClass() throws Exception{
        ClassPool pool = ClassPool.getDefault();
        String className = "Device";
        CtClass ct = pool.makeClass("comfort.persistence.hibernate.runtime." + className);
        CtMethod ctMethod = CtNewMethod.make("@SuppressWarnings(\"\") public void show() {System.out.println(\"Hello\");}", ct);
        ct.addMethod(ctMethod);
        ct.
        Class c = ct.toClass();
        c.getMethod("show").invoke(c.newInstance());
    }

}
