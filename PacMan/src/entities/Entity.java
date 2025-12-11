package entities;

import java.awt.*;

public abstract class Entity {
    public int x, y;
    public int size;
    public Image image;

    public Entity(Image img, int x, int y, int size) {
        this.image = img;
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public boolean intersects(Entity other) {
        return x < other.x + other.size &&
                x + size > other.x &&
                y < other.y + other.size &&
                y + size > other.y;
    }

    public void draw(Graphics g) {
        g.drawImage(this.image, this.x, this.y, this.size, this.size, null);
    }
}
