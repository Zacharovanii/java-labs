package entities;

import java.awt.*;

public abstract class Entity {
    public int x, y;
    public int width, height;
    public Image image;

    public Entity(Image img, int x, int y, int w, int h) {
        this.image = img;
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }

    public boolean intersects(Entity other) {
        return x < other.x + other.width &&
                x + width > other.x &&
                y < other.y + other.height &&
                y + height > other.y;
    }

    public void draw(Graphics g) {
        g.drawImage(this.image, this.x, this.y, this.width, this.height, null);
    }
}
