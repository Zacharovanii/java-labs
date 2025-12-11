package model;

public class GameState {
    private int score;
    private int lives;
    private int level;
    private boolean isGameOver;
    private boolean isPaused;
    private boolean isWin;
    private long frightenedTimer;
    private static final long FRIGHTENED_DURATION = 8000;

    public GameState() {
        reset();
    }

    public void reset() {
        score = 0;
        lives = 3;
        level = 1;
        isGameOver = false;
        isPaused = false;
        isWin = false;
        frightenedTimer = 0;
    }

    // Геттеры
    public int getScore() { return score; }
    public int getLives() { return lives; }
    public int getLevel() { return level; }
    public boolean isGameOver() { return isGameOver; }
    public boolean isPaused() { return isPaused; }
    public boolean isWin() { return isWin; }
    public boolean isGhostsFrightened() { return frightenedTimer > 0; }

    // Сеттеры и модификаторы
    public void increaseScore(int amount) { score += amount; }
    public void increaseLives() { lives++; }
    public void decreaseLives() { lives--; }
    public void nextLevel() { level++; isWin = false; }
    public void gameOver() { isGameOver = true; }
    public void togglePause() { isPaused = !isPaused; }
    public void win() { isWin = true; }

    public void startFrightenedMode() {
        frightenedTimer = System.currentTimeMillis() + FRIGHTENED_DURATION;
    }

    public void updateFrightenedTimer() {
        if (frightenedTimer > 0 && System.currentTimeMillis() > frightenedTimer) {
            frightenedTimer = 0;
        }
    }

    public long getFrightenedTimeLeft() {
        if (frightenedTimer == 0) return 0;
        return Math.max(0, frightenedTimer - System.currentTimeMillis());
    }
}