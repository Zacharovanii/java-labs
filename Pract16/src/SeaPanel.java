import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

class SeaPanel extends JPanel {
    private final SeaModel model;

    public SeaPanel(SeaModel model) {
        this.model = model;
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int w = getWidth();
        int h = getHeight();
        int baseY = (int) (h * 0.75);

        g2d.setColor(model.getSkyColor());
        g2d.fillRect(0, 0, w, h);

        drawWave(
                g2d, w, h, baseY,
                25, 0.02,
                new Color(0, 120, 255, 180),
                model.getPhase()
        );
        drawWave(g2d, w, h, baseY + 10,
                35, 0.03,
                new Color(0, 90, 200, 200),
                model.getPhase() * 1.3
        );

    }

    private void drawWave(Graphics2D g2d, int w, int h, int baseY,
                          double amplitude, double freq, Color color, double phase) {
        g2d.setColor(color);
        Path2D path = new Path2D.Double();
        path.moveTo(0, baseY);
        for (int x = 0; x <= w; x++) {
            double y = baseY + amplitude * Math.sin(freq * x + phase);
            path.lineTo(x, y);
        }
        path.lineTo(w, h);
        path.lineTo(0, h);
        path.closePath();
        g2d.fill(path);
    }
}
