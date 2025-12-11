package entities;

public class Food extends StaticEntity {
    public Food(int x, int y, int size) {
        super(null, x, y, size);
    }

    public Food(int x, int y) {
        this(x, y, 4);
    }
}
