package controller;

import model.*;
import view.GameView;
import view.HUDView;
import model.Direction;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GameController {
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

        this.game = mapLoader.load("basic");
        view.setGame(game);
        hud.setGame(game);

        initializeTimers();
        setupKeyBindings();
    }

    private void initializeTimers() {
        gameTimer = new Timer(50, e -> updateGame());
        hudTimer = new Timer(100, e -> {
            if (!game.state.isPaused()) {
                game.state.updateFrightenedTimer();
            }
            hud.repaint();
        });

        gameTimer.start();
        hudTimer.start();
    }

    private void bind(String key, Runnable action) {
        ActionMap am = view.getActionMap();

        am.put(key, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action.run();
            }
        });
    }

    private void setupKeyBindings() {
        InputMap im = view.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        bind("moveUp", () -> game.pacman.requestDirection(Direction.UP));
        bind("moveDown", () -> game.pacman.requestDirection(Direction.DOWN));
        bind("moveLeft", () -> game.pacman.requestDirection(Direction.LEFT));
        bind("moveRight", () -> game.pacman.requestDirection(Direction.RIGHT));

        bind("togglePause", this::togglePause);
        bind("restart", this::restartGame);

        im.put(KeyStroke.getKeyStroke("W"), "moveUp");
        im.put(KeyStroke.getKeyStroke("A"), "moveLeft");
        im.put(KeyStroke.getKeyStroke("S"), "moveDown");
        im.put(KeyStroke.getKeyStroke("D"), "moveRight");

        im.put(KeyStroke.getKeyStroke("P"), "togglePause");
        im.put(KeyStroke.getKeyStroke("SPACE"), "restart");
    }

    private void updateGame() {
        if (game.state.isGameOver() || game.state.isPaused()) {
            return;
        }

        game.movePacman();
        game.eatFood();
        game.moveGhosts();

        if (game.isLevelCleared()) {
            handleLevelClear();
        }

        view.repaint();
        hud.repaint();
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

        view.repaint();
        hud.repaint();
    }
}