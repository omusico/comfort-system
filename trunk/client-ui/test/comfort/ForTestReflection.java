package comfort;

import java.util.Date;

/**
 * Author: Michael Morozov
 * Date: 22.02.2008
 * Time: 0:18:07
 */
public class ForTestReflection {
    private int i;
    private String s;
    private Date d;
    private double dbl;

    public double getDbl() {
        return dbl;
    }

    public void setDbl(double dbl) {
        this.dbl = dbl;
    }

    public String getStr() {
        return s;
    }

    public void setStr(String s) {
        this.s = s;
    }

    public int getMyProp(){
        return i;
    }
    public void setMyProp(int i){
        this.i = i;
    }

    public void setDate(Date d) {
        this.d = d;
    }
    
}
