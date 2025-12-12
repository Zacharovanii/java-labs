package entities;

import java.awt.*;

public class Wall extends StaticEntity {
    public Wall(int x, int y, int size) {
        super(null, x, y, size);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wall wall)) return false;
        return x == wall.x && y == wall.y;
    }
    @Override
    public int hashCode() {return 31 * x + y;}
}
