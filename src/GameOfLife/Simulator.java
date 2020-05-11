package GameOfLife;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

public class Simulator {
    private Timeline timeline;
    private final Mainview mainview;

    public Simulator(Mainview mainview) {
        this.mainview = mainview;
        setTimeline(500);
    }

    private void doStep(ActionEvent event) {
        this.mainview.getGrid().next();
        this.mainview.draw();
    }


    public void start() {
        this.timeline.play();
    }

    public void stop() {
        this.timeline.stop();
    }

    public void setTimeline(double num) {
        this.timeline = new Timeline(new KeyFrame(Duration.millis(num), this::doStep));
        this.timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public Animation.Status getTimelineStat() {
        return this.timeline.getStatus();
    }
}
