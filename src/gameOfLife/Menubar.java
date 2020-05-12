package gameOfLife;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.*;

/**
 * MenuBar of program to save and load(save file in Object type).
 *
 * @author Narawish Sangsiriwut
 */
public class Menubar extends MenuBar {
    /**
     * mainview of board
     */
    private final Mainview mainview;
    /**
     * FileChooser for load and save
     */
    FileChooser fileChooser = new FileChooser();

    /**
     * initialize Menubar.
     */
    public Menubar(Mainview main) {
        this.mainview = main;
        Menu file = new Menu("File");
        MenuItem save = new MenuItem("Save");
        save.setOnAction(this::handleSaveClicked);
        MenuItem load = new MenuItem("Load");
        load.setOnAction(this::handleLoadClicked);
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(event -> System.exit(0));
        file.getItems().addAll(save, load, exit);
        this.getMenus().add(file);
        Menu about = new Menu("Help");
        MenuItem help = new MenuItem("Rule");
        help.setOnAction(this::showRule);
        MenuItem control = new MenuItem("Control");
        control.setOnAction(this::showControl);
        about.getItems().addAll(help, control);
        this.getMenus().add(about);
    }

    /**
     * handle load event.
     *
     * @param event load event
     */
    private void handleLoadClicked(ActionEvent event) {
        Window stage = this.getScene().getWindow();
        fileChooser.setTitle("Load Dialog");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("game file", String.format("*.gol_%s", mainview.getSize())));
        try {
            File file = fileChooser.showOpenDialog(stage);
            fileChooser.setInitialDirectory(file.getParentFile());
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
            this.mainview.getGrid().grid = (int[][]) inputStream.readObject();
            this.mainview.getGrid().day = 0;
            this.mainview.draw();
            inputStream.close();
        } catch (Exception ignored) {
        }
    }

    /**
     * handle save event.
     *
     * @param event save event
     */
    private void handleSaveClicked(ActionEvent event) {
        Window stage = this.getScene().getWindow();
        fileChooser.setTitle("Save Dialog");
        fileChooser.setInitialFileName("mysave");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("game file", String.format("*.gol_%s", mainview.getSize())));
        try {
            File file = fileChooser.showSaveDialog(stage);
            fileChooser.setInitialDirectory(file.getParentFile());
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(mainview.getGrid().grid);
            outputStream.close();
        } catch (Exception ignored) {
        }
    }

    /**
     * show rule of the game
     *
     * @param event rule click
     */
    private void showRule(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Game of life's rules");
        alert.setContentText(GameInfo.RULE);
        alert.show();
    }

    /**
     * show control
     *
     * @param event control click
     */
    private void showControl(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Control");
        alert.setContentText(GameInfo.CONTROL);
        alert.show();
    }
}
