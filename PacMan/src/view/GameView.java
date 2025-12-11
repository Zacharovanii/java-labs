package view;

import entities.*;
import model.Game;

import javax.swing.*;
import java.awt.*;

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

        Wall.drawWalls(g, map.walls, map.geometry.tileSize());

        g.setColor(Color.WHITE);
        for (Food f : map.foods)
            g.fillRect(f.x, f.y, f.size, f.size);

        g.setColor(Color.ORANGE);
        for (SuperFood f : map.superFoods)
            f.draw(g);

        drawGhosts(g);

        map.pacman.draw(g);
    }

    private void drawGhosts(Graphics g) {
        for (Ghost ghs : map.ghosts)
            ghs.draw(g);
    }

}
