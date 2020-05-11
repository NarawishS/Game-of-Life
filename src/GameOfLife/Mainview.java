package GameOfLife;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;

public class Mainview extends VBox {
    private final Canvas canvas;
    private final Affine affine;
    private final Grid grid;
    private final int width = 1200;
    private final int height = 800;
    private int drawMode = Grid.ALIVE;
    private Simulator simulator;
    private Text status = new Text();

    public Mainview() {
        Toolbar toolbar = new Toolbar(this);
        toolbar.getChildren().add(status);
        this.affine = new Affine();
        int resolution = 20;
        this.affine.appendScale(resolution, resolution);
        this.canvas = new Canvas(this.width, this.height);
        this.canvas.setOnMousePressed(this::drawHandler);
        this.canvas.setOnMouseDragged(this::drawHandler);
        this.simulator = new Simulator(this);
        this.grid = new Grid(this.width / resolution, this.height / resolution);
        this.getChildren().addAll(this.canvas, toolbar);
    }

    private void drawHandler(MouseEvent mouseEvent) {
        double mouseX = mouseEvent.getX();
        double mouseY = mouseEvent.getY();
        try {
            Point2D pos = this.affine.inverseTransform(mouseX, mouseY);
            int posX = (int) pos.getX();
            int posY = (int) pos.getY();
            this.grid.setState(posX, posY, this.drawMode);
            System.out.println(posX + ", " + posY + " : " + this.drawMode);
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
                if (this.grid.grid[i][j] == Grid.ALIVE) {
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
        this.status.setText(String.format("\tDay : %d\tAlive : %d", this.grid.day, this.grid.getAlive()));
    }

    public Grid getGrid() {
        return this.grid;
    }

    public void setCell(int mode) {
        this.drawMode = mode;
    }

    public Simulator getSimulator() {
        return this.simulator;
    }
}
