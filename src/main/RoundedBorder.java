package main;
import javax.swing.border.AbstractBorder;
import java.awt.*;

// Custom border class for rounded corners
public  class RoundedBorder extends AbstractBorder {
    private final Color color;
    private final int thickness;

    public RoundedBorder(Color color, int thickness) {
        this.color = color;
        this.thickness = thickness;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(thickness));
        g2d.drawRoundRect(x, y, width - 1, height - 1, 8, 8);
    }
}