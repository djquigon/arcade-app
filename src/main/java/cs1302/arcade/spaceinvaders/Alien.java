package cs1302.arcade;

import cs1302.arcade.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * This class represents a single alien in a space invaders game.
 */
public class Alien extends Rectangle{
    
    private Image alien;
    
    /**
     * Creates a single alien for a space invaders game.
     */ 
    public Alien(){
        //Constructs an alien
        super(40, 40);
        alien = new Image("spaceinvaders_alien.gif", 40, 40, true, false);
        this.setFill(new ImagePattern(alien));
    }//Alien Constructor
}//Class
