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
//

public class TetrisStage extends Stage{

    public final int HEIGHT = 20;
    public final int WIDTH = 10;
    
    EventHandler<ActionEvent> handler;
    Group layout;
    MenuBar menuBar;
    KeyFrame keyFrame;
    Timeline timeline;
    int iteration = 1;
    HBox hb = new HBox();
    VBox vb = new VBox();
    VBox vb2 = new VBox();
    Rectangle[][] rect;
    
    public TetrisStage(){
        super();
        //This should go in board class just experimenting here
        rect = new Rectangle[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            hb = new HBox();
            for (int j = 0; j < WIDTH; j++) {
                rect[i][j] = new Rectangle(30.0,30.0, Color.TRANSPARENT);
                rect[i][j].setStroke(Color.BLACK);
                hb.getChildren().add(rect[i][j]);
            }
            vb.getChildren().add(hb);
        }    

        //Gonna need this so seperate to call multiple times
        
        /*for (int i = 0; i < rect.length; i++) {
            hBox = new HBox();
            for (int j = 0; j < rect[0].length; j++) {
                hBox.getChildren().add(rect[i][j]);
            }
            vBox.getChildren().add(hBox);

            }*/
       
         this.setMenuBar();
         //This fixes menubar
         vb2.getChildren().addAll(menuBar, vb);
         vb2.setMargin(vb, new Insets(10,0,0,75));
         
         Scene window = new Scene(vb2, 800, 680);
         this.initModality(Modality.APPLICATION_MODAL);
         this.setTitle("T e t r i s");
         this.setMinWidth(800);
         this.setMinHeight(680);
         this.setResizable(false);
         this.sizeToScene();
         this.setScene(window);
         this.blockMove();
    }

     public void blockMove(){
         handler = new EventHandler<ActionEvent>(){
                 @Override
                 public void handle(ActionEvent event){
                     if(iteration > HEIGHT - 1){
                         iteration = 0;
                     }
                     for(int i = iteration; i < i+1; i++){
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
                                 //rect[i-1][w].setFill(Color.TRANSPARENT);
                                 //Preserving 2 value
                                 break;
                             }
                                //Removing above
                             //rect[i][w].setFill(Color.TRANSPARENT);
                         }//for white
                         iteration++;
                     }//for i
                 }//handle
             };//EventHandler
         
         //Intializes KeyFrame with animation being done every 2 seconds         
         keyFrame = new KeyFrame(Duration.millis(2500), handler);
         timeline = new Timeline();
         timeline.setCycleCount(Timeline.INDEFINITE);
         timeline.getKeyFrames().add(keyFrame);
         timeline.play();
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
