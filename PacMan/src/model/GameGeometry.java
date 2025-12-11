package model;

public record GameGeometry(
        int rows,
        int cols,
        int tileSize
) {
    public int boardWidth() { return cols * tileSize; }
    public int boardHeight() { return rows * tileSize; }

    public static GameGeometry standard() {
        return new GameGeometry(19, 19, 32);
    }

    public static GameGeometry of(int rows, int cols) {
        return new GameGeometry(rows, cols, 32);
    }

    public static GameGeometry of(int rows, int cols, int tileSize) {
        return new GameGeometry(rows, cols, tileSize);
    }

    public GameGeometry {
        if (rows <= 0) throw new IllegalArgumentException("Rows must be positive");
        if (cols <= 0) throw new IllegalArgumentException("Cols must be positive");
        if (tileSize <= 0) throw new IllegalArgumentException("Tile size must be positive");
    }
}