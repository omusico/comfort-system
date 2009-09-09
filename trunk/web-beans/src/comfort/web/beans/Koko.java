package comfort.web.beans;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.In;

@Name("koko")
public class Koko {
    @In
    private String txt;
    public void doIt(){
        System.out.println("txt = " + txt);
    }

}
