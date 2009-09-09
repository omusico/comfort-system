package comfort.client.ui.binding;

import comfort.exceptions.ProgrammerError;

import java.awt.*;

/**
 * Author: Michael Morozov
 * Date: 08.02.2008
 * Time: 23:11:02
 */
public class PlacableUtil {

    public static String getLayout(String layout) {
        if (layout == "center"){
            return BorderLayout.CENTER;
        } else if (layout == "left"){
            return BorderLayout.WEST;
        } else if (layout == "right"){
            return BorderLayout.EAST;
        } else if (layout == "top"){
            return BorderLayout.NORTH;
        } else if (layout == "bottom"){
            return BorderLayout.SOUTH;
        }
        return null;
    }

    public static Dimension getSize(String size) {
        if (size != null){
            String[] hw = size.split(",");
            try {
                return new Dimension(Integer.valueOf(hw[0]), Integer.valueOf(hw[1]));
            } catch (Exception e) {
                throw ProgrammerError.general("size syntax is <width, height>", e);
            }
        }
        return null;
    }

}
