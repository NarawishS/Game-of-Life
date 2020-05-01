package sample;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;

public class Mainview extends VBox {
    private Button stepButton;
    private Button draw;
    private Button erase;
    private Canvas canvas;
    private Affine affine;
    private Grid grid;
    private final int resolution = 10;
    private final int width = 600;
    private final int height = 400;

    public Mainview() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);
        this.stepButton = new Button("step");
        this.stepButton.setOnAction(event -> {
            this.grid.next();
            draw();
        });
        this.draw = new Button("draw");
        this.erase = new Button("erase");
        this.affine = new Affine();
        this.affine.appendScale(this.resolution, this.resolution);
        this.canvas = new Canvas(this.width, this.height);
        hBox.getChildren().addAll(this.stepButton, this.draw, this.erase);
        this.getChildren().addAll(hBox, this.canvas);
        this.grid = new Grid(this.width / this.resolution, this.height / this.resolution);
        grid.setAlive(2, 2);
        grid.setAlive(3, 2);
        grid.setAlive(4, 2);
    }

    public void draw() {
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setFill(Color.LIGHTGRAY);
        g.fillRect(0, 0, this.width, this.height);
        g.setTransform(this.affine);
        g.setFill(Color.BLACK);
        for (int i = 0; i < grid.width; i++) {
            for (int j = 0; j < grid.height; j++) {
                if (this.grid.grid[i][j] == 1) {
                    g.fillRect(i, j, 1, 1);
                }
            }
        }

        g.setStroke(Color.GRAY);
        g.setLineWidth(0.05);
        for (int x = 0; x <= this.grid.width; x++) {
            g.strokeLine(x, 0, x, this.height);
        }
        for (int y = 0; y <= this.grid.height; y++) {
            g.strokeLine(0, y, this.width, y);
        }
    }

}
