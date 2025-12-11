package controller;

import model.*;
import view.GameView;
import view.HUDView;
import model.Direction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class GameController {
    private Game game;
    private final GameView view;
    private final HUDView hud;
    private final MapLoader mapLoader;
    private Timer gameTimer;
    private Timer hudTimer;

    private final String[] mapNames = {"basic", "level2", "level3"};
    private int currentMapIndex = 0;

    public GameController(GameView view, HUDView hud, MapLoader mapLoader) {
        this.view = view;
        this.hud = hud;
        this.mapLoader = mapLoader;

        loadCurrentMap();

        initializeTimers();
        setupKeyBindings();
    }

    private void loadCurrentMap() {
        game = mapLoader.load(mapNames[currentMapIndex]);
        view.setGame(game);
        hud.setGame(game);
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

        bind("test", this::handleLevelClear);
        bind("togglePause", this::togglePause);
        bind("restart", this::restartGame);

        im.put(KeyStroke.getKeyStroke("W"), "moveUp");
        im.put(KeyStroke.getKeyStroke("A"), "moveLeft");
        im.put(KeyStroke.getKeyStroke("S"), "moveDown");
        im.put(KeyStroke.getKeyStroke("D"), "moveRight");

        im.put(KeyStroke.getKeyStroke("T"), "test");
        im.put(KeyStroke.getKeyStroke("P"), "togglePause");
        im.put(KeyStroke.getKeyStroke("SPACE"), "restart");
    }

    private void updateGame() {
        if (game.state.isPaused()) {
            return;
        } else if (game.state.isGameOver()) {
            handleGameOver();
        } else {
            game.movePacman();
            game.eatFood();
            game.moveGhosts();

            if (game.isLevelCleared()) {
                handleLevelClear();
            }

            view.repaint();
            hud.repaint();
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


    private void restartGame() {
        game = mapLoader.load("basic");
        view.setGame(game);
        hud.setGame(game);

        gameTimer.start();
        hudTimer.start();

        view.repaint();
        hud.repaint();
    }

    private void handleGameOver() {
        gameTimer.stop();
        hudTimer.stop();

        String username = JOptionPane.showInputDialog(view, "Введите ваше имя:");

        if (username != null && !username.isBlank()) {
            LeaderRepository.addLeader(username.trim(), game.state.getScore());
        }

        java.util.List<Leader> topLeaders = LeaderRepository.getTopLeaders();
        StringBuilder sb = new StringBuilder("Топ-5 игроков:\n");
        for (int i = 0; i < topLeaders.size(); i++) {
            sb.append(i + 1).append(". ").append(topLeaders.get(i)).append("\n");
        }

        JOptionPane.showMessageDialog(view, sb.toString(), "Лидеры", JOptionPane.INFORMATION_MESSAGE);

        restartGame();
    }


    private void handleLevelClear() {
        game.state.win();
        view.repaint();
        hud.repaint();

        gameTimer.stop();
        hudTimer.stop();

        Timer delay = new Timer(1000, e -> {
            ((Timer)e.getSource()).stop();

            // Следующая карта по кругу
            currentMapIndex = (currentMapIndex + 1) % mapNames.length;
            Game nextMap = mapLoader.load(mapNames[currentMapIndex]);
            game.nextLevel(nextMap);

            gameTimer.start();
            hudTimer.start();
            view.repaint();
            hud.repaint();
        });

        delay.setRepeats(false);
        delay.start();
    }

}