package cs1302.arcade;

import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class CheckersStage extends Stage{

    Group layout;
    MenuBar menuBar;

    public CheckersStage(){
        super();
        layout = new Group();
        this.setMenuBar();
        layout.getChildren().addAll(menuBar);
        Scene window = new Scene(layout, 800, 680);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Tetris");
        this.setMinWidth(800);
        this.setMinHeight(680);
        this.setResizable(false);
        this.sizeToScene();
        this.setScene(window);
    }

    public void setMenuBar(){
        menuBar = new MenuBar();
        Menu file = new Menu("File");
        MenuItem exit = new MenuItem("Exit to Main Menu");
        exit.setOnAction(e -> this.close());
        file.getItems().add(exit);
        Menu help = new Menu("Help");
        MenuItem about = new MenuItem("How to Play");
        help.getItems().add(about);
        menuBar.getMenus().addAll(file, help);
        menuBar.prefWidthProperty().bind(this.widthProperty());
    }

}
