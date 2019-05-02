package cs1302.arcade;

import cs1302.arcade.*;
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
    public static void checkEvents(SpaceInvadersShip ship){ //made this static, problem was we had no constructor before
        System.out.print("here");
        ship.setOnKeyPressed(event-> { //isn't going in to this, call setonkeypressed on something else?
                System.out.print("here");
                if(event.getCode() == KeyCode.RIGHT){
                    System.out.print("here");
                    ship.setMovingRight(true);
                    while(ship.getMovingRight() == true){
                        ship.update();
                    }
                    ship.setOnKeyReleased(e-> {
                            ship.setMovingRight(false);
                        });
                }
                
            });
    }    
}
