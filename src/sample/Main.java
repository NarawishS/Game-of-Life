package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Mainview mainview = new Mainview();
        Scene scene = new Scene(mainview);
        primaryStage.setTitle("Game of life");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        mainview.draw();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
