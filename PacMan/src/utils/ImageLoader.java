package utils;

import javax.swing.*;
import java.awt.*;

public class ImageLoader {

    public static final Image WALL;
    public static final Image BLUE_GHOST;
    public static final Image ORANGE_GHOST;
    public static final Image PINK_GHOST;
    public static final Image RED_GHOST;
    public static final Image SCARED_GHOST;
    public static final Image EYES_GHOST;

    public static final Image PACMAN_UP;
    public static final Image PACMAN_DOWN;
    public static final Image PACMAN_LEFT;
    public static final Image PACMAN_RIGHT;

    public static final Image CHERRY;
    public static final Image CHERRY_2;
    public static final Image POWER_FOOD;


    static {
        WALL = load("assets/wall.png");
        BLUE_GHOST = load("assets/blueGhost.png");
        ORANGE_GHOST = load("assets/orangeGhost.png");
        PINK_GHOST = load("assets/pinkGhost.png");
        RED_GHOST = load("assets/redGhost.png");
        SCARED_GHOST = load("assets/scaredGhost.png");
        EYES_GHOST = load("assets/eyes.png");

        PACMAN_UP = load("assets/pacmanUp.png");
        PACMAN_DOWN = load("assets/pacmanDown.png");
        PACMAN_LEFT = load("assets/pacmanLeft.png");
        PACMAN_RIGHT = load("assets/pacmanRight.png");

        CHERRY = load("assets/cherry.png");
        CHERRY_2 = load("assets/cherry2.png");
        POWER_FOOD = load("assets/powerFood.png");
    }

    private static Image load(String path) {
        return new ImageIcon(ImageLoader.class.getClassLoader().getResource(path)).getImage();
    }
}
