package cs1302.arcade;

import cs1302.arcade.*;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.shape.Rectangle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.control.Separator;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;

public class TetrisStage extends Stage{
    
    private Scene scene; //the main scene
    private VBox window; //window
    private MenuBar menuBar; //menubar
    private HBox main; //contains board, sep and info
    private TetrisBoard board; //the board
    private Separator sep; //separator between board and info
    private VBox info; //contains score, controls, etc.

    /**
     * Creates the main Stage for the Lano tetris game.
     */
    public TetrisStage(){
        super();
        this.setMenuBar(); //initializes menuBar
        window = new VBox();
        main = new HBox();
        board = new TetrisBoard();
        sep = new Separator(Orientation.VERTICAL);
        this.setInfo(); //initializes info
        main.getChildren().addAll(board, sep, info);
        window.getChildren().addAll(menuBar, main);
        main.setMargin(board, new Insets(10,75,0,75));
        scene = new Scene(window, 800, 680);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("T e t r i s");
        this.setMinWidth(800);
        this.setMinHeight(680);
        this.setResizable(false);
        this.sizeToScene();
        this.setScene(scene);
        board.handleMove();
        board.blockMove();
    }

    /**
     * Sets the contents of the {@code info} VBox.
     */
    public void setInfo(){
        int scoreTotal = 0;
        Text score = new Text("Score: " + scoreTotal);
        score.setFont(new Font(30));
        Button pauseStart = new Button("Pause Game");
        pauseStart.setOnAction(e -> {
                if(pauseStart.getText().equals("Pause Game")){
                    pauseStart.setText("Unpause Game");
                    board.getBmTimeline().stop();
                }
                else{
                    pauseStart.setText("Pause Game");
                    board.getBmTimeline().play();
                }
            });
        info = new VBox(25);
        info.setMargin(score, new Insets(0, 100, 0, 100));
        info.setMargin(pauseStart, new Insets(0, 120, 0, 110));
        info.getChildren().addAll(score, pauseStart);
    }

    /**
     * Sets the contents of the {@code MenuBar}.
     */
    public void setMenuBar(){
        menuBar = new MenuBar();
        Menu file = new Menu("File");
        MenuItem exit = new MenuItem("Exit to Main Menu");
        exit.setOnAction(e -> {
                this.close();
                board.getBmTimeline().stop();
            });
        file.getItems().add(exit);
        Menu help = new Menu("Help");
        MenuItem about = new MenuItem("How to Play");
        help.getItems().add(about);
        menuBar.getMenus().addAll(file, help);
        menuBar.prefWidthProperty().bind(this.widthProperty());
    }

    public Scene getTetrisScene(){
        return scene;
    }
}
