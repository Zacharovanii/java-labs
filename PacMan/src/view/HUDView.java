package view;

import model.Game;

import javax.swing.*;
import java.awt.*;

public class HUDView extends JPanel {
    private int gameTimeSeconds = 0;
    private Image pacmanIcon;
    private boolean isTimerActive = true;

    private Game game;

    public HUDView() {
    }

    public void setGame(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(game.geometry.boardWidth(), 60));
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);

        this.pacmanIcon = game.pacman.right;
        startGameTimer();
    }

    /**
     * Запускает таймер игры
     */
    private void startGameTimer() {
        // Обновляем каждую секунду
        Timer gameTimer = new Timer(1000, e -> { // Обновляем каждую секунду
            if (isTimerActive && !game.state.isPaused() && !game.state.isGameOver()) {
                gameTimeSeconds++;
                repaint();
            }
        });
        gameTimer.start();
    }

    /**
     * Останавливает таймер (пауза)
     */
    public void stopTimer() {
        isTimerActive = false;
    }

    /**
     * Запускает/возобновляет таймер
     */
    public void startTimer() {
        isTimerActive = true;
    }

    /**
     * Полностью сбрасывает таймер
     */
    public void resetTimer() {
        gameTimeSeconds = 0;
        isTimerActive = true;
    }

    /**
     * Перезапускает игру
     */
    public void gameRestarted() {
        resetTimer();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawLeftSection(g2d);
        drawCenterSection(g2d);
        drawRightSection(g2d);
    }

    private void drawLeftSection(Graphics2D g) {
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("SCORE: " + game.state.getScore(), 15, 25);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString("LIVES: ", 15, 46);

        int lifeIconSize = 16;
        int lifeStartX = 60;
        int lifeY = 33;

        for (int i = 0; i < game.state.getLives(); i++) {
            g.drawImage(pacmanIcon, lifeStartX + i * (lifeIconSize + 5), lifeY,
                    lifeIconSize, lifeIconSize, null);
        }
    }

    private void drawCenterSection(Graphics2D g) {
        String statusText;
        Color statusColor;

        if (game.state.isGameOver()) {
            statusText = "GAME OVER";
            statusColor = Color.RED;
        } else if (game.state.isWin()) {
            statusText = "WINNN!!";
            statusColor = Color.YELLOW;
        } else if (game.state.isPaused()) {
            statusText = "PAUSED";
            statusColor = Color.YELLOW;
        } else {
            statusText = "PLAYING";
            statusColor = Color.GREEN;
        }

        g.setColor(new Color(255, 255, 255, 30));
        g.fillRoundRect(getWidth() / 2 - 60, 8, 120, 25, 10, 10);

        g.setColor(statusColor);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString(statusText, getWidth() / 2 - g.getFontMetrics().stringWidth(statusText) / 2, 25);

        g.setColor(new Color(255, 255, 255, 80));
        g.drawRoundRect(getWidth() / 2 - 60, 8, 120, 25, 10, 10);
    }

    private void drawRightSection(Graphics2D g) {
        int rightMargin = 15;

        // Таймер
        String timePlayed = formatTime(gameTimeSeconds);

        // Меняем цвет таймера на паузе
        if (game.state.isPaused()) {
            g.setColor(Color.GRAY);
        } else {
            g.setColor(Color.CYAN);
        }

        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("TIME: " + timePlayed, getWidth() - g.getFontMetrics().stringWidth("TIME: " + timePlayed) - rightMargin, 25);

        // Уровень
//        g.setColor(Color.ORANGE);
//        g.setFont(new Font("Arial", Font.BOLD, 14));
//        String levelText = "LEVEL: " + GameModel.level;
//        g.drawString(levelText, getWidth() - g.getFontMetrics().stringWidth(levelText) - rightMargin, 40);

        // Индикатор паузы возле таймера
        if (game.state.isPaused()) {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 12));
            g.drawString("⏸", getWidth() - 35, 38);
        }
    }

    private String formatTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    /**
     * Возвращает текущее игровое время в секундах
     */
    public int getGameTime() {
        return gameTimeSeconds;
    }
}