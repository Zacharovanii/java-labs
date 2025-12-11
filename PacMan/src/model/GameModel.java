package model;

import java.awt.*;
import utils.ImageLoader;

public class GameModel {
    public static final int rowCount = 19;
    public static final int columnCount = 19;
    public static final int tileSize = 32;
    public static final int boardWidth = columnCount * tileSize;
    public static final int boardHeight = rowCount * tileSize;


    public static final Image wallImage = ImageLoader.WALL;
    public static final Image blueGhostImage = ImageLoader.BLUE_GHOST;
    public static final Image orangeGhostImage = ImageLoader.ORANGE_GHOST;
    public static final Image pinkGhostImage = ImageLoader.PINK_GHOST;
    public static final Image redGhostImage = ImageLoader.RED_GHOST;
    public static final Image scaredGhost = ImageLoader.SCARED_GHOST;
    public static final Image eyes = ImageLoader.EYES_GHOST;

    public static final Image pacmanUpImage = ImageLoader.PACMAN_UP;
    public static final Image pacmanDownImage = ImageLoader.PACMAN_DOWN;
    public static final Image pacmanLeftImage = ImageLoader.PACMAN_LEFT;
    public static final Image pacmanRightImage = ImageLoader.PACMAN_RIGHT;

    public static final Image cherry = ImageLoader.CHERRY;
    public static final Image cherry2 = ImageLoader.CHERRY_2;
    public static final Image powerFood = ImageLoader.POWER_FOOD;

    public static int score = 0;
    public static int lives = 3;
    public static int level = 1;
    public static boolean gameOver = false;
    public static long ghostVulnerableTimer = 0;
    public static boolean isPaused = false;
    public static boolean win = false;


    //X = wall, O = skip, P = pac man, ' ' = food
    //Ghosts: b = blue, o = orange, p = pink, r = red
    public static final String[] tileMap = {
            "XXXXXXXXXXXXXXXXXXX",
            "X        X        X",
            "X XX XXX X XXX XX X",
            "X        *        X",
            "X XX X XXXXX X XX X",
            "X    X       X   *X",
            "XXXX XXXX XXXX XXXX",
            "OOOX X       X XOOO",
            "XXXX X XXrXX X XXXX",
            "O       bpo       O",
            "XXXX X XXXXX X XXXX",
            "X        X        X",
            "X XX XXX X XXX XX X",
            "X  X     P     X  X",
            "XX X X XXXXX X X XX",
            "X    X   X   X    X",
            "X XXXXXX X XXXXXX X",
            "X        *        X",
            "XXXXXXXXXXXXXXXXXXX"
    };

    public static void resetGame() {
        score = 0;
        lives = 3;
        level = 1;
        gameOver = false;
        ghostVulnerableTimer = 0;
    }
}
