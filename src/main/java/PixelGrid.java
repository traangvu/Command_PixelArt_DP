public class PixelGrid {
    private final boolean[][] grid = new boolean[8][8];
    private int cursorX = 0, cursorY = 0;

    public void moveCursor(int dx, int dy) {
        cursorX = Math.max(0, Math.min(7, cursorX + dx));
        cursorY = Math.max(0, Math.min(7, cursorY + dy));
    }

    public void togglePixel() {
        grid[cursorY][cursorX] = !grid[cursorY][cursorX];
    }

    public boolean isPixelOn(int x, int y) {
        return grid[y][x];
    }

    public int getCursorX() {
        return cursorX;
    }

    public int getCursorY() {
        return cursorY;
    }

    public boolean[][] getGridCopy() {
        boolean[][] copy = new boolean[8][8];
        for (int y = 0; y < 8; y++) {
            System.arraycopy(grid[y], 0, copy[y], 0, 8);
        }
        return copy;
    }
}
