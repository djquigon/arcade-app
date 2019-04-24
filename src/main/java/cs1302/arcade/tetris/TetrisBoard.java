package cs1302.arcade;

import cs1302.arcade.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TetrisBoard extends VBox{

    public static final int HEIGHT = 20; //the height of the board
    public static final int WIDTH = 10; //the width of the board

    private KeyFrame bmKeyFrame; //boardMove keyframe
    private Timeline bmTimeline; //boardMove Timeline
    private int iteration = 1; //blockMove iteration
    private int current = 4;
    private int moved = 0;
    private Rectangle[][] rect; //board grid
    private boolean flag = false;
    
    /**
     * Creates a VBox containing a 2D array representing the tiles of the tetris board.
     */
    public TetrisBoard(){
        super();
        rect = new Rectangle[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            HBox row = new HBox();
            for (int j = 0; j < WIDTH; j++) {
                rect[i][j] = new Rectangle(30.0,30.0, Color.TRANSPARENT);
                rect[i][j].setStroke(Color.BLACK);
                row.getChildren().add(rect[i][j]);
            }
            this.getChildren().add(row);
        }
    }
    //Gonna need this so seperate to call multiple times
    /*for (int i = 0; i < rect.length; i++) {
      hBox = new HBox();
      for (int j = 0; j < rect[0].length; j++) {
      hBox.getChildren().add(rect[i][j]);
      }
      vBox.getChildren().add(hBox);
      }
    */

    /**
     * Returns the rectangle at the specificed indexes in {@code rect}
     *
     * @param y the column
     * @param x the row
     * @return the rectangle at the given indexes
     */
    public Rectangle getBlock(int y, int x) {
      return rect[x][y];
    }

    /**
     * This moves the block. (changing this)
     */
    public void blockMove(){
       EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>(){
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
        bmKeyFrame = new KeyFrame(Duration.millis(1500), handler);
        bmTimeline = new Timeline();
        bmTimeline.setCycleCount(Timeline.INDEFINITE);
        bmTimeline.getKeyFrames().add(bmKeyFrame);
        bmTimeline.play();
               /*
               @Override
                public void handle(ActionEvent event){
                    if(iteration == HEIGHT - 1){
                        iteration = 1;
                        return;
                    }
                    int currIteration = iteration;
                    for(int i = iteration; i < currIteration+1; i++){
                        //Filling
                        for(int j = current; j < current+3; j++){
                            if(flag){
                                i--;
                                //flag = false;
                                bmTimeline.stop();
                            }
                            else{
                                if(j == current){
                                    rect[i-1][j].setFill(Color.RED);
                                }
                                rect[i][j].setFill(Color.RED);
                            }
                        }//for j
                         //Animation
                        bmTimeline.play();
                        flag = false;
                        for(int w = current + 2; w > current-3; w--){
                            //Moving down
                            rect[i+1][w].setFill(Color.RED);
                            if(w == current){
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
        bmKeyFrame = new KeyFrame(Duration.millis(1500), handler);
        bmTimeline = new Timeline();
        bmTimeline.setCycleCount(Timeline.INDEFINITE);
        bmTimeline.getKeyFrames().add(bmKeyFrame);
        bmTimeline.play();
    }
               */
    }
    /**
     * Returns the {@code blockMove} Timeline
     *
     * @return the blockMove Timeline
     */
    public Timeline getBmTimeline(){
        return bmTimeline;
    }

    
    public void handleMove(){
        this.getScene().setOnKeyPressed(e -> {
                if(e.getCode() == KeyCode.LEFT){
                    flag = true;
                    if(current == 0){
                        return;
                    }
                    current--;
                }
                if(e.getCode() == KeyCode.RIGHT){
                    current++;
                }
            });
    }
}
