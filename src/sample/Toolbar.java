package sample;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class Toolbar extends HBox {
    private final Mainview mainview;

    public Toolbar(Mainview main) {
        this.mainview = main;
        Button stepButton = new Button("step");
        stepButton.setOnAction(this::stepHandler);
        Button drawButton = new Button("draw");
        drawButton.setOnAction(this::drawHandler);
        Button eraseButton = new Button("erase");
        eraseButton.setOnAction(this::eraseHandler);
        Label label1 = new Label("speed");
        Slider speedSlider = new Slider();
        Button startButton = new Button("start");
        Button stopButton = new Button("stop");
        this.setAlignment(Pos.CENTER);
        this.setSpacing(5);
        this.getChildren().addAll(drawButton, eraseButton,
                label1, speedSlider, startButton, stopButton, stepButton);
    }

    private void stepHandler(ActionEvent event) {
        this.mainview.getGrid().next();
        this.mainview.draw();
    }

    private void drawHandler(ActionEvent event) {
        this.mainview.setMode(Grid.ALIVE);
    }

    private void eraseHandler(ActionEvent event) {
        this.mainview.setMode(Grid.DEAD);
    }
}
