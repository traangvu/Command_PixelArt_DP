public class TogglePixelCommand implements Command {
    private final PixelGrid grid;

    public TogglePixelCommand(PixelGrid grid) {
        this.grid = grid;
    }

    @Override
    public void execute() {
        int x = grid.getCursorX();
        int y = grid.getCursorY();
        boolean isOn = grid.isPixelOn(x, y);
        grid.setPixel(x, y, !isOn);
    }
}
