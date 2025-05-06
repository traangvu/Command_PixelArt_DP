public class TogglePixelCommand implements Command {
    private final PixelGrid grid;

    public TogglePixelCommand(PixelGrid grid) {
        this.grid = grid;
    }

    @Override
    public void execute() {
        int x = grid.getCursorX();
        int y = grid.getCursorY();
        grid.togglePixel(x, y);
    }
}
