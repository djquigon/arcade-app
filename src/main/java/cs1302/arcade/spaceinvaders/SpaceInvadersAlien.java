package cs1302.arcade;

import cs1302.arcade.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.geometry.Pos;

/**
 * This class represents a single alien in a space invaders game.
 */
public class SpaceInvadersAlien extends Rectangle{
    
    private Image alien;
    private boolean movingRight;//?
    private boolean movingLeft;//?
    
    /**
     * Creates a single alien for a space invaders game.
     *
     * @param stage a reference to the main stage
     * @param x x position in group
     * @param y y position in group
     */ 
    public SpaceInvadersAlien(){
        super(30, 30);
        alien = new Image("spaceinvaders_alien.gif", 40, 40, true, false);
        this.setFill(new ImagePattern(alien));
        movingRight = false;
        movingLeft = false;
    }
}
    
