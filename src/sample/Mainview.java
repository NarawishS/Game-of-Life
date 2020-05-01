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
    private Canvas canvas;
    private Affine affine;
    private Grid grid;
    private final int resolution = 20;
    private final int width = 600;
    private final int height = 400;
    private int drawMode = Grid.ALIVE;

    public Mainview() {
        Toolbar toolbar = new Toolbar(this);
        this.affine = new Affine();
        this.affine.appendScale(this.resolution, this.resolution);
        this.canvas = new Canvas(this.width, this.height);
        this.canvas.setOnMousePressed(this::drawHandler);
        this.canvas.setOnMouseDragged(this::drawHandler);
        this.getChildren().addAll(this.canvas, toolbar);
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
        // background
        g.setFill(Color.BLACK);
        g.fillRect(0, 0, this.width, this.height);
        g.setTransform(this.affine);
        // alive cell
        g.setFill(Color.WHITE);
        for (int i = 0; i < grid.width; i++) {
            for (int j = 0; j < grid.height; j++) {
                if (this.grid.grid[i][j] == 1) {
                    g.fillRect(i, j, 1, 1);
                }
            }
        }
        // table line
        g.setStroke(Color.WHITE);
        g.setLineWidth(0.05);
        for (int x = 0; x <= this.grid.width; x++) {
            g.strokeLine(x, 0, x, this.height);
        }
        for (int y = 0; y <= this.grid.height; y++) {
            g.strokeLine(0, y, this.width, y);
        }
    }


    public Grid getGrid() {
        return this.grid;
    }

    public void setMode(int mode) {
        this.drawMode = mode;
    }
}
