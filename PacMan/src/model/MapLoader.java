package model;

import entities.*;

import java.util.HashSet;

public class MapLoader {
    public static MapModel load(String[] tileMap) {

        HashSet<Wall> walls = new HashSet<>();
        HashSet<Food> foods = new HashSet<>();
        HashSet<SuperFood> superFoods = new HashSet<>();
        HashSet<Ghost> ghosts = new HashSet<>();
        Pacman pacman = null;

        for (int r = 0; r < tileMap.length; r++) {
            for (int c = 0; c < tileMap[r].length(); c++) {

                char chr = tileMap[r].charAt(c);
                int x = c * GameModel.tileSize;
                int y = r * GameModel.tileSize;

                switch (chr) {
                    case 'X' -> walls.add(new Wall(x, y));
                    case 'P' -> pacman = new Pacman(GameModel.pacmanRightImage, x, y);
                    case 'b' -> ghosts.add(new Ghost(GameModel.blueGhostImage, x, y));
                    case 'o' -> ghosts.add(new Ghost(GameModel.orangeGhostImage, x, y));
                    case 'p' -> ghosts.add(new Ghost(GameModel.pinkGhostImage, x, y));
                    case 'r' -> ghosts.add(new Ghost(GameModel.redGhostImage, x, y));
                    case ' ', '.' -> foods.add(new Food(x + 14, y + 14));
                    case '*' -> superFoods.add(new SuperFood(x, y));
                }
            }
        }

        return new MapModel(walls, foods, ghosts, superFoods, pacman);
    }
}
