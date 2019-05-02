package cs1302.arcade;

import cs1302.arcade.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;

public class UserFunctions{

    
    public void checkEvents(SpaceInvadersShip ship){ //responds to key presses
        ship.setOnKeyPressed(event-> {
                if(event.getCode() == KeyCode.RIGHT){
                    ship.setMovingRight(true);
                    ship.setOnKeyReleased(e-> {
                            ship.setMovingRight(false);
                        });
                }
                
            });
    }    
}
