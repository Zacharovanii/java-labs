package model;

import entities.*;
import utils.ImageLoader;
import java.util.HashSet;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MapLoader {
    private final ImageLoader images;
    private final int tileSize;

    public MapLoader(ImageLoader images, int tileSize) {
        this.images = images;
        this.tileSize = tileSize;
    }

    public Game load(String mazeName) {
        String[] tileMap = loadMaze(mazeName);

        HashSet<Wall> walls = new HashSet<>();
        HashSet<Food> foods = new HashSet<>();
        HashSet<SuperFood> superFoods = new HashSet<>();
        HashSet<Ghost> ghosts = new HashSet<>();
        Pacman[] pacmanHolder = new Pacman[1];

        for (int r = 0; r < tileMap.length; r++) {
            for (int c = 0; c < tileMap[r].length(); c++) {
                char chr = tileMap[r].charAt(c);
                int x = c * tileSize;
                int y = r * tileSize;

                Entity entity = createEntity(chr, x, y);
                if (entity != null) {
                    addEntity(entity, walls, foods, superFoods, ghosts, pacmanHolder);
                }
            }
        }

        if (pacmanHolder[0] == null) {
            throw new RuntimeException("No Pacman found in maze: " + mazeName);
        }

        return new Game(walls, foods, ghosts, superFoods, pacmanHolder[0]);
    }

    private Entity createEntity(char chr, int x, int y) {
        return switch (chr) {
            case 'X' -> new Wall(x, y, tileSize);
            case 'P' -> new Pacman(
                    images.PACMAN_UP, images.PACMAN_DOWN,
                    images.PACMAN_LEFT, images.PACMAN_RIGHT,
                    x, y, tileSize, tileSize / 4
            );
            case 'b' -> new Ghost(
                    images.BLUE_GHOST, images.SCARED_GHOST, images.EYES_GHOST,
                    x, y, tileSize, tileSize / 4
            );
            case 'o' -> new Ghost(
                    images.ORANGE_GHOST, images.SCARED_GHOST, images.EYES_GHOST,
                    x, y, tileSize, tileSize / 4
            );
            case 'p' -> new Ghost(
                    images.PINK_GHOST, images.SCARED_GHOST, images.EYES_GHOST,
                    x, y, tileSize, tileSize / 4
            );
            case 'r' -> new Ghost(
                    images.RED_GHOST, images.SCARED_GHOST, images.EYES_GHOST,
                    x, y, tileSize, tileSize / 4
            );
            case '*' -> new SuperFood(images.CHERRY_2, x, y, tileSize);
            case '.', ' ' -> new Food(x + 12, y + 12);
            default -> null;
        };
    }

    private void addEntity(Entity entity,
                           HashSet<Wall> walls,
                           HashSet<Food> foods,
                           HashSet<SuperFood> superFoods,
                           HashSet<Ghost> ghosts,
                           Pacman[] pacmanHolder) {
        if (entity instanceof Wall) {
            walls.add((Wall) entity);
        } else if (entity instanceof Food) {
            foods.add((Food) entity);
        } else if (entity instanceof SuperFood) {
            superFoods.add((SuperFood) entity);
        } else if (entity instanceof Ghost) {
            ghosts.add((Ghost) entity);
        } else if (entity instanceof Pacman) {
            pacmanHolder[0] = (Pacman) entity;
        }
    }

    private String[] loadMaze(String mazeName) {
        URL url = MapLoader.class.getResource("/assets/mazes/" + mazeName + ".txt");
        if (url == null) {
            throw new RuntimeException("Maze not found: " + mazeName);
        }
        try (InputStream stream = url.openStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {

            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            return lines.toArray(new String[0]);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load maze: " + mazeName, e);
        }
    }
}