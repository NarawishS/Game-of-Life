package gameOfLife;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

/**
 * handle simulation of the game.
 *
 * @author Narawish Sangsiriwut
 */
public class Simulator {
    /**
     * timeline for handle draw
     */
    private Timeline timeline;
    /**
     * main of game
     */
    private final Mainview mainview;

    /**
     * initialize timeline
     */
    public Simulator(Mainview mainview) {
        this.mainview = mainview;
        setTimeline(500);
    }

    /**
     * get information of cell and draw on canvas.
     */
    private void doStep(ActionEvent event) {
        this.mainview.getGrid().next();
        this.mainview.draw();
    }

    /**
     * start animation.
     */
    public void start() {
        this.timeline.play();
    }

    /**
     * stop animation.
     */
    public void stop() {
        this.timeline.stop();
    }

    /**
     * initialize timeline with KeyFrame of num
     *
     * @param num Duration of Keyframe in millis
     */
    public void setTimeline(double num) {
        this.timeline = new Timeline(new KeyFrame(Duration.millis(num), this::doStep));
        this.timeline.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * get status of Timeline
     */
    public Animation.Status getTimelineStat() {
        return this.timeline.getStatus();
    }
}
