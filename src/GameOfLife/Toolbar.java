package GameOfLife;

import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class Toolbar extends HBox {
    private final Mainview mainview;
    private final Slider speedSlider;

    public Toolbar(Mainview main) {
        this.mainview = main;
        Button stepButton = new Button("step");
        stepButton.setOnAction(this::stepHandler);
        Button drawButton = new Button("draw");
        drawButton.setOnAction(this::drawHandler);
        Button eraseButton = new Button("erase");
        eraseButton.setOnAction(this::eraseHandler);
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
        this.getChildren().addAll(drawButton, eraseButton, randomButton,
                label1, speedSlider, startButton, stopButton, stepButton);
    }

    private void randomHandler(ActionEvent event) {
        this.mainview.getGrid().randomGrid();
        this.mainview.draw();
    }

    private void startHandler(ActionEvent event) {
        if (this.mainview.getSimulator().getTimelineStat() != Animation.Status.RUNNING) {
            this.mainview.getSimulator().setTimeline(this.speedSlider.getValue());
            this.mainview.getSimulator().start();
        }
    }

    private void stopHandler(ActionEvent event) {
        this.mainview.getSimulator().stop();
    }

    private void stepHandler(ActionEvent event) {
        this.mainview.getGrid().next();
        this.mainview.draw();
    }

    private void drawHandler(ActionEvent event) {
        this.mainview.setCell(Grid.ALIVE);
    }

    private void eraseHandler(ActionEvent event) {
        this.mainview.setCell(Grid.DEAD);
    }

}
