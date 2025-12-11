package entities;

import model.GameModel;

import java.awt.*;
import java.util.HashSet;

public class Wall extends StaticEntity {

    private static final int VISUAL_PADDING = 4; // отступ внутри хитбокса

    public Wall(int x, int y) {
        super(null, x, y, GameModel.tileSize, GameModel.tileSize);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wall wall)) return false;
        return x == wall.x && y == wall.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }


    public static void drawWalls(Graphics g, HashSet<Wall> walls) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF); // В оригинале не было сглаживания

        int size = GameModel.tileSize;
        g2.setColor(Color.BLUE);

        int wallThickness = 4;

        for (Wall wall : walls) {
            int x = wall.x;
            int y = wall.y;

            // Проверяем всех соседей
            boolean top = walls.contains(new Wall(x, y - size));
            boolean bottom = walls.contains(new Wall(x, y + size));
            boolean left = walls.contains(new Wall(x - size, y));
            boolean right = walls.contains(new Wall(x + size, y));
            boolean topLeft = walls.contains(new Wall(x - size, y - size));
            boolean topRight = walls.contains(new Wall(x + size, y - size));
            boolean bottomLeft = walls.contains(new Wall(x - size, y + size));
            boolean bottomRight = walls.contains(new Wall(x + size, y + size));

            // Оригинальный алгоритм Пакмана:
            // 1. Всегда рисуем внутренние углы
            // 2. Рисуем границы только там, где нет соседей
            // 3. Используем специальную логику для Т-образных пересечений

            // Верхняя граница
            if (!top) {
                g2.fillRect(x, y, size, wallThickness);
            }

            // Нижняя граница
            if (!bottom) {
                g2.fillRect(x, y + size - wallThickness, size, wallThickness);
            }

            // Левая граница
            if (!left) {
                g2.fillRect(x, y, wallThickness, size);
            }

            // Правая граница
            if (!right) {
                g2.fillRect(x + size - wallThickness, y, wallThickness, size);
            }

            // Внутренние углы - ключевая особенность оригинального Пакмана
            // Левый верхний внутренний угол
            if (top && left && !topLeft) {
                g2.fillRect(x, y, wallThickness, wallThickness);
            }

            // Правый верхний внутренний угол
            if (top && right && !topRight) {
                g2.fillRect(x + size - wallThickness, y, wallThickness, wallThickness);
            }

            // Левый нижний внутренний угол
            if (bottom && left && !bottomLeft) {
                g2.fillRect(x, y + size - wallThickness, wallThickness, wallThickness);
            }

            // Правый нижний внутренний угол
            if (bottom && right && !bottomRight) {
                g2.fillRect(x + size - wallThickness, y + size - wallThickness, wallThickness, wallThickness);
            }

            // Специальные случаи для Т-образных пересечений
            // Т-образное пересечение сверху
            if (top && left && right && !bottom) {
                g2.fillRect(x + wallThickness, y + size - wallThickness,
                        size - 2 * wallThickness, wallThickness);
            }

            // Т-образное пересечение снизу
            if (bottom && left && right && !top) {
                g2.fillRect(x + wallThickness, y, size - 2 * wallThickness, wallThickness);
            }

            // Т-образное пересечение слева
            if (left && top && bottom && !right) {
                g2.fillRect(x + size - wallThickness, y + wallThickness,
                        wallThickness, size - 2 * wallThickness);
            }

            // Т-образное пересечение справа
            if (right && top && bottom && !left) {
                g2.fillRect(x, y + wallThickness, wallThickness, size - 2 * wallThickness);
            }
        }
    }
}
