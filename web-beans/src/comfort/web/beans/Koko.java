package comfort.web.beans;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.In;

/**
 * Created by IntelliJ IDEA.
 * User: Миша
 * Date: 08.04.2009
 * Time: 2:17:55
 * To change this template use File | Settings | File Templates.
 */
@Name("koko")
public class Koko {
    @In
    private String txt;
    public void doIt(){
        System.out.println("txt = " + txt);
    }

}
