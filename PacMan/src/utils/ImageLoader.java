package utils;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageLoader {
    public final Image BLUE_GHOST;
    public final Image ORANGE_GHOST;
    public final Image PINK_GHOST;
    public final Image RED_GHOST;
    public final Image SCARED_GHOST;
    public final Image EYES_GHOST;

    public final Image PACMAN_UP;
    public final Image PACMAN_DOWN;
    public final Image PACMAN_LEFT;
    public final Image PACMAN_RIGHT;

    public final Image CHERRY;
    public final Image CHERRY_2;
    public final Image POWER_FOOD;

    public ImageLoader() {
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

    private Image load(String path) {
        URL url = getClass().getClassLoader().getResource(path);
        if (url == null) {
            throw new RuntimeException(
            "Не удалось загрузить изображение: " + path +
            ". Проверьте наличие файла в ресурсах."
            );
        }
        ImageIcon icon = new ImageIcon(url);
        return icon.getImage();
    }
}
