package comfort.run;

import comfort.client.ui.components.EntityGrid;
import interfaces.IEntity;

import javax.swing.*;
import javax.swing.plaf.synth.SynthLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: РњРёС€Р°
 * Date: 10.11.2007
 * Time: 13:19:29
 * To change this template use File | Settings | File Templates.
 */
public class Application {

    private static void refreshLAF(JFrame frame) {
        SynthLookAndFeel lookAndFeel = new SynthLookAndFeel();


        try {
            lookAndFeel.load(Application.class.getResourceAsStream("skin1.xml"),
                    Application.class);
            UIManager.setLookAndFeel(lookAndFeel);
            if (frame != null)
                SwingUtilities.updateComponentTreeUI(frame);
        }

        catch (Exception e) {
            System.err.println("Couldn't get specified look and feel ("
                    + lookAndFeel
                    + "), for some reason.");
            System.err.println("Using the default look and feel.");
            e.printStackTrace();
        }
        UIManager.getDefaults().put("Table.evenRowBackground", new Color(253, 245, 215));
        UIManager.getDefaults().put("Table.evenRowForeground", Color.BLACK);
        UIManager.getDefaults().put("Table.oddRowBackground", new Color(252, 241, 197));
        UIManager.getDefaults().put("Table.oddRowForeground", Color.BLACK);
//        System.gc();

    }

    public static void main(String[] args) {
    }
}
