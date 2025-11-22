import java.awt.*;
import java.util.Random;

class SeaModel {
    private double phase = 0;
    private Color skyColor = new Color(100, 150, 255);
    private final Random rand = new Random();

    public double getPhase() {
        return phase;
    }

    public void updatePhase(double delta) {
        phase += delta;
    }

    public Color getSkyColor() {
        return skyColor;
    }

    public void changeSkyColor() {
        skyColor = new Color(rand.nextInt(156) + 100, rand.nextInt(156) + 100, 255);
    }
}
