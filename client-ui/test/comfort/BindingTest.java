package comfort;

import junit.framework.TestCase;

import java.io.File;
import java.io.FilenameFilter;
import java.io.StringReader;

/**
 * Author: Michael Morozov
 * Date: 20.01.2008
 * Time: 17:33:04
 */
public class BindingTest extends TestCase {
    public void testReadingPage() {
        // РЎРѕР·РґР°РµРј Reader
        StringReader src = new StringReader(
                new StringBuilder().
                        append("<page name=\"test\">").
                        append("<views>").
                        append("<view name=\"menu\">").
                        append("<select name=\"name\">").
                        append("<property name=\"format\" value=\"%.2f\"/>").
                        append("</select>").
                        append("</view>").
                        append("</views>").
                        append("<targets>").
                        append("<target for=\"global\">").
                        append("<area name=\"myarea\" layout=\"center\">").
                        append("</area>").
                        append("</target>").
                        append("</targets>").
                        append("</page>").toString());
//        assertEquals(page.getTargets()[0].get(0).getAreas().get(0).get(0).getName(), "mysubarea");
//        assertEquals(page.getTargets()[0].get(0).getForArea(), "global");
//        assertNotNull((IView) page.getViews().get(0));
//        assertNotNull(((IView) page.getViews().get(0)).get(0));
//        assertEquals(((IView) page.getViews().get(0)).get(0).get(0).getValue(), "%.2f");
    }

}
