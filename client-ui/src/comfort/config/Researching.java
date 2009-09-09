package comfort.config;

import junit.framework.TestCase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Author: Michael Morozov
 * Date: 17.01.2008
 * Time: 20:33:00
 */
public class Researching extends TestCase {
    public Object monitor = new Object();

    public void testPanelSwitchComponents() {
//        BorderLayout layout = new BorderLayout();
        final JPanel mainArea = new JPanel(new BorderLayout());
        JPanel controlArea = new JPanel(new BorderLayout());
        final JPanel workArea = new JPanel(new BorderLayout());
// РџРµСЂРІР°СЏ РїР°РЅРµР»СЊ СЃ РєРѕРјРїРѕРЅРµРЅС‚Р°РјРё
        final JPanel componentsOne = new JPanel(new BorderLayout());
        componentsOne.add(new JButton("ONE"), BorderLayout.WEST);
        JLabel label1 = new JLabel("РџРµСЂРІР°СЏ РїР°РЅРµР»СЊ СЃ РєРѕРјРїРѕРЅРµРЅС‚Р°РјРё");
        label1.setBackground(Color.BLUE);
        componentsOne.add(label1, BorderLayout.CENTER);

// Р’С‚РѕСЂР°СЏ РїР°РЅРµР»СЊ СЃ РєРѕРјРїРѕРЅРµРЅС‚Р°РјРё
        final JPanel componentsTwo = new JPanel(new BorderLayout());
        componentsTwo.add(new JButton("TWO"), BorderLayout.WEST);
        JLabel label2 = new JLabel("Р­С‚Рѕ РІС‚РѕСЂР°СЏ РїР°РЅРµР»СЊ СЃ РєРѕРјРїРѕРЅРµРЅС‚Р°РјРё");
        label2.setBackground(Color.BLUE);
        label2.setOpaque(true);
        componentsTwo.add(label2, BorderLayout.CENTER);

        JButton switchButton = new JButton("switch");

        switchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (workArea.getComponents().length == 0 || workArea.getComponents()[0] == componentsTwo) {
                    workArea.removeAll();
                    workArea.add(componentsOne, BorderLayout.CENTER);
                } else {
                    workArea.removeAll();
                    workArea.add(componentsTwo, BorderLayout.CENTER);
                }

                workArea.paintAll(workArea.getGraphics());

            }
        });
        controlArea.add(switchButton, BorderLayout.EAST);
//        workArea.place(componentsOne, BorderLayout.CENTER);
        mainArea.add(controlArea, BorderLayout.NORTH);
        mainArea.add(workArea, BorderLayout.CENTER);
        JFrame frame = new JFrame("Test Switch");
        frame.setSize(300, 300);
        frame.add(mainArea);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                synchronized (monitor) {
                    monitor.notifyAll();
                }
            }
        });
        frame.setVisible(true);

        try {
            synchronized (monitor) {
                monitor.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
