package sample;

import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;

public class Mainview extends VBox {
    private Button stepButton;
    private Button drawButton;
    private Button eraseButton;
    private Canvas canvas;
    private Affine affine;
    private Grid grid;
    private final int resolution = 20;
    private final int width = 600;
    private final int height = 400;
    private int drawMode = 1;

    public Mainview() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);
        this.stepButton = new Button("step");
        this.stepButton.setOnAction(event -> {
            this.grid.next();
            draw();
        });
        this.drawButton = new Button("draw");
        this.drawButton.setOnAction(event -> {
            this.drawMode = 1;
        });
        this.eraseButton = new Button("erase");
        this.eraseButton.setOnAction(event -> {
            this.drawMode = 0;
        });
        this.affine = new Affine();
        this.affine.appendScale(this.resolution, this.resolution);
        this.canvas = new Canvas(this.width, this.height);
        this.canvas.setOnMousePressed(this::drawHandler);
        this.canvas.setOnMouseDragged(this::drawHandler);
        hBox.getChildren().addAll(this.stepButton, this.drawButton, this.eraseButton);
        this.getChildren().addAll(this.canvas, hBox);
        this.grid = new Grid(this.width / this.resolution, this.height / this.resolution);
    }

    private void drawHandler(MouseEvent mouseEvent) {
        double mouseX = mouseEvent.getX();
        double mouseY = mouseEvent.getY();
        try {
            Point2D pos = this.affine.inverseTransform(mouseX, mouseY);
            int posX = (int) pos.getX();
            int posY = (int) pos.getY();
            this.grid.setState(posX, posY, this.drawMode);
            draw();
        } catch (NonInvertibleTransformException e) {
            e.printStackTrace();
        }
    }

    public void draw() {
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setFill(Color.BLACK);
        g.fillRect(0, 0, this.width, this.height);
        g.setTransform(this.affine);
        g.setFill(Color.WHITE);
        for (int i = 0; i < grid.width; i++) {
            for (int j = 0; j < grid.height; j++) {
                if (this.grid.grid[i][j] == 1) {
                    g.fillRect(i, j, 1, 1);
                }
            }
        }

        g.setStroke(Color.WHITE);
        g.setLineWidth(0.05);
        for (int x = 0; x <= this.grid.width; x++) {
            g.strokeLine(x, 0, x, this.height);
        }
        for (int y = 0; y <= this.grid.height; y++) {
            g.strokeLine(0, y, this.width, y);
        }
    }


}
