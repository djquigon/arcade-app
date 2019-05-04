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
import javafx.geometry.Bounds;

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
        Circle laser = new Circle(0, 0, 3, Color.LIME);
        //Boolean collision = false;
        laser.setTranslateX(ship.getCurrentX());
        laser.setTranslateY(SpaceInvadersStage.MAX_Y_DOWN);
        stage.getMain().getChildren().add(laser); //add to stackpane
        AnimationTimer moveLaser = moveLaser(stage, laser, aliens);
        moveLaser.start();
    }
    
    /**
     * Provides the {@code AnimationTimer} for the firing the laser.
     *
     * @param stage a reference to the main stage
     * @param laser the laser being fired
     */
    private static AnimationTimer moveLaser(SpaceInvadersStage stage, Circle laser, SpaceInvadersAlienGroup aliens){ //goes over
        AnimationTimer moveLaser = new AnimationTimer(){
                @Override
                public void handle(long now){
                    if(laser.getTranslateY() > SpaceInvadersStage.MAX_Y_UP){
                        laser.setTranslateY(laser.getTranslateY() - SpaceInvadersShip.LASER_SPEED);
                        checkCollisions(laser, stage, aliens);
                    }
                    else{
                        stage.getMain().getChildren().remove(laser);
                    }
                }
            };
        return moveLaser;
    } //moveLaser

    public static void checkCollisions(Circle laser, SpaceInvadersStage stage, SpaceInvadersAlienGroup aliens){ //goes over
        for(int x = 0; x < SpaceInvadersAlienGroup.ALIENS_WIDTH; x++){
            for(int y = 0; y < SpaceInvadersAlienGroup.ALIENS_HEIGHT; y++){
                if(laser.getBoundsInParent().intersects(aliens.getAlien(x,y).getBoundsInParent())){
                    stage.getMain().getChildren().remove(laser);
                    stage.getAlienGroup().getChildren().remove(aliens.getAlien(x,y));
                    System.out.println("removed");
                    return;
                    //increment score
                }
            }
        }
    }
    
}
