package GameOfLife;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Startview extends VBox {

    public Startview(Stage primaryStage) {
        this.setMinSize(300, 100);
        this.setPadding(new Insets(10.0));
        this.setAlignment(Pos.CENTER);
        this.setSpacing(5.0);

        HBox resolutionTile = new HBox();
        Text resolution = new Text("Screen resolution");
        ComboBox<Size> size = new ComboBox<>();
        size.getItems().addAll(Size.values());
        size.setValue(Size.Small_800x400);
        resolutionTile.getChildren().addAll(resolution, size);
        resolutionTile.setAlignment(Pos.CENTER_LEFT);
        resolutionTile.setSpacing(10.0);

        HBox cellSize = new HBox();
        Text boxSize = new Text("Cell size");
        ComboBox<CellSize> cSize = new ComboBox<>();
        cSize.getItems().addAll(CellSize.values());
        cSize.setValue(CellSize.Large);
        cellSize.getChildren().addAll(boxSize, cSize);
        cellSize.setAlignment(Pos.CENTER_LEFT);
        cellSize.setSpacing(10.0);

        HBox menu = new HBox();
        Button play = new Button("Play!");
        play.setOnAction(event -> {
            Mainview mainview = new Mainview(size.getValue().width, size.getValue().height, cSize.getValue().size);
            primaryStage.setTitle("Game of life");
            primaryStage.setScene(new Scene(mainview));
            primaryStage.show();
            primaryStage.setResizable(false);
            mainview.draw();
        });
        Button quit = new Button("Quit");
        quit.setOnAction(event -> System.exit(0));
        menu.getChildren().addAll(play, quit);
        menu.setAlignment(Pos.CENTER_RIGHT);
        menu.setSpacing(10.0);

        this.getChildren().addAll(resolutionTile, cellSize, menu);
    }

    public enum Size {
        Small_800x400(800, 400),
        Medium_1000x600(1000, 600),
        Large_1200x800(1200, 800);

        private final int width;
        private final int height;

        Size(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }

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
