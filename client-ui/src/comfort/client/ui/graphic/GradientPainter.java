package comfort.client.ui.graphic;

import javax.swing.plaf.synth.SynthContext;
import javax.swing.plaf.synth.SynthPainter;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Author: Michael Morozov
 * Date: 16.12.2007
 * Time: 17:28:25
 */
public class GradientPainter extends SynthPainter {
    private GradientPaint paint;
    private Color color1;
    private Color color2;
    private Color borderColor;

    public GradientPainter(Color color1, Color color2, Color borderColor) {
        this.color1 = color1;
        this.color2 = color2;
        this.borderColor = borderColor;
    }

    public void paintButtonBackground(SynthContext context, Graphics g, int x, int y, int w, int h) {
        Graphics2D g2 = (Graphics2D) g;
        if (paint == null)
            paint = new GradientPaint((float) x, (float) y, color1,
                    (float) (x + w), (float) (y + h), color2);
        g2.setPaint(paint);
        Shape shape = new RoundRectangle2D.Float((float) x, (float) y, (float) w, (float) h, 5, 5);
        g2.clip(shape);
        g2.fillRect(x, y, x + w, y + h);
        g2.setPaint(null);
    }


    public void paintButtonBorder(SynthContext context, Graphics g, int x, int y, int w, int h) {
        g.setColor(borderColor);
        g.drawRoundRect(x, y, w - 1, h - 1, 5, 5);
    }


    public void paintLabelBackground(SynthContext context, Graphics g, int x, int y, int w, int h) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, w, h);
    }


}
