package entities;

import model.Direction;
import model.GhostMode;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Ghost extends DynamicEntity {
    private Pacman target;
    private final Random random = new Random();
    private GhostMode mode = GhostMode.CHASE;

    private int frightenedTimer = 0;

    private int speed;
    private final int basicSpeed;

    private final Image base;
    private final Image scared;
    private final Image eyes;

    public Ghost(
            Image _base, Image _scared, Image _eyes,
            int x, int y, int size, int _speed

    ) {
        super(_base, x, y, size, _speed);
        base = _base;
        scared = _scared;
        eyes = _eyes;
        speed = _speed;
        basicSpeed = _speed;
        direction = Direction.RIGHT;
    }

    public void addTarget(Pacman pacman) {
        target = pacman;
    }

    public void changeToFrightened() {
        if (mode == GhostMode.EYES) return; // глаза не пугаются
        mode = GhostMode.FRIGHTENED;
        image = scared;
        speed = basicSpeed / 2;
        int frightenedDuration = 8000;
        frightenedTimer = frightenedDuration / 50; // пересчитали под 50мс Tick
    }


    private void snapToGrid() {
        x = (x / size) * size;
        y = (y / size) * size;
    }


    public void changeToEyes() {
        mode = GhostMode.EYES;
        image = eyes;
        speed = basicSpeed;
    }

    public void changeToChase() {
        mode = GhostMode.CHASE;
        image = base;
        speed = basicSpeed;
    }

    public GhostMode getMode() {
        return mode;
    }


    public void move(HashSet<Wall> walls) {
        if (mode == GhostMode.FRIGHTENED) {
            frightenedTimer--;
            if (frightenedTimer <= 0) {
                changeToChase();
                frightenedTimer = 0;
                snapToGrid();
            }
        }

        if (mode == GhostMode.EYES) {
            moveToHome();
            return;
        }

        chooseDirection(walls);
        updateVelocity();
        x += velocityX;
        y += velocityY;

//        handleTeleport();
        checkWallsCollision(walls);
    }


    private void moveToHome() {
        int speedEyes = speed * 2;

        // Двигаем по X
        if (x < startX) {
            x += speedEyes;
            if (x > startX) x = startX; // не перескакиваем центр
        }
        else if (x > startX) {
            x -= speedEyes;
            if (x < startX) x = startX;
        }

        // Двигаем по Y
        if (y < startY) {
            y += speedEyes;
            if (y > startY) y = startY;
        }
        else if (y > startY) {
            y -= speedEyes;
            if (y < startY) y = startY;
        }

        // Если точно в центре — оживляем
        if (x == startX && y == startY) {
            changeToChase();
        }
    }

    private void chooseDirection(HashSet<Wall> walls) {
        List<Direction> possibleDirections = getPossibleDirections(walls);
        if (possibleDirections.isEmpty()) return;

        // Если можем продолжить движение вперед - делаем это с большей вероятностью
        if (possibleDirections.contains(direction) && random.nextInt(100) < 70) {
            return; // продолжаем в том же направлении
        }

        if (mode == GhostMode.FRIGHTENED) {
            direction = findDirectionAwayFromTarget(possibleDirections);
            return;
        }

        // Ищем лучшее направление
        Direction bestDirection = findDirectionToTarget(possibleDirections);

        // Избегаем резких разворотов (180 градусов)
        if (bestDirection != null && !isOppositeDirection(bestDirection, direction)) {
            this.direction = bestDirection;
        } else {
            // Если лучшее направление - разворот, выбираем другое хорошее направление
            Direction alternative = findGoodAlternative(possibleDirections);
            if (alternative != null) {
                this.direction = alternative;
            }
        }
    }

    private Direction findDirectionAwayFromTarget(List<Direction> possibleDirections) {
        Direction bestDirection = null;
        double maxDistance = -1;

        for (Direction dir : possibleDirections) {
            int newX = x;
            int newY = y;

            switch (dir) {
                case UP -> newY -= speed;
                case DOWN -> newY += speed;
                case LEFT -> newX -= speed;
                case RIGHT -> newX += speed;
            }

            double distance = calculateDistance(newX, newY, target.x, target.y);

            if (distance > maxDistance) {
                maxDistance = distance;
                bestDirection = dir;
            }
        }

        return bestDirection;
    }


    private boolean isOppositeDirection(Direction dir1, Direction dir2) {
        return (dir1 == Direction.UP && dir2 == Direction.DOWN) ||
                (dir1 == Direction.DOWN && dir2 == Direction.UP) ||
                (dir1 == Direction.LEFT && dir2 == Direction.RIGHT) ||
                (dir1 == Direction.RIGHT && dir2 == Direction.LEFT);
    }

    private Direction findGoodAlternative(List<Direction> possibleDirections) {
        // Убираем противоположное направление из возможных
        Direction opposite = getOppositeDirection(direction);
        List<Direction> alternatives = new ArrayList<>(possibleDirections);
        alternatives.remove(opposite);

        if (alternatives.isEmpty()) return null;

        // Возвращаем случайное из оставшихся направлений
        return alternatives.get(random.nextInt(alternatives.size()));
    }

    private Direction getOppositeDirection(Direction dir) {
        return switch (dir) {
            case UP -> Direction.DOWN;
            case DOWN -> Direction.UP;
            case LEFT -> Direction.RIGHT;
            case RIGHT -> Direction.LEFT;
            default -> dir;
        };
    }

    private Direction findDirectionToTarget(List<Direction> possibleDirections) {
        Direction bestDirection = null;
        double minDistance = Double.MAX_VALUE;

        for (Direction dir : possibleDirections) {
            // Предсказываем новую позицию при движении в этом направлении
            int newX = x;
            int newY = y;

            switch (dir) {
                case UP -> newY -= speed;
                case DOWN -> newY += speed;
                case LEFT -> newX -= speed;
                case RIGHT -> newX += speed;
            }

            // Вычисляем расстояние до Пакмана
            double distance = calculateDistance(newX, newY, target.x, target.y);

            if (distance < minDistance) {
                minDistance = distance;
                bestDirection = dir;
            }
        }

        return bestDirection;
    }

    private double calculateDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    private List<Direction> getPossibleDirections(HashSet<Wall> walls) {
        List<Direction> possibleDirections = new ArrayList<>();

        // Проверяем все 4 направления на возможность движения
        if (canMove(Direction.UP, walls)) possibleDirections.add(Direction.UP);
        if (canMove(Direction.DOWN, walls)) possibleDirections.add(Direction.DOWN);
        if (canMove(Direction.LEFT, walls)) possibleDirections.add(Direction.LEFT);
        if (canMove(Direction.RIGHT, walls)) possibleDirections.add(Direction.RIGHT);

        return possibleDirections;
    }

    private boolean canMove(Direction dir, HashSet<Wall> walls) {
        // Создаем временный прямоугольник для проверки столкновений

        if (mode == GhostMode.EYES)
            return true;   // глаза проходят сквозь стены

        Rectangle futureBounds = new Rectangle(x, y, size, size);

        switch (dir) {
            case UP -> futureBounds.y -= speed;
            case DOWN -> futureBounds.y += speed;
            case LEFT -> futureBounds.x -= speed;
            case RIGHT -> futureBounds.x += speed;
        }

        // Проверяем столкновения со стенами
        for (Wall wall : walls) {
            if (futureBounds.intersects(wall.getBounds())) {
                return false;
            }
        }

        return true;
    }
}