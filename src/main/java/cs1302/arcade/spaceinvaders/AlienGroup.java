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
    
    //Constants
    public static final int ALIENS_WIDTH = 8;
    public static final int ALIENS_HEIGHT = 5;
    public static final int ALIENS_HORZ_SPACING = 55;
    public static final int ALIENS_VERT_SPACING = 50;
    public static final int ALIENS_SPEED_DOWN = 20;
    public static final int MAX_X_LEFT = -200;
    public static final int MAX_X_RIGHT = 200;
    public static final int MAX_Y_UP = -200;
    public static final int MAX_Y_DOWN = 180;
    
    //Instance variables
    private SpaceStage stage;
    private int aliensLeft;
    private Alien[][] aliens; //track aliens
    private int iteration; //the iteration of alien group movement
    private int aliensSpeed;
    private AnimationTimer moveAliens;
    private Timeline alienAttack;
    
    /**
     * Creates a group of aliens which attack the user ship.
     *
     * @param stage reference to the main stage
     * @param ship reference to the ship
     */
    public AlienGroup(SpaceStage stage, Ship ship){
        //Alien Group constructor
        this.stage = stage;
        aliens = new Alien[ALIENS_WIDTH][ALIENS_HEIGHT];
        for(int x = 0; x < ALIENS_WIDTH; x++){ //create row of aliens
            for(int y = 0; y < ALIENS_HEIGHT; y++){ //creare columns of aliens
                aliens[x][y] = new Alien(); //create new alien
                aliens[x][y].relocate(x * ALIENS_HORZ_SPACING, y * ALIENS_VERT_SPACING); //spacing
                this.getChildren().add(aliens[x][y]); //add to group
            }//for
        }//for
        stage.getMain().getChildren().add(this); //add the group to main
        this.setTranslateX(MAX_X_LEFT); //set the initial x position
        this.setTranslateY(MAX_Y_UP); //set the initial y position
        aliensLeft = 40;
        iteration = 0;
        aliensSpeed = (5 * stage.getLevel());
        moveAliens = moveAliens(this); //start moving the aliens
        moveAliens.start();
        alienAttack(stage, ship, this);
    }//AlienGroup Constructor

    /**
     * Creates the animation to move the alien group across the screen.
     * 
     * @param aliens the group of aliens attacking the user ship
     */
    public AnimationTimer moveAliens(AlienGroup aliens){
        //Animation for moving aliens
        AnimationTimer moveAliens = new AnimationTimer(){
                @Override
                public void handle(long now){                  
                    if(iteration % 2 == 0){ //if on left side
                        moveRight(aliens);
                        
                    }//if
                    if(iteration % 2 == 1){ //if on right side
                        moveLeft(aliens);
                    }//if
                }//handle
            };//AnimationTimer
        return moveAliens;
    }//MoveAliens Animation

    /**
     * Moves the alien group to the right.
     *
     * @param aliens the group of aliens attacking the user ship
     */
    public void moveRight(AlienGroup aliens){
        if(aliens.getTranslateX() <= MAX_X_RIGHT){ //if not at max right
            aliens.setTranslateX(aliens.getTranslateX() + aliensSpeed); //move right
        }//if
        else{
            aliens.setTranslateY(aliens.getTranslateY() + ALIENS_SPEED_DOWN); //move down
            iteration++; //set to next iteration
        }//else
    }//MoveRight

    /**
     * Moves the alien group to the left.
     *
     * @param aliens the group of aliens attacking the user ship
     */
    public void moveLeft(AlienGroup aliens){
        if(aliens.getTranslateX() >= MAX_X_LEFT){ //if not at max left
            aliens.setTranslateX(aliens.getTranslateX() - aliensSpeed); //move left
        }//if
        else{
            aliens.setTranslateY(aliens.getTranslateY() + ALIENS_SPEED_DOWN); //move down
            iteration++; //set to next iteration
        }//else
    }//MoveLeft
    
    /**
     * Returns the alien at the specified spot on array.
     *
     * @return alien at the specified position in array
     * @param x the x position in the array
     * @param y the y position in the array
     */
    public Alien getAlien(int x, int y){
        return aliens[x][y];
    }//GetAlien
    
    /**
     * Sets the alien at the given indexes to null and
     * checks if there are any aliens remaining on the map.
     *
     * @param x the x position
     * @param y the y position
     */
    public void removeAlien(int x, int y){
        //Removes alien and updates score
        aliens[x][y] = null;
        aliensLeft--;
        stage.setScore(stage.getScore() + (10 * stage.getLevel()));
        stage.setScoreText(stage.getScore());
        //If all aliens are gone from group, then level up
        if(aliensLeft == 0){
            moveAliens.stop();
            alienAttack.stop();
            stage.levelUp();
        }//if
    }//RemoveAlien
    
    /**
     * Selects a random alien and makes it shoot a laser.
     *
     * @param stage reference to the main stage
     * @param ship reference to the ship
     * @param aliens reference to alien group attacking the ship
     */
    public void alienAttack(SpaceStage stage, Ship ship, AlienGroup aliens){
        Runnable r = () -> {
            Platform.runLater(()-> {     
                    EventHandler<ActionEvent> handler = event -> {
                        randomAlienFire(stage, ship, aliens);
                    };//EventHandler
                    KeyFrame keyFrame = new KeyFrame(Duration.seconds(3), handler);
                    alienAttack = new Timeline();
                    alienAttack.setCycleCount(Timeline.INDEFINITE);
                    alienAttack.getKeyFrames().add(keyFrame);
                    alienAttack.play();
                });//Platform Run Later
        };//Runnable
        Thread t = new Thread(r);
        t.setDaemon(true);
        t.start();
    }//AlienAttack
    
    /**
     * Moves laser away from aliens and towards the ship.
     *
     * @return the animation timer in charge of moving the laser
     * @param stage reference to the main stage
     * @param ship reference to the ship
     * @param laser reference to the laser that is being moved
     */
    private AnimationTimer moveLaser(SpaceStage stage, Ship ship, Rectangle laser){
        //Animation for moving laser
        AnimationTimer moveLaser = new AnimationTimer(){
                @Override
                public void handle(long now){
                    //Checks if laser is on screen
                    if(laser.getTranslateY() < SpaceStage.MAX_Y_DOWN){
                        laser.setTranslateY(laser.getTranslateY() + Ship.LASER_SPEED);
                        shipCollision(stage,ship,laser,this);                        
                    }//if
                    //Checks if laser is off screen
                    else{ 
                        stage.getMain().getChildren().remove(laser);
                    }//else
                }//handle
            };//AnimationTimer
        return moveLaser;
    }//MoveLaser

    /**
     * Checks if the laser has collided with the ship or if an alien
     * ship has collided with the ship.
     *
     * @param stage reference to the main stage
     * @param ship reference to the ship
     * @param laser reference to the laser being moved
     * @param moveLaser reference to the animation timer in charge of moving laser
     */
    public void shipCollision(SpaceStage stage,Ship ship,Rectangle laser,AnimationTimer moveLaser){
        //Checks if alien laser has collided with ship
        if(((Path)Shape.intersect(laser, ship)).getElements().size() > 0){
            stage.getMain().getChildren().remove(laser);
            laser = null;
            moveLaser.stop();
            //Drops lives
            stage.setLives(stage.getLives() -1);
            stage.setLivesText(stage.getLives());
            //Checks if user has lost all lives
            if(stage.getLives() == 0){
                stage.getMain().getChildren().remove(ship);
                ship = null;
                moveAliens.stop();
                alienAttack.stop();
                moveLaser.stop();
                stage.lose();
            }//if
            return;
        }//if
        for(int x = 0; x < ALIENS_WIDTH; x++){ //create row of aliens
            for(int y = 0; y < ALIENS_HEIGHT; y++){ //creare columns of aliens
                if(getAlien(x,y) != null){
                    //Checks if alien has collided with ship,
                    //if so, then user loses
                    if(((Path)Shape.intersect(getAlien(x,y), ship)).getElements().size() > 0){
                        stage.getMain().getChildren().remove(ship);
                        ship = null;
                        moveAliens.stop();
                        alienAttack.stop();
                        moveLaser.stop();
                        stage.lose();
                        return;
                    }//if
                }//if
            }//for
        }//for
    }//ShipCollision
    
    /**
     * Sets the x and y to the location of a random existing alien.
     * Then fires a laser towards the user.
     *
     * @param stage the main stage
     * @param ship the user's ship
     * @param aliens the group of aliens
     */
    public void randomAlienFire(SpaceStage stage, Ship ship, AlienGroup aliens){
        //Selects random alien to fire
        int x = (int)(Math.random()*8);
        int y = (int)(Math.random()*5);
        if(getAlien(x,y) == null){
            while(true){
                x = (int)(Math.random()*8);
                y = (int)(Math.random()*5);
                if(getAlien(x,y) != null){
                    break;
                }//if
                else{
                    continue;
                }//else
            }//while
        }//if
        Rectangle laser = new Rectangle(5, 12);
        laser.setTranslateX((aliens.getTranslateX()-175) + (x * ALIENS_HORZ_SPACING));
        laser.setTranslateY((aliens.getTranslateY()-100) + (y * ALIENS_VERT_SPACING));
        laser.setFill(Color.RED);
        stage.getMain().getChildren().add(laser); //add to stackpane
        AnimationTimer moveLaser = moveLaser(stage, ship, laser);
        moveLaser.start();
    }//RandomAlienFire
    
    /**
     * Returns the moveAliens {@code AnimationTimer}
     *
     * @return the animation timer to move aliens.
     */
    public AnimationTimer getMoveAliens(){
        return moveAliens;
    }//GetMoveAliens
    
    /**
     * Returns the alienAttack {@code AnimationTimer}
     *
     * @return the timeline for attacking the user
     */
    public Timeline getAlienAttack(){
        return alienAttack;
    }//GetAlienAttack

    /**
     * Repopulates the alien group with new aliens.
     *
     * @param stage a reference to the main stage
     * @param ship a reference to the ship
     */
    public void repopulate(SpaceStage stage, Ship ship){
        aliensLeft = 40;
        aliensSpeed = (5 * stage.getLevel());
        iteration = 0;
        for(int x = 0; x < ALIENS_WIDTH; x++){ //create row of aliens
            for(int y = 0; y < ALIENS_HEIGHT; y++){ //creare columns of aliens
                aliens[x][y] = new Alien(); //create new alien
                aliens[x][y].relocate(x * ALIENS_HORZ_SPACING, y * ALIENS_VERT_SPACING); //spacing
                this.getChildren().add(aliens[x][y]); //add to group
            }//for
        }//for
        this.setTranslateX(MAX_X_LEFT); //set the initial x position
        this.setTranslateY(MAX_Y_UP); //set the initial y position
        moveAliens = moveAliens(this); //start moving the aliens
        moveAliens.start();
        alienAttack(stage, ship, this);
    }//Repopulate
}//Class
