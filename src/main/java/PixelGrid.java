public class PixelGrid {
    private final boolean[][] grid = new boolean[8][8];
    private int cursorX = 0;
    private int cursorY = 0;

    public boolean isPixelOn(int x, int y) {
        return grid[y][x];
    }

    public void togglePixel(int x, int y) {
        grid[y][x] = !grid[y][x];
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
        if (cursorY < 7) cursorY++;
    }

    public void moveCursorLeft() {
        if (cursorX > 0) cursorX--;
    }

    public void moveCursorRight() {
        if (cursorX < 7) cursorX++;
    }

    public boolean[][] getGridCopy() {
        boolean[][] gridCopy = new boolean[8][8];
        for (int y = 0; y < 8; y++) {
            System.arraycopy(grid[y], 0, gridCopy[y], 0, 8);
        }
        return gridCopy;
    }
}
