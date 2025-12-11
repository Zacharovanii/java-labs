package model;

import entities.*;
import utils.ImageLoader;
import java.util.HashSet;
import java.io.*;
import java.net.URL;

public class MapLoader {
    public static MapModel load(String mazeName, ImageLoader images, int tileSize) {
        String[] tileMap = loadMaze(mazeName);

        HashSet<Wall> walls = new HashSet<>();
        HashSet<Food> foods = new HashSet<>();
        HashSet<SuperFood> superFoods = new HashSet<>();
        HashSet<Ghost> ghosts = new HashSet<>();
        Pacman pacman = null;

        for (int r = 0; r < tileMap.length; r++) {
            for (int c = 0; c < tileMap[r].length(); c++) {
                char chr = tileMap[r].charAt(c);
                int x = c * tileSize;
                int y = r * tileSize;

                switch (chr) {
                    case 'X' -> walls.add(new Wall(x, y));
                    case 'P' -> pacman = new Pacman(images.PACMAN_RIGHT, x, y);
                    case 'b' -> ghosts.add(new Ghost(images.BLUE_GHOST, images.SCARED_GHOST,
                            images.EYES_GHOST, x, y, tileSize));
                    case 'o' -> ghosts.add(new Ghost(images.ORANGE_GHOST, images.SCARED_GHOST,
                            images.EYES_GHOST, x, y, tileSize));
                    case 'p' -> ghosts.add(new Ghost(images.PINK_GHOST, images.SCARED_GHOST,
                            images.EYES_GHOST, x, y, tileSize));
                    case 'r' -> ghosts.add(new Ghost(images.RED_GHOST, images.SCARED_GHOST,
                            images.EYES_GHOST, x, y, tileSize));
                    case ' ', '.' -> foods.add(new Food(x + 14, y + 14));
                    case '*' -> superFoods.add(new SuperFood(x, y, images.CHERRY_2));
                }
            }
        }

        return new MapModel(walls, foods, ghosts, superFoods, pacman);
    }

    public static String[] loadMaze(String mazeName) {
        URL url = MapLoader.class.getResource("/assets/mazes/" + mazeName + ".txt");
        if (url == null) {
            throw new RuntimeException("Maze not found: " + mazeName);
        }
        try {
            InputStream stream = url.openStream();
            return new BufferedReader(new InputStreamReader(stream))
                    .lines()
                    .toArray(String[]::new);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load maze: " + mazeName, e);
        }
    }
}