package entities;

import java.awt.*;
import java.util.HashSet;

import model.Direction;

public class Pacman extends DynamicEntity {

    private Direction desiredDirection = Direction.NONE;
    public final Image up;
    public final Image down;
    public final Image left;
    public final Image right;

    private int boardWidth;

    public Pacman(
            Image _up, Image _down, Image _left, Image _right,
            int x, int y, int size, int speed
    ) {
        super(_right, x, y, size, speed);
        up = _up;
        down = _down;
        left = _left;
        right = _right;
    }

    public void requestDirection(Direction d) {
        this.desiredDirection = d;
    }
    public void setBoardWidth(int bw) { boardWidth = bw; }


    public void updateSprite() {
        switch (direction) {
            case UP -> image = up;
            case DOWN -> image = down;
            case LEFT -> image = left;
            case RIGHT -> image = right;
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

        handleTeleport(boardWidth);
        checkWallsCollision(walls);

        updateSprite();
    }


    private boolean canTurn(Direction d, HashSet<Wall> walls) {
        // Проверяем, что Pacman почти по центру тайла
        int centerX = (x + size / 2) / size;
        int centerY = (y + size / 2) / size;

        int targetX = centerX * size;
        int targetY = centerY * size;

        // Если Pacman не почти по центру тайла — не поворачиваем
        if (Math.abs(x - targetX) > 3 || Math.abs(y - targetY) > 3)
            return false;

        // Проверяем коллизию на один тайл в сторону поворота
        int newX = x, newY = y;

        switch (d) {
            case UP    -> newY -= size/2;
            case DOWN  -> newY += size/2;
            case LEFT  -> newX -= size/2;
            case RIGHT -> newX += size/2;
        }

        Rectangle test = new Rectangle(newX, newY, size, size);

        for (Wall w : walls) {
            if (test.intersects(w.getBounds())) return false;
        }

        return true;
    }
}
