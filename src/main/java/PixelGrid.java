public class PixelGrid {
    private static final int SIZE = 8;
    private final boolean[][] grid = new boolean[SIZE][SIZE];
    private int cursorX = 0;
    private int cursorY = 0;

    public boolean isPixelOn(int x, int y) {
        if (isInBounds(x, y)) {
            return grid[y][x];
        }
        return false;
    }

    public void togglePixel(int x, int y) {
        if (isInBounds(x, y)) {
            grid[y][x] = !grid[y][x];
        }
    }

    public void setPixel(int x, int y, boolean value) {
        if (isInBounds(x, y)) {
            grid[y][x] = value;
        }
    }

    public boolean[][] getGridCopy() {
        boolean[][] copy = new boolean[SIZE][SIZE];
        for (int y = 0; y < SIZE; y++) {
            System.arraycopy(grid[y], 0, copy[y], 0, SIZE);
        }
        return copy;
    }

    public int getCursorX() {
        return cursorX;
    }

    public int getCursorY() {
        return cursorY;
    }

    public void moveCursorUp() {
        if (cursorY > 0) cursorY--;
    }

    public void moveCursorDown() {
        if (cursorY < SIZE - 1) cursorY++;
    }

    public void moveCursorLeft() {
        if (cursorX > 0) cursorX--;
    }

    public void moveCursorRight() {
        if (cursorX < SIZE - 1) cursorX++;
    }

    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }
}
