package entities;

import java.awt.*;
import java.util.HashSet;
import model.GameModel;
import model.Direction;

public class Pacman extends DynamicEntity {

    private Direction desiredDirection = Direction.NONE;


    public Pacman(Image img, int x, int y) {
        super(img, x, y, GameModel.tileSize, GameModel.tileSize, GameModel.tileSize / 4);
    }

    public void requestDirection(Direction d) {
        this.desiredDirection = d;
    }


    public void updateSprite() {
        switch (direction) {
            case UP -> image = GameModel.pacmanUpImage;
            case DOWN -> image = GameModel.pacmanDownImage;
            case LEFT -> image = GameModel.pacmanLeftImage;
            case RIGHT -> image = GameModel.pacmanRightImage;
        }
    }

    public void move(HashSet<Wall> walls) {

        // --- 1. Пробуем применить buffered turn ---
        if (desiredDirection != null && canTurn(desiredDirection, walls)) {
            direction = desiredDirection;
            updateVelocity();
            desiredDirection = null;
        }

        // --- 2. Двигаем Pacman ---
        x += velocityX;
        y += velocityY;

        handleTeleport();
        checkWallsCollision(walls);

        updateSprite();
    }


    private boolean canTurn(Direction d, HashSet<Wall> walls) {
        // Проверяем, что Pacman почти по центру тайла
        int centerX = (x + width / 2) / GameModel.tileSize;
        int centerY = (y + height / 2) / GameModel.tileSize;

        int targetX = centerX * GameModel.tileSize;
        int targetY = centerY * GameModel.tileSize;

        // Если Pacman не почти по центру тайла — не поворачиваем
        if (Math.abs(x - targetX) > 3 || Math.abs(y - targetY) > 3)
            return false;

        // Проверяем коллизию на один тайл в сторону поворота
        int newX = x, newY = y;

        switch (d) {
            case UP    -> newY -= GameModel.tileSize/2;
            case DOWN  -> newY += GameModel.tileSize/2;
            case LEFT  -> newX -= GameModel.tileSize/2;
            case RIGHT -> newX += GameModel.tileSize/2;
        }

        Rectangle test = new Rectangle(newX, newY, width, height);

        for (Wall w : walls) {
            if (test.intersects(w.getBounds())) return false;
        }

        return true;
    }
}
