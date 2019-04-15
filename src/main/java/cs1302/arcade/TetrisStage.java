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

public class TetrisStage extends Stage{

    public TetrisStage(){
        super();
        Group layout = new Group();
        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File");
        MenuItem exit = new MenuItem("Exit to Main Menu");
        exit.setOnAction(e -> this.close()); 
        file.getItems().add(exit);
        Menu help = new Menu("Help");
        MenuItem about = new MenuItem("How to Play");
        help.getItems().add(about);
        menuBar.getMenus().addAll(file, help);
        menuBar.prefWidthProperty().bind(this.widthProperty());
        Text temp = new Text("temp");
        temp.setX(100);
        temp.setY(100);
        Text temp1 = new Text("temp1");
        temp1.setX(500);
        temp1.setY(500);
        layout.getChildren().addAll(menuBar, temp, temp1);
        Scene window = new Scene(layout, 800, 680);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Tetris");
        this.setMinWidth(800);
        this.setMinHeight(680);
        this.setResizable(false);
        this.sizeToScene();
        this.setScene(window);
    }
   
}
