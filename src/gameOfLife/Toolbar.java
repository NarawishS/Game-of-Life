package gameOfLife;

import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

/**
 * console of the game.
 *
 * @author Narawish Sangsiriwut
 */
public class Toolbar extends HBox {
    /**
     * mainview of game
     */
    private final Mainview mainview;
    /**
     * slider for speed
     */
    private final Slider speedSlider;

    /**
     * initialize toolbar
     *
     * @param main mainview of game
     */
    public Toolbar(Mainview main) {
        this.mainview = main;
        Button stepButton = new Button("step");
        stepButton.setOnAction(this::stepHandler);
        Button drawButton = new Button("draw");
        drawButton.setOnAction(this::drawHandler);
        Button eraseButton = new Button("erase");
        eraseButton.setOnAction(this::eraseHandler);
        Button clearButton = new Button("clear");
        clearButton.setOnAction(event -> {
            this.mainview.getGrid().clearGrid();
            this.mainview.draw();
        });
        Label label1 = new Label("speed");
        Button startButton = new Button("start");
        startButton.setOnAction(this::startHandler);
        Button stopButton = new Button("stop");
        stopButton.setOnAction(this::stopHandler);
        Button randomButton = new Button("random");
        randomButton.setOnAction(this::randomHandler);
        this.speedSlider = new Slider();
        this.speedSlider.setMax(500);
        this.speedSlider.setMin(1);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(5);
        this.getChildren().addAll(drawButton, eraseButton, clearButton, randomButton,
                label1, speedSlider, startButton, stopButton, stepButton);
    }

    /**
     * handle event for random.
     *
     * @param event random button click
     */
    private void randomHandler(ActionEvent event) {
        this.mainview.getGrid().randomGrid();
        this.mainview.draw();
    }

    /**
     * handle event for start.
     *
     * @param event start button click
     */
    private void startHandler(ActionEvent event) {
        if (this.mainview.getSimulator().getTimelineStat() != Animation.Status.RUNNING) {
            this.mainview.getSimulator().setTimeline(this.speedSlider.getValue());
            this.mainview.getSimulator().start();
        }
    }

    /**
     * handle event for stop.
     *
     * @param event stop button click
     */
    private void stopHandler(ActionEvent event) {
        this.mainview.getSimulator().stop();
    }

    /**
     * handle event for step.
     *
     * @param event step button click
     */
    private void stepHandler(ActionEvent event) {
        this.mainview.getGrid().next();
        this.mainview.draw();
    }

    /**
     * handle event for draw.
     *
     * @param event draw button click
     */
    private void drawHandler(ActionEvent event) {
        this.mainview.setDrawMode(Grid.ALIVE);
    }

    /**
     * handle event for erase.
     *
     * @param event erase button click
     */
    private void eraseHandler(ActionEvent event) {
        this.mainview.setDrawMode(Grid.DEAD);
    }

}
