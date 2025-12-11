package model;

public class GameState {
    private int score;
    private int lives;
    private int level;
    private boolean isGameOver;
    private boolean isPaused;
    private boolean isWin;

    GameState() {
        score = 0;
        lives = 3;
        level = 1;
        isGameOver = false;
        isPaused = false;
        isWin = false;
    }

    public int getScore() {return score;}
    public int getLives() {return lives;}
    public int getLevel() {return level;}
    public boolean isGameOver() {return isGameOver;}
    public boolean isPaused() {return isPaused;}
    public boolean isWin() {return isWin;}

    public void increaseScore(int amount) {score += amount;}
    public void increaseLives() {lives++;}
    public void decreaseLives() {lives--;}
    public void gameOver() {isGameOver = true;}
    public void togglePause() {isPaused = !isPaused;}
    public void gameWin() {isWin = true;}

    public void reset() {
        score = 0;
        lives = 3;
        level = 1;
        isGameOver = false;
        isPaused = false;
        isWin = false;
    }
}
