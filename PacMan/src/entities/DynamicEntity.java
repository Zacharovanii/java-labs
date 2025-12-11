package entities;

import java.awt.*;
import java.util.HashSet;

import model.Direction;

public abstract class DynamicEntity extends Entity {

    public Direction direction = Direction.UP;
    public EntityStatus status = EntityStatus.ALIVE;
    public final int startX;
    public final int startY;
    public int velocityX = 0;
    public int velocityY = 0;
    public int speed;

    public DynamicEntity(Image img, int x, int y, int size, int speed) {
        super(img, x, y, size);
        this.speed = speed;
        this.startX = x;
        this.startY = y;
    }

    public void updateVelocity() {
        switch (direction) {
            case Direction.UP -> { velocityX = 0; velocityY = -speed; }
            case Direction.DOWN -> { velocityX = 0; velocityY = speed; }
            case Direction.LEFT -> { velocityX = -speed; velocityY = 0; }
            case Direction.RIGHT -> { velocityX = speed; velocityY = 0; }
        }
    }


    public void handleTeleport(int boardWidth) {
        int tunnelTop = 9 * size - size / 2;
        int tunnelBottom = 9 * size + size / 2;

        if (y >= tunnelTop && y <= tunnelBottom) {
            // Вышел слева
            if (x < -size) {
                x = boardWidth - size;
            }
            // Вышел справа
            else if (x > boardWidth - size) {
                x = -size;
            }
        }
    }


    public void checkWallsCollision(HashSet<Wall> walls) {
        for (Wall wall : walls) {
            if (intersects(wall)) {
                x -= velocityX;
                y -= velocityY;
                return;
            }
        }
    }

    public void reset() {
        x = startX;
        y = startY;
        velocityX = velocityY = 0;
        status = EntityStatus.ALIVE;
    }

    public void kill() {
        status = EntityStatus.DEAD;
    }
}
