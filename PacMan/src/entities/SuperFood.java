package entities;

import java.awt.*;
import java.util.Random;

public class SuperFood extends StaticEntity {
    public SuperFood(
            Image image,
            int x, int y, int size) {
        super(image, x, y, size);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x, y, size, size, null);
    }
}
