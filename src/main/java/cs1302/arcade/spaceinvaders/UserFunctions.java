package cs1302.arcade;

import cs1302.arcade.*;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import javafx.animation.KeyValue;
import javafx.animation.AnimationTimer;

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
    public static void checkEvents(SpaceInvadersStage stage, SpaceInvadersShip ship){
        Scene scene = stage.getScene();//get scene from stage
        scene.setOnKeyPressed(event-> {
                if(event.getCode() == KeyCode.RIGHT){ //if right arrow clicked
                    UserFunctions.moveRight(scene, ship);
                }
                if(event.getCode() == KeyCode.LEFT){ //if left arrow clicked
                    UserFunctions.moveLeft(scene, ship);
                }
                if(event.getCode() == KeyCode.SPACE){
                    UserFunctions.fireLaser(stage, ship);
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
    private static void fireLaser(SpaceInvadersStage stage, SpaceInvadersShip ship){
        Circle laser = new Circle(0, 0, 3, Color.LIME);
        laser.setTranslateX(ship.getCurrentX());
        laser.setTranslateY(SpaceInvadersStage.MAX_Y_DOWN);
        //double currentY = SpaceInvadersStage.MAX_Y_DOWN;
        stage.getMain().getChildren().add(laser); //add to stackpane
        AnimationTimer moveLaser = moveLaser(stage, laser);
        moveLaser.start();
    }
    
    
    private static AnimationTimer moveLaser(SpaceInvadersStage stage, Circle laser){
        AnimationTimer moveLaser = new AnimationTimer(){
                @Override
                public void handle(long now){
                    if(laser.getTranslateY() > SpaceInvadersStage.MAX_Y_UP){ //or not hitting an alien
                        laser.setTranslateY(laser.getTranslateY() - SpaceInvadersShip.LASER_SPEED);
                    }
                    else{
                        stage.getMain().getChildren().remove(laser);
                    }
                }
            };
        return moveLaser;
    } //moveLaser
    
}
