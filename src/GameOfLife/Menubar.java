package GameOfLife;

import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.*;

public class Menubar extends MenuBar {
    private final Mainview mainview;
    FileChooser fileChooser = new FileChooser();

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
    }

    private void handleLoadClicked(ActionEvent event) {
        Window stage = this.getScene().getWindow();
        fileChooser.setTitle("Load Dialog");
        fileChooser.setInitialFileName("mysave");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file", "*.txt"));
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

    private void handleSaveClicked(ActionEvent event) {
        Window stage = this.getScene().getWindow();
        fileChooser.setTitle("Save Dialog");
        fileChooser.setInitialFileName("mysave");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("save file", "*.gof"));
        try {
            File file = fileChooser.showSaveDialog(stage);
            fileChooser.setInitialDirectory(file.getParentFile());
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(mainview.getGrid().grid);
            outputStream.close();
        } catch (Exception ignored) {
        }
    }


}
