public class GenerateCodeCommand implements Command {
    private final PixelGrid grid;

    public GenerateCodeCommand(PixelGrid grid) {
        this.grid = grid;
    }

    @Override
    public void execute() {
        boolean[][] gridCopy = grid.getGridCopy();
        StringBuilder code = new StringBuilder("int[][] pixelArt = {\n");
        for (int i = 0; i < 8; i++) {
            code.append("    {");
            for (int j = 0; j < 8; j++) {
                code.append(gridCopy[i][j] ? "1" : "0");
                if (j < 7) code.append(", ");
            }
            code.append("},\n");
        }
        code.append("};");
        System.out.println(code.toString());
    }
}
