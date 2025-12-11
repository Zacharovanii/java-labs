package controller;

import model.*;
import view.GameView;
import view.HUDView;

import javax.swing.*;
import java.awt.event.*;

public class GameController implements ActionListener, KeyListener {
    private MapModel map;
    private final GameView view;
    private final HUDView hud;

public GameController(MapModel _map, GameView _view, HUDView _hud) {
        map = _map;
        view = _view;
        hud = _hud;

        Timer gameTimer = new Timer(50, this); // 20 FPS
        Timer hudTimer = new Timer(100, e -> hud.repaint());

        gameTimer.start();
        hudTimer.start();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (!GameModel.gameOver && !GameModel.isPaused) {
            map.movePacman();
            map.eatFood();
            map.moveGhosts();

            handlePacmanDeath();
            handleLevelClear();
        }

        view.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        if (GameModel.gameOver) {
            restartGame();
            return;
        }

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP, KeyEvent.VK_W -> map.pacman.requestDirection(Direction.UP);
            case KeyEvent.VK_DOWN, KeyEvent.VK_S -> map.pacman.requestDirection(Direction.DOWN);
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> map.pacman.requestDirection(Direction.LEFT);
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> map.pacman.requestDirection(Direction.RIGHT);
            case KeyEvent.VK_P -> togglePause();
            case KeyEvent.VK_R -> resetLevel();
        }
    }

    private void togglePause() {
        GameModel.isPaused = !GameModel.isPaused;

        if (GameModel.isPaused) {
            hud.stopTimer();
        } else {
            hud.startTimer();
        }

        hud.repaint();
    }

    private void resetLevel() {
        map.resetPositions();
        GameModel.isPaused = false;
        hud.startTimer();
        hud.repaint();
    }

    private void restartGame() {
        GameModel.resetGame();
        map = MapLoader.load(GameModel.tileMap);
        view.setMap(map);
        hud.gameRestarted();
    }

    private void handlePacmanDeath() {
        if (!map.pacman.isDead()) return;

        GameModel.lives--;

        if (GameModel.lives < 1) {
            GameModel.gameOver = true;
        }

        map.resetGhosts();
        map.pacman.reset();
    }


    private void handleLevelClear() {
        if (map.foods.isEmpty() && map.superFoods.isEmpty()) {
            GameModel.level++;
            map = MapLoader.load(GameModel.tileMap);
            view.setMap(map);
            GameModel.win = true;
        }
    }
}