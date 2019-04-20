package cs1302.arcade;

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
import cs1302.arcade.*;
//
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.util.Duration;
import javafx.scene.control.Separator;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
//

public class TetrisStage extends Stage{

    public static final int HEIGHT = 20;
    public static final int WIDTH = 10;
    
    EventHandler<ActionEvent> handler;
    Scene scene;
    MenuBar menuBar;
    KeyFrame keyFrame;
    Timeline bmTimeline;
    int iteration = 1;
    int scoreTotal;
    Text score;
    HBox main;
    VBox board;
    VBox window;
    VBox info;
    Button pauseStart;
    Separator sep;
    Rectangle[][] rect;
    
    public TetrisStage(){
        super();
        this.setMenuBar(); //initializes menuBar
        //This should go in board class just experimenting here
        rect = new Rectangle[HEIGHT][WIDTH];
        board = new VBox();
        for (int i = 0; i < HEIGHT; i++) {
            HBox row = new HBox();
            for (int j = 0; j < WIDTH; j++) {
                rect[i][j] = new Rectangle(30.0,30.0, Color.TRANSPARENT);
                rect[i][j].setStroke(Color.BLACK);
                row.getChildren().add(rect[i][j]);
            }
            board.getChildren().add(row);
        }    

        //Gonna need this so seperate to call multiple times
        
        /*for (int i = 0; i < rect.length; i++) {
            hBox = new HBox();
            for (int j = 0; j < rect[0].length; j++) {
                hBox.getChildren().add(rect[i][j]);
            }
            vBox.getChildren().add(hBox);

            }*/
        window = new VBox();
        main = new HBox();
        //declare board here when class is made
        sep = new Separator(Orientation.VERTICAL);
        scoreTotal = 0;
        score = new Text("Score: " + scoreTotal);
        score.setFont(new Font(30));
        pauseStart = new Button("Pause Game");
        pauseStart.setOnAction(e -> {
                if(pauseStart.getText().equals("Pause Game")){
                    pauseStart.setText("Unpause Game");
                    bmTimeline.stop();
                }
                else{
                    pauseStart.setText("Pause Game");
                    bmTimeline.play();
                }
            });
        info = new VBox(25);
        info.setMargin(score, new Insets(0, 100, 0, 100));
        info.setMargin(pauseStart, new Insets(0, 100, 0, 110));
        info.getChildren().addAll(score, pauseStart);
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
        this.blockMove();
    }

     public void blockMove(){
         handler = new EventHandler<ActionEvent>(){
                 @Override
                 public void handle(ActionEvent event){
                     if(iteration == HEIGHT - 1){
                         iteration = 1;
                         return;
                     }
                     int currIteration = iteration;
                     for(int i = iteration; i < currIteration+1; i++){
                         //Filling
                         for(int j = 4; j< 7; j++){
                             if(j == 4){
                                 rect[i-1][j].setFill(Color.RED);
                             }
                             rect[i][j].setFill(Color.RED);
                         }//for j
                         //Animation
                         for(int w = 6; w > 3; w--){
                             //Moving down
                             rect[i+1][w].setFill(Color.RED);
                             if(w == 4){
                                 rect[i-1][w].setFill(Color.TRANSPARENT);
                                 //Preserving 2 value
                                 break;
                             }
                                //Removing above
                             rect[i][w].setFill(Color.TRANSPARENT);
                             }//for white
                         iteration++;
                     }//for i
                 }//handle
             };//EventHandler
         //Intializes KeyFrame with animation being done every 2 seconds         
         keyFrame = new KeyFrame(Duration.millis(1500), handler);
         bmTimeline = new Timeline();
         bmTimeline.setCycleCount(Timeline.INDEFINITE);
         bmTimeline.getKeyFrames().add(keyFrame);
         bmTimeline.play();
     }
    
    public void setMenuBar(){
        menuBar = new MenuBar();
        Menu file = new Menu("File");
        MenuItem exit = new MenuItem("Exit to Main Menu");
        exit.setOnAction(e -> {
                this.close();
                bmTimeline.stop();
            });
        file.getItems().add(exit);
        Menu help = new Menu("Help");
        MenuItem about = new MenuItem("How to Play");
        help.getItems().add(about);
        menuBar.getMenus().addAll(file, help);
        menuBar.prefWidthProperty().bind(this.widthProperty());
    }
}
