import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PixelArtEditor extends Application {
    private static final int CELL_SIZE = 40;
    private final PixelGrid grid = new PixelGrid();
    private final Rectangle[][] cells = new Rectangle[8][8];
    private final TextArea debugConsole = new TextArea();  // Debug console

    @Override
    public void start(Stage stage) {
        GridPane pane = new GridPane();
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                Rectangle rect = new Rectangle(CELL_SIZE, CELL_SIZE);
                rect.setStroke(Color.LIGHTGRAY);
                rect.setFill(Color.WHITE);
                cells[y][x] = rect;
                pane.add(rect, x, y);
            }
        }

        Button generateButton = new Button("Create Code");
        generateButton.setOnAction(e -> {
            new GenerateCodeCommand(grid).execute();
            log("GenerateCodeCommand executed");
        });

        debugConsole.setEditable(false);
        debugConsole.setPrefRowCount(6);
        debugConsole.setStyle("-fx-font-family: monospace;");

        VBox root = new VBox(pane, generateButton, debugConsole);
        Scene scene = new Scene(root);
        updateGrid();
        log("Application started and grid initialized.");

        root.setFocusTraversable(true);

        scene.setOnKeyPressed(event -> {
            Command cmd = null;
            KeyCode key = event.getCode();
            switch (key) {
                case UP:
                    cmd = new MoveCursorUpCommand(grid);
                    log("Key pressed: UP");
                    break;
                case DOWN:
                    cmd = new MoveCursorDownCommand(grid);
                    log("Key pressed: DOWN");
                    break;
                case LEFT:
                    cmd = new MoveCursorLeftCommand(grid);
                    log("Key pressed: LEFT");
                    break;
                case RIGHT:
                    cmd = new MoveCursorRightCommand(grid);
                    log("Key pressed: RIGHT");
                    break;
                case SPACE:
                    cmd = new TogglePixelCommand(grid);
                    log("Key pressed: SPACE (Toggle Pixel)");
                    break;
                default:
                    log("Key pressed: " + key);
            }

            if (cmd != null) {
                cmd.execute();
                updateGrid();
                log("Command executed: " + cmd.getClass().getSimpleName());
            }
        });

        stage.setScene(scene);
        stage.setTitle("Pixel Art Editor");
        stage.show();
        root.requestFocus();  // Request focus so key events work
    }

    void updateGrid() {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                cells[y][x].setFill(grid.isPixelOn(x, y) ? Color.BLACK : Color.WHITE);

                if (x == grid.getCursorX() && y == grid.getCursorY()) {
                    cells[y][x].setStroke(Color.RED);
                    cells[y][x].setStrokeWidth(3);
                } else {
                    cells[y][x].setStroke(Color.LIGHTGRAY);
                    cells[y][x].setStrokeWidth(1);
                }
            }
        }
    }

    private void log(String message) {
        debugConsole.appendText(message + "\n");
        System.out.println(message);
    }
}
