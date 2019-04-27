package cs1302.arcade;

import cs1302.arcade.*;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Font;
import javafx.geometry.Insets;

public class CheckersStage extends Stage{

    Scene scene;
    VBox window;
    HBox main;
    MenuBar menuBar;
    CheckersBoard board;
    VBox info;

    public CheckersStage(){
        super();
        window = new VBox();
        menuBar = new MenuBar();
        this.setMenuBar();
        main = new HBox();
        board = new CheckersBoard();
        this.setInfo();
        main.getChildren().addAll(board, info);
        window.getChildren().addAll(menuBar, main);
        scene = new Scene(window, 800, 680);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("C H E C K E R S");
        this.setMinWidth(800);
        this.setMinHeight(680);
        this.setResizable(false);
        this.sizeToScene();
        this.setScene(scene);
    }

    public void setMenuBar(){
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
        
    /**
     * Sets the contents of the {@code info} VBox.
     */
    public void setInfo(){
        String whoseTurn = "";
        Text turn = new Text("Turn: " + whoseTurn);
        turn.setFont(new Font(20));
        info = new VBox(25);
        info.setMargin(turn, new Insets(0, 100, 0, 50));
        info.getChildren().addAll(turn);
    }   
}
