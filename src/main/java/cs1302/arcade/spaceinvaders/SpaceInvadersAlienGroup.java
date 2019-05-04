package cs1302.arcade;

import cs1302.arcade.*;
import javafx.scene.Group;
import javafx.animation.AnimationTimer;

/**
 * This class represents a group of aliens which attack the user ship.
 */
public class SpaceInvadersAlienGroup extends Group{

    public static final int ALIENS_WIDTH = 8;
    public static final int ALIENS_HEIGHT = 5;
    public static final int ALIENS_HORZ_SPACING = 50;
    public static final int ALIENS_VERT_SPACING = 50;
    public static final int ALIENS_SPEED = 10;
    public static final int ALIENS_SPEED_DOWN = 20;
    public static final int MAX_X_LEFT = -200;
    public static final int MAX_X_RIGHT = 200;
    public static final int MAX_Y_UP = -200;
    public static final int MAX_Y_DOWN = 180;
    
    private SpaceInvadersAlien[][] aliens; //track aliens
    private int iteration = 0; //the iteration of alien group movement
    
    /**
     * Creates a group of aliens which attack the user ship.
     *
     * @param stage reference to the main stage
     */
    public SpaceInvadersAlienGroup(SpaceInvadersStage stage){ //could make enum for different types of aliens
        aliens = new SpaceInvadersAlien[ALIENS_WIDTH][ALIENS_HEIGHT];
        for(int x = 0; x < ALIENS_WIDTH; x++){ //create row of aliens
            for(int y = 0; y < ALIENS_HEIGHT; y++){ //creare columns of aliens
                aliens[x][y] = new SpaceInvadersAlien(); //create new alien
                aliens[x][y].relocate(x * ALIENS_HORZ_SPACING, y * ALIENS_VERT_SPACING); //spacing
                this.getChildren().add(aliens[x][y]); //add to group
            }
        }
        stage.getMain().getChildren().add(this); //add the group to main
        this.setTranslateX(MAX_X_LEFT); //set the initial x position
        this.setTranslateY(MAX_Y_UP); //set the initial y position
        moveAliens(this); //start moving the aliens
    }

    /**
     * Creates the animation to move the alien group across the screen.
     * 
     * @param aliens the gorup of aliens attacking the user ship
     */
    public void moveAliens(SpaceInvadersAlienGroup aliens){
        AnimationTimer moveAliens = new AnimationTimer(){
                @Override
                public void handle(long now){
                    if(iteration % 2 == 0){ //if on left side
                        moveRight(aliens);
                    }
                    if(iteration % 2 == 1){ //if on right side
                        moveLeft(aliens);
                    }
                }
            };
        moveAliens.start();
    }

    /**
     * Moves the alien group to the right.
     *
     * @param aliens the group of aliens attacking the user ship
     */
    public void moveRight(SpaceInvadersAlienGroup aliens){
        if(aliens.getTranslateX() != MAX_X_RIGHT){ //if not at max right
            aliens.setTranslateX(aliens.getTranslateX() + ALIENS_SPEED); //move right
        }
        else{
            aliens.setTranslateY(aliens.getTranslateY() + ALIENS_SPEED_DOWN); //move down
            iteration++; //set to next iteration
        }
    }

    /**
     * Moves the alien group to the left.
     *
     * @param aliens the group of aliens attacking the user ship
     */
    public void moveLeft(SpaceInvadersAlienGroup aliens){
        if(aliens.getTranslateX() != MAX_X_LEFT){ //if not at max left
            aliens.setTranslateX(aliens.getTranslateX() - ALIENS_SPEED); //move left
        }
        else{
            aliens.setTranslateY(aliens.getTranslateY() + ALIENS_SPEED_DOWN); //move down
            iteration++; //set to next iteration
        }
    }
    
}
