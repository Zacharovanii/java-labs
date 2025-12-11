package model;

public class Leader {
    private final String username;
    private final int score;

    public Leader(String username, int score) {
        this.username = username;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return username + " - " + score;
    }
}
