package gameOfLife;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;

/**
 * Main Scene of the game.
 *
 * @author Narawish Sangsiriwut
 */
public class Mainview extends VBox {
    /**
     * Canvas that draw the board.
     */
    private final Canvas canvas;
    /**
     * Resolution of the cell.
     */
    private final Affine affine;
    /**
     * board data.
     */
    private final Grid grid;
    /**
     * width of canvas.
     */
    private final int width;
    /**
     * height of canvas.
     */
    private final int height;
    /**
     * size of cell.
     */
    private final int resolution;
    /**
     * status to set cell when mouse is press on canvas.
     */
    private int drawMode = Grid.ALIVE;
    /**
     * class for running board.
     */
    private final Simulator simulator;
    /**
     * Status of board contain day, alive cell.
     */
    private final Text status = new Text();

    /**
     * Create init component of Mainview.
     */
    public Mainview(int width, int height, int resolution, boolean loop) {
        this.width = width;
        this.height = height;
        this.resolution = resolution;
        Toolbar toolbar = new Toolbar(this);
        toolbar.getChildren().add(status);
        this.affine = new Affine();
        this.affine.appendScale(resolution, resolution);
        this.canvas = new Canvas(this.width, this.height);
        this.canvas.setOnMousePressed(this::drawHandler);
        this.canvas.setOnMouseDragged(this::drawHandler);
        this.simulator = new Simulator(this);
        this.grid = new Grid(this.width / resolution, this.height / resolution, loop);
        Menubar menubar = new Menubar(this);
        this.getChildren().addAll(menubar, this.canvas, toolbar);
    }

    /**
     * set canvas cell according to current drawMode.
     *
     * @param mouseEvent event when mouse is press on canvas.
     */
    private void drawHandler(MouseEvent mouseEvent) {
        double mouseX = mouseEvent.getX();
        double mouseY = mouseEvent.getY();
        try {
            Point2D pos = this.affine.inverseTransform(mouseX, mouseY);
            int posX = (int) pos.getX();
            int posY = (int) pos.getY();
            this.grid.setState(posX, posY, this.drawMode);
            this.grid.day = 0;
            draw();
        } catch (NonInvertibleTransformException e) {
            e.printStackTrace();
        }
    }

    /**
     * draw grid information on canvas and update status.
     */
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

    /**
     * get grid.
     *
     * @return this grid
     */
    public Grid getGrid() {
        return this.grid;
    }

    /**
     * set drawMode.
     */
    public void setDrawMode(int mode) {
        this.drawMode = mode;
    }

    /**
     * get simulator.
     *
     * @return this Simulator
     */
    public Simulator getSimulator() {
        return this.simulator;
    }

    /**
     * get size information of canvas.
     *
     * @return information of canvas(eg.800x400_20)
     */
    public String getSize() {
        return String.format("%dx%d_%d", this.width, this.height, this.resolution);
    }
}
