package gameOfLife;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Scene when start the application to configure the canvas.
 *
 * @author Narawish Sangsiriwut
 */
public class Startview extends VBox {
    ComboBox<Size> size = new ComboBox<>();
    ComboBox<CellSize> cSize = new ComboBox<>();

    /**
     * initialize configuration menu
     */
    public Startview(Stage primaryStage) {
        this.setMinSize(320, 100);
        this.setPadding(new Insets(10.0));
        this.setAlignment(Pos.CENTER);
        this.setSpacing(5.0);
        primaryStage.setResizable(false);

        HBox resolutionTile = new HBox();
        Text resolution = new Text("Screen resolution   ");

        size.getItems().addAll(Size.values());
        size.setValue(Size.Small_800x400);
        resolutionTile.getChildren().addAll(resolution, size);
        resolutionTile.setAlignment(Pos.CENTER_LEFT);
        resolutionTile.setSpacing(10.0);

        HBox cellSize = new HBox();
        Text boxSize = new Text("Cell size                  ");

        cSize.getItems().addAll(CellSize.values());
        cSize.setValue(CellSize.Large);
        cellSize.getChildren().addAll(boxSize, cSize);
        cellSize.setAlignment(Pos.CENTER_LEFT);
        cellSize.setSpacing(10.0);

        HBox loopStatus = new HBox();
        Text loopText = new Text("loop edge case      ");
        CheckBox checkBox = new CheckBox();
        loopStatus.getChildren().addAll(loopText, checkBox);
        loopStatus.setAlignment(Pos.CENTER_LEFT);
        loopStatus.setSpacing(10.0);

        HBox menu = new HBox();
        Button play = new Button("Play!");
        play.setOnAction(event -> {
            Mainview mainview = new Mainview(size.getValue().width, size.getValue().height, cSize.getValue().size, checkBox.isSelected());
            primaryStage.setTitle("Game of life");
            primaryStage.setScene(new Scene(mainview));
            primaryStage.setResizable(false);
            if (size.getValue().equals(Size.FullScreen))
                primaryStage.setFullScreen(true);
            primaryStage.show();
            mainview.draw();
        });
        Button quit = new Button("Quit");
        quit.setOnAction(event -> System.exit(0));
        menu.getChildren().addAll(play, quit);
        menu.setAlignment(Pos.CENTER_RIGHT);
        menu.setSpacing(10.0);

        this.getChildren().addAll(resolutionTile, cellSize, loopStatus, menu);
    }

    /**
     * Size enum of canvas
     */
    public enum Size {
        Small_800x400(800, 400),
        Medium_1000x600(1000, 600),
        Large_1200x800(1200, 800),
        FullScreen((int) (Mainview.SCREENX - (Mainview.SCREENX % 20)), (int) (Mainview.SCREENY - (Mainview.SCREENY % 20) - 20));

        private final int width;
        private final int height;

        Size(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }

    /**
     * resolution enum for cell size
     */
    public enum CellSize {
        Small(5),
        Medium(10),
        Large(20);

        private final int size;

        CellSize(int size) {
            this.size = size;
        }
    }
}
