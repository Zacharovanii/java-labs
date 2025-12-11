package model;

import entities.*;
import java.util.HashSet;

public class Game {
    public HashSet<Wall> walls;
    public HashSet<Food> foods;
    public HashSet<SuperFood> superFoods;
    public HashSet<Ghost> ghosts;
    public Pacman pacman;

    public GameGeometry geometry;
    public GameState state;

    public Game(
            HashSet<Wall> walls,
            HashSet<Food> foods,
            HashSet<Ghost> ghosts,
            HashSet<SuperFood> superFoods,
            Pacman pacman
    ) {
        geometry = GameGeometry.standard();
        state = new GameState();
        pacman.setBoardWidth(geometry.boardWidth());

        this.walls = walls;
        this.foods = foods;
        this.ghosts = ghosts;
        this.superFoods = superFoods;
        this.pacman = pacman;

        for (Ghost g : ghosts) {
            g.addTarget(pacman);
            g.setBoardWidth(geometry.boardWidth());
        }
    }

    private boolean isPacmanGhostCollision() {
        for (Ghost g : ghosts) {
            if (g.intersects(pacman)) {
                if (g.getMode() == GhostMode.CHASE) {
                    return true;
                } else if (g.getMode() == GhostMode.FRIGHTENED) {
                    g.changeToEyes();
                    state.increaseScore(200);
                }
            }
        }
        return false;
    }

    public void movePacman() {
        pacman.move(walls);
        if (isPacmanGhostCollision()) {
            handlePacmanDeath();
        }
    }

    public void moveGhosts() {
        for (Ghost g : ghosts) {
            g.move(walls);
        }
    }

    public void eatFood() {
        // Обычная еда
        Food eaten = null;
        for (Food f : foods) {
            if (pacman.intersects(f)) {
                eaten = f;
                state.increaseScore(10);
                break;
            }
        }
        if (eaten != null) foods.remove(eaten);

        // Супер еда
        SuperFood superEaten = null;
        for (SuperFood sf : superFoods) {
            if (pacman.intersects(sf)) {
                superEaten = sf;
                state.increaseScore(50);
                for (Ghost g : ghosts) {
                    g.changeToFrightened();
                }
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

    public void handlePacmanDeath() {
        pacman.kill();
        state.decreaseLives();

        if (state.getLives() <= 0) {
            state.gameOver();
        } else {
            resetPositions();
            resetGhosts();
        }
    }

    public boolean isLevelCleared() {
        return foods.isEmpty() && superFoods.isEmpty();
    }

    public void nextLevel() {
        state.nextLevel();
        resetPositions();
        resetGhosts();
    }
}