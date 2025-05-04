public class MoveCursorUpCommand implements Command {
    private final PixelGrid grid;

    public MoveCursorUpCommand(PixelGrid grid) {
        this.grid = grid;
    }

    public void execute() {
        grid.moveCursor(0, -1);
    }
}
