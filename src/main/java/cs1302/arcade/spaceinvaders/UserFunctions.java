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
     */
    public static void checkEvents(SpaceInvadersStage stage, SpaceInvadersShip ship, SpaceInvadersAlienGroup aliens){ //this goes over
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
    private static void moveRight(Scene scene, SpaceInvadersShip ship){
        if(ship.getCurrentX() != SpaceInvadersStage.MAX_X_RIGHT){//if not at right end
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
    private static void moveLeft(Scene scene, SpaceInvadersShip ship){
        if(ship.getCurrentX() != SpaceInvadersStage.MAX_X_LEFT){//if not at left end
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
     * @param scene a reference to the main scene
     * @param ship a reference to the main ship
     */
    private static void fireLaser(SpaceInvadersStage stage, SpaceInvadersShip ship, SpaceInvadersAlienGroup aliens){ //goes over
        Runnable r = () -> {
            Platform.runLater(()-> {
                    Rectangle laser = new Rectangle(ship.getCurrentX(), SpaceInvadersStage.MAX_Y_DOWN, 5, 7);
                    laser.setFill(Color.LIME);
                    laser.setTranslateX(ship.getCurrentX());
                    laser.setTranslateY(SpaceInvadersStage.MAX_Y_DOWN);
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
     * @param laser the laser being fired
     */

    private static AnimationTimer moveLaser(SpaceInvadersStage stage, Rectangle laser, SpaceInvadersAlienGroup aliens){ //goes over
        AnimationTimer moveLaser = new AnimationTimer(){
                @Override
                public void handle(long now){
                    if(laser.getTranslateY() > SpaceInvadersStage.MAX_Y_UP){ //while still on screen
                        laser.setTranslateY(laser.getTranslateY() - SpaceInvadersShip.LASER_SPEED);
                        alienCollision(laser,stage,aliens,this);
                        
                    }
                    else{ //when off screen
                        stage.getMain().getChildren().remove(laser);
                    }
                }
            };
        return moveLaser;
    } //moveLaser

    public static void alienCollision(Rectangle laser, SpaceInvadersStage stage, SpaceInvadersAlienGroup aliens, AnimationTimer moveLaser){ //goes over
        for(int x = 0; x < SpaceInvadersAlienGroup.ALIENS_WIDTH; x++){
            for(int y = 0; y < SpaceInvadersAlienGroup.ALIENS_HEIGHT; y++){
                SpaceInvadersAlien alien = aliens.getAlien(x,y); //alien at x,y in the group
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
