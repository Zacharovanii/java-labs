package model;

import entities.*;
import java.util.HashSet;

public class MapModel {
    public HashSet<Wall> walls;
    public HashSet<Food> foods;
    public HashSet<SuperFood> superFoods;
    public HashSet<Ghost> ghosts;
    public Pacman pacman;

    public MapModel(HashSet<Wall> walls, HashSet<Food> foods, HashSet<Ghost> ghosts,
                    HashSet<SuperFood> superFoods, Pacman pacman) {
        this.walls = walls;
        this.foods = foods;
        this.ghosts = ghosts;
        this.superFoods = superFoods;
        this.pacman = pacman;

        for (Ghost g : ghosts)
            g.addTarget(pacman);
    }


    private boolean isPacmanGhostCollision() {
        for (Ghost g : ghosts)
            if (g.intersects(pacman) && g.getMode() == GhostMode.CHASE)
                return true;
            else if (g.intersects(pacman) && g.getMode() == GhostMode.FRIGHTENED) {
                g.changeToEyes();
                GameModel.score += 200;
            }
        return false;
    }

    public void movePacman() {
        pacman.move(walls);
        if (isPacmanGhostCollision()) {
            pacman.kill();
        }
    }

    public void moveGhosts() {
        for (Ghost g : ghosts)
            g.move(walls);
    }


    public void eatFood() {
        Food eaten = null;
        for (Food f : foods) {
            if (pacman.intersects(f)) {
                eaten = f;
                GameModel.score += 10;
                break;
            }
        }
        if (eaten != null) foods.remove(eaten);

        // Поедаем супер еду
        SuperFood superEaten = null;
        for (SuperFood sf : superFoods) {
            if (pacman.intersects(sf)) {
                superEaten = sf;
                GameModel.score += 50;
                for (Ghost g : ghosts)
                    g.changeToFrightened();
                break;
            }
        }
        if (superEaten != null) superFoods.remove(superEaten);
    }


    public void resetPositions() {
        pacman.reset();
        for (Ghost ghost : ghosts) {
            ghost.reset();
        }
    }

    public void resetGhosts() {
        for (Ghost g : ghosts) {
            g.reset();
            g.changeToChase();
        }
    }


}