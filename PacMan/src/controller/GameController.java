package controller;

import model.*;
import view.GameView;
import view.HUDView;
import model.Direction;
import model.GhostMode;

import javax.swing.*;
import java.awt.event.*;

public class GameController implements ActionListener, KeyListener {
    private Game game;
    private final GameView view;
    private final HUDView hud;
    private final MapLoader mapLoader;
    private Timer gameTimer;
    private Timer hudTimer;

    public GameController(GameView view, HUDView hud, MapLoader mapLoader) {
        this.view = view;
        this.hud = hud;
        this.mapLoader = mapLoader;

        // Загружаем начальную игру
        this.game = mapLoader.load("basic");
        view.setGame(game);
        hud.setGame(game);

        initializeTimers();
    }

    private void initializeTimers() {
        gameTimer = new Timer(50, this); // 20 FPS
        hudTimer = new Timer(100, e -> {
            if (!game.state.isPaused()) {
                game.state.updateFrightenedTimer();
            }
            hud.repaint();
        });

        gameTimer.start();
        hudTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (game.state.isGameOver() || game.state.isPaused()) {
            return;
        }

        // Обновление состояния игры
        game.movePacman();
        game.eatFood();
        game.moveGhosts();

        // Проверка условий
        if (game.isLevelCleared()) {
            handleLevelClear();
        }

        view.repaint();
        hud.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        if (game.state.isGameOver()) {
            handleGameRestart(e);
            return;
        }

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP, KeyEvent.VK_W ->
                    game.pacman.requestDirection(Direction.UP);
            case KeyEvent.VK_DOWN, KeyEvent.VK_S ->
                    game.pacman.requestDirection(Direction.DOWN);
            case KeyEvent.VK_LEFT, KeyEvent.VK_A ->
                    game.pacman.requestDirection(Direction.LEFT);
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D ->
                    game.pacman.requestDirection(Direction.RIGHT);
            case KeyEvent.VK_P -> togglePause();
            case KeyEvent.VK_R -> resetLevel();
            case KeyEvent.VK_ESCAPE -> System.exit(0);
        }
    }

    private void togglePause() {
        game.state.togglePause();

        if (game.state.isPaused()) {
            gameTimer.stop();
            hudTimer.stop();
        } else {
            gameTimer.start();
            hudTimer.start();
        }

        hud.repaint();
    }

    private void resetLevel() {
        game.state.reset();
        game.resetPositions();
        game.resetGhosts();

        gameTimer.start();
        hudTimer.start();

        hud.repaint();
        view.repaint();
    }

    private void handleGameRestart(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER ||
                e.getKeyCode() == KeyEvent.VK_SPACE) {
            restartGame();
        }
    }

    private void restartGame() {
        game = mapLoader.load("basic");
        view.setGame(game);
        hud.setGame(game);

        gameTimer.start();
        hudTimer.start();

        view.repaint();
        hud.repaint();
    }

    private void handleLevelClear() {
        game.state.win();
        game.state.nextLevel();
        game.nextLevel();

        // Можно загрузить следующий уровень по имени
        // Например: String nextLevel = "level" + game.state.getLevel();
        // game = mapLoader.load(nextLevel);

        view.repaint();
        hud.repaint();
    }
}