package cs1302.arcade;

import cs1302.arcade.*;
import javafx.scene.shape.Shape;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import javafx.animation.KeyValue;
import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.scene.shape.Path;
import javafx.application.Platform;

/**
 * Represents all the possible actions the user can make pertaining to their ship.
 */
public class UserFunctions{

    /**
     * Checks for user actions and carries them out.
     *
     * @param stage a reference to the main stage
     * @param ship a reference to the main ship
     * @param aliens a reference to the alien group attacking the ship
     */
    public static void checkEvents(SpaceStage stage, Ship ship, AlienGroup aliens){ //this goes over
        Scene scene = stage.getScene();//get scene from stage
        scene.setOnKeyPressed(event-> {
                if(event.getCode() == KeyCode.RIGHT){ //if right arrow clicked
                    UserFunctions.moveRight(scene, ship);
                }
                if(event.getCode() == KeyCode.LEFT){ //if left arrow clicked
                    UserFunctions.moveLeft(scene, ship);
                }
                if(event.getCode() == KeyCode.SPACE){ //if spacebar clicked
                    UserFunctions.fireLaser(stage, ship, aliens);
                }
            });
    }

    /**
     * Moves the users ship to the right.
     *
     * @param scene a reference to the main scene
     * @param ship a reference to the main ship
     */
    private static void moveRight(Scene scene, Ship ship){
        if(ship.getCurrentX() != SpaceStage.MAX_X_RIGHT){//if not at right end
            ship.setMovingRight(true);
            ship.update();
        }
        scene.setOnKeyReleased(e-> {
                ship.setMovingRight(false); //had to set both b/c of movement error
                ship.setMovingLeft(false);
            });
    }
    
    /**
     * Moves the users ship to the left.
     *
     * @param scene a reference to the main scene
     * @param ship a reference to the main ship
     */
    private static void moveLeft(Scene scene, Ship ship){
        if(ship.getCurrentX() != SpaceStage.MAX_X_LEFT){//if not at left end
            ship.setMovingLeft(true);
            ship.update();
        }
        scene.setOnKeyReleased(e-> {
                ship.setMovingRight(false); //had to set both b/c of movement error
                ship.setMovingLeft(false);
            });
    }
    
    /**
     * Fires a laser from the ship towards the aliens.
     *
     * @param stage a reference to the main stage
     * @param ship a reference to the main ship
     * @param aliens a reference to the alien group that is attacking the ship
     */
    private static void fireLaser(SpaceStage stage, Ship ship, AlienGroup aliens){ //goes over
        Runnable r = () -> {
            Platform.runLater(()-> {
                    Rectangle laser = new Rectangle(ship.getCurrentX(), SpaceStage.MAX_Y_DOWN, 5, 7);
                    laser.setFill(Color.LIME);
                    laser.setTranslateX(ship.getCurrentX());
                    laser.setTranslateY(SpaceStage.MAX_Y_DOWN);
                    stage.getMain().getChildren().add(laser); //add to stackpane
                    AnimationTimer moveLaser = moveLaser(stage, laser, aliens);
                    moveLaser.start();
                });
        };
        Thread t = new Thread(r);
        t.setDaemon(true);
        t.start();
    }

    /**
     * Provides the {@code AnimationTimer} for the firing the laser.
     *
     * @param stage a reference to the main stage
     * @param laser reference to the laser being fired
     * @param aliens reference to the alien group attacking the ship
     */
    private static AnimationTimer moveLaser(SpaceStage stage, Rectangle laser, AlienGroup aliens){ //goes over
        AnimationTimer moveLaser = new AnimationTimer(){
                @Override
                public void handle(long now){
                    if(laser.getTranslateY() > SpaceStage.MAX_Y_UP){ //while still on screen
                        laser.setTranslateY(laser.getTranslateY() - Ship.LASER_SPEED);
                        alienCollision(laser,stage,aliens,this);
                        
                    }
                    else{ //when off screen
                        stage.getMain().getChildren().remove(laser);
                    }
                }
            };
        return moveLaser;
    } //moveLaser

    /**
     * Checks if the spaceship's laser has collided with an alien, if so
     * delete that alien.
     *
     * @param laser a reference to the laser the ship fired
     * @param stage a reference to the main stage
     * @param aliens a reference to the aliens attacking the ship
     * @param moveLaser a reference to the animation timer in charge of moving the ship's laser
     */
    public static void alienCollision(Rectangle laser, SpaceStage stage, AlienGroup aliens, AnimationTimer moveLaser){ //goes over
        for(int x = 0; x < AlienGroup.ALIENS_WIDTH; x++){
            for(int y = 0; y < AlienGroup.ALIENS_HEIGHT; y++){
                Alien alien = aliens.getAlien(x,y); //alien at x,y in the group
                if(alien != null){
                    if(((Path)Shape.intersect(laser, alien)).getElements().size() > 0){
                        stage.getMain().getChildren().remove(laser);
                        stage.getAlienGroup().getChildren().remove(alien);
                        laser = null;
                        aliens.removeAlien(x,y);
                        moveLaser.stop();
                        return;
                    }
                }
            }
        }
    }
    
}
