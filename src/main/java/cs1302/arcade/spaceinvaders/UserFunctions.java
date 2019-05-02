package cs1302.arcade;

import cs1302.arcade.*;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;

/**
 *
 */
public class UserFunctions{

    /**
     *
     */
    public static void checkEvents(SpaceInvadersStage ref, SpaceInvadersShip ship){
        Scene scene = ref.getScene();//get scene from stage
        scene.setOnKeyPressed(event-> {
                if(event.getCode() == KeyCode.RIGHT){//if clicked right
                    if(ship.getCurrentX() != SpaceInvadersShip.MAX_X_RIGHT){//if not at right end
                        ship.setMovingRight(true);
                        ship.update();
                    }
                    scene.setOnKeyReleased(e-> {
                            ship.setMovingRight(false); //had to set both b/c of movement error
                            ship.setMovingLeft(false);
                        });
                }
                if(event.getCode() == KeyCode.LEFT){//if clicked left
                    if(ship.getCurrentX() != SpaceInvadersShip.MAX_X_LEFT){//if not at left end
                        ship.setMovingLeft(true);
                        ship.update();
                    }
                    scene.setOnKeyReleased(e-> {
                            ship.setMovingRight(false); //had to set both b/c of movement error
                            ship.setMovingLeft(false);
                        });
                }
            });
    }    
}
