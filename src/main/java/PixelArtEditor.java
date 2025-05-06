import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PixelArtEditor extends Application {
    private static final int CELL_SIZE = 40;
    private final PixelGrid grid = new PixelGrid();
    private final Rectangle[][] cells = new Rectangle[8][8];

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
        });

        VBox root = new VBox(pane, generateButton);
        Scene scene = new Scene(root);
        updateGrid();

        // Setting focus to the root node
        root.setFocusTraversable(true);

        scene.setOnKeyPressed(event -> {
            Command cmd = null;
            if (event.getCode() == KeyCode.UP) {
                cmd = new MoveCursorUpCommand(grid);
                System.out.println("Up arrow pressed");
            } else if (event.getCode() == KeyCode.DOWN) {
                cmd = new MoveCursorDownCommand(grid);
                System.out.println("Down arrow pressed");
            } else if (event.getCode() == KeyCode.LEFT) {
                cmd = new MoveCursorLeftCommand(grid);
                System.out.println("Left arrow pressed");
            } else if (event.getCode() == KeyCode.RIGHT) {
                cmd = new MoveCursorRightCommand(grid);
                System.out.println("Right arrow pressed");
            } else if (event.getCode() == KeyCode.SPACE) {
                cmd = new TogglePixelCommand(grid);
                System.out.println("Space pressed");
            }

            // Execute command if it's not null
            if (cmd != null) {
                cmd.execute();
                updateGrid();
            }
        });

        stage.setScene(scene);
        stage.setTitle("Pixel Art Editor");
        stage.show();
    }

    void updateGrid() {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (grid.isPixelOn(x, y)) {
                    cells[y][x].setFill(Color.BLACK);
                } else {
                    cells[y][x].setFill(Color.WHITE);
                }

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

    public static void main(String[] args) {
        launch(args);
    }
}
