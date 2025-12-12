package view;

import entities.*;
import model.Game;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class GameView extends JPanel {

    private Game map;

    public GameView() {}

    public void setGame(Game map) {
        this.map = map;
        setPreferredSize(new Dimension(map.geometry.boardWidth(), map.geometry.boardHeight()));
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawWalls(g, map.walls, map.geometry.tileSize());

        g.setColor(Color.WHITE);
        for (Food f : map.foods)
            g.fillRect(f.x, f.y, f.size, f.size);

        g.setColor(Color.ORANGE);
        for (SuperFood f : map.superFoods)
            f.draw(g);

        drawGhosts(g);

        map.pacman.draw(g);
    }

    public static void drawWalls(Graphics g, HashSet<Wall> walls, int size) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF); // В оригинале не было сглаживания
        g2.setColor(Color.BLUE);

        int wallThickness = 4;

        for (Wall wall : walls) {
            int x = wall.x;
            int y = wall.y;

            // Проверяем всех соседей
            boolean top         = walls.contains(new Wall(x, y - size, size));
            boolean bottom      = walls.contains(new Wall(x, y + size, size));
            boolean left        = walls.contains(new Wall(x - size, y, size));
            boolean right       = walls.contains(new Wall(x + size, y, size));
            boolean topLeft     = walls.contains(new Wall(x - size, y - size, size));
            boolean topRight    = walls.contains(new Wall(x + size, y - size, size));
            boolean bottomLeft  = walls.contains(new Wall(x - size, y + size, size));
            boolean bottomRight = walls.contains(new Wall(x + size, y + size, size));

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
        }
    }

    private void drawGhosts(Graphics g) {
        for (Ghost ghs : map.ghosts)
            ghs.draw(g);
    }

}
