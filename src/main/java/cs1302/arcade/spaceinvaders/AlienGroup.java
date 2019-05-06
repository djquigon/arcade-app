package cs1302.arcade;

import cs1302.arcade.*;
import javafx.scene.Group;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import java.lang.Math;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.application.Platform;
import javafx.animation.KeyFrame;

/**
 * This class represents a group of aliens which attack the user ship.
 */
public class AlienGroup extends Group{

    public static final int ALIENS_WIDTH = 8;
    public static final int ALIENS_HEIGHT = 5;
    public static final int ALIENS_HORZ_SPACING = 50;
    public static final int ALIENS_VERT_SPACING = 50;
    public static final int ALIENS_SPEED = 5;
    public static final int ALIENS_SPEED_DOWN = 20;
    public static final int MAX_X_LEFT = -200;
    public static final int MAX_X_RIGHT = 200;
    public static final int MAX_Y_UP = -200;
    public static final int MAX_Y_DOWN = 180;
    
    private Alien[][] aliens; //track aliens
    private int iteration = 0; //the iteration of alien group movement
    
    /**
     * Creates a group of aliens which attack the user ship.
     *
     * @param stage reference to the main stage
     * @param ship reference to the ship
     */
    public AlienGroup(SpaceStage stage, Ship ship){ //could make enum for different types of aliens
        aliens = new Alien[ALIENS_WIDTH][ALIENS_HEIGHT];
        for(int x = 0; x < ALIENS_WIDTH; x++){ //create row of aliens
            for(int y = 0; y < ALIENS_HEIGHT; y++){ //creare columns of aliens
                aliens[x][y] = new Alien(); //create new alien
                aliens[x][y].relocate(x * ALIENS_HORZ_SPACING, y * ALIENS_VERT_SPACING); //spacing
                this.getChildren().add(aliens[x][y]); //add to group
            }
        }
        stage.getMain().getChildren().add(this); //add the group to main
        this.setTranslateX(MAX_X_LEFT); //set the initial x position
        this.setTranslateY(MAX_Y_UP); //set the initial y position
        moveAliens(this); //start moving the aliens
        alienAttack(stage, ship, this);
    }

    /**
     * Creates the animation to move the alien group across the screen.
     * 
     * @param aliens the group of aliens attacking the user ship
     */
    public void moveAliens(AlienGroup aliens){
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
    public void moveRight(AlienGroup aliens){
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
    public void moveLeft(AlienGroup aliens){
        if(aliens.getTranslateX() != MAX_X_LEFT){ //if not at max left
            aliens.setTranslateX(aliens.getTranslateX() - ALIENS_SPEED); //move left
        }
        else{
            aliens.setTranslateY(aliens.getTranslateY() + ALIENS_SPEED_DOWN); //move down
            iteration++; //set to next iteration
        }
    }
    
    /**
     * Returns the alien array that tracks the full group of aliens.
     *
     * @return the aliens array
     * @param x the x position in the array
     * @param y the y position in the array
     */
    public Alien getAlien(int x, int y){
        return aliens[x][y];
    }

    /**
     * Sets the alien at the given indexes to null.
     *
     * @param x the x position
     * @param y the y position
     */
    public void removeAlien(int x, int y){
        aliens[x][y] = null;
    }

    /**
     * Selects a random alien and makes it shoot a laser.
     *
     * @param stage reference to the main stage
     * @param ship reference to the ship
     * @param aliens reference to alien group attacking the ship
     */
    public void alienAttack(SpaceStage stage, Ship ship, AlienGroup aliens){
        EventHandler<ActionEvent> handler = event -> {
            Runnable r = () -> {
                Platform.runLater(()-> {
                        int x = (int)(Math.random()*8);
                        int y = (int)(Math.random()*5);
                        if(getAlien(x,y) == null){
                            x = (int)(Math.random()*8);
                            y = (int)(Math.random()*5);
                        }
                        Rectangle laser = new Rectangle(5, 10);
                        laser.setTranslateX((aliens.getTranslateX()-175) + (x * ALIENS_HORZ_SPACING));
                        System.out.println("x "+ x);
                        laser.setTranslateY((aliens.getTranslateY()-100) + (y * ALIENS_VERT_SPACING));
                        System.out.println("y " + y);
                        laser.setFill(Color.RED);
                        stage.getMain().getChildren().add(laser); //add to stackpane
                        AnimationTimer moveLaser = moveLaser(stage, ship, laser);
                        moveLaser.start();
                    });
            };
            Thread t = new Thread(r);
            System.out.println("new thread");
            t.setDaemon(true);
            t.start();
        };
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(5), handler);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    /**
     * Moves laser away from aliens and towards the ship.
     *
     * @return the animation timer in charge of moving the laser
     * @param stage reference to the main stage
     * @param ship reference to the ship
     * @param laser reference to the laser that is being moved
     */
    private static AnimationTimer moveLaser(SpaceStage stage, Ship ship, Rectangle laser){ //goes over
        AnimationTimer moveLaser = new AnimationTimer(){
                @Override
                public void handle(long now){
                    if(laser.getTranslateY() < SpaceStage.MAX_Y_DOWN){ //while still on screen
                        laser.setTranslateY(laser.getTranslateY() + Ship.LASER_SPEED);
                        shipCollision(stage,ship,laser,this);                        
                    }
                    else{ //when off screen
                        stage.getMain().getChildren().remove(laser);
                    }
                }
            };
        return moveLaser;
    } //moveLaser

    /**
     * Checks if the laser has collided with the ship.
     *
     * @param stage reference to the main stage
     * @param ship reference to the ship
     * @param laser reference to the laser being moved
     * @param moveLaser reference to the animation timer in charge of moving laser
     */
    public static void shipCollision(SpaceStage stage, Ship ship, Rectangle laser, AnimationTimer moveLaser){
        if(((Path)Shape.intersect(laser, ship)).getElements().size() > 0){
            stage.getMain().getChildren().remove(laser);
            stage.getMain().getChildren().remove(ship);
            laser = null;
            ship = null;
            moveLaser.stop();
            return;
        }
    }
    
}
