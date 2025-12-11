package view;

import entities.*;
import model.GameModel;
import model.MapModel;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {

    private MapModel map;

    public GameView(MapModel map) {
        this.map = map;
        setPreferredSize(new Dimension(GameModel.boardWidth, GameModel.boardHeight));
        setBackground(Color.BLACK);
    }

    public void setMap(MapModel map) {
        this.map = map;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Wall.drawWalls(g, map.walls);

        g.setColor(Color.WHITE);
        for (Food f : map.foods)
            g.fillRect(f.x, f.y, f.width, f.height);

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
