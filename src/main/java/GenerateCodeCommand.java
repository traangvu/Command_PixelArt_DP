public class GenerateCodeCommand implements Command {
    private final PixelGrid grid;

    public GenerateCodeCommand(PixelGrid grid) {
        this.grid = grid;
    }

    @Override
    public void execute() {
        System.out.println("int[][] pixelArt = {");
        for (int y = 0; y < 8; y++) {
            System.out.print("    {");
            for (int x = 0; x < 8; x++) {
                System.out.print(grid.isPixelOn(x, y) ? "1" : "0");
                if (x < 7) System.out.print(", ");
            }
            System.out.println("},");
        }
        System.out.println("};");
    }
}
