package entities;

import java.awt.*;
import java.util.HashSet;

import model.Direction;
import model.GameModel;

public abstract class DynamicEntity extends Entity {

    public Direction direction = Direction.UP;
    public EntityStatus status = EntityStatus.ALIVE;
    public final int startX;
    public final int startY;
    public int velocityX = 0;
    public int velocityY = 0;
    public int speed;

    public DynamicEntity(Image img, int x, int y, int w, int h, int speed) {
        super(img, x, y, w, h);
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


    public void handleTeleport() {

        int tunnelY = 9 * GameModel.tileSize;
        int tolerance = GameModel.tileSize / 2;

        // Проверяем, что призрак именно в туннеле
        if (Math.abs(y - tunnelY) > tolerance) return;

        if (x < -width) {
            x = GameModel.boardWidth;
            y = tunnelY;
        }
        else if (x > GameModel.boardWidth) {
            x = -width;
            y = tunnelY;
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

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
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

    public boolean isDead() {
        return status == EntityStatus.DEAD;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }
}
