package entities;

import java.awt.*;
import java.util.Random;
import model.GameModel;

public class SuperFood extends StaticEntity {

    private static final Image[] images = {GameModel.cherry, GameModel.cherry2};
    private static final Random random = new Random();
    private final Image img;

    public SuperFood(int x, int y) {
        super(null, x, y, GameModel.tileSize, GameModel.tileSize);
        image = images[random.nextInt(images.length)];
        this.img = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(this.img, x, y, width, height, null);
    }
}
