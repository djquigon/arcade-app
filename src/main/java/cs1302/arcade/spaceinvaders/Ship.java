package cs1302.arcade;

import cs1302.arcade.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.geometry.Pos;

/**
 * Represents a ship for a space invaders game.
 */
public class Ship extends Rectangle{

    public static final double SHIP_SPEED = 15; //speed the ship moves
    public static final double LASER_SPEED = 10; //speed a laser from a ship moves

    private Image ship; //image of the ship
    private int currentX; //?could remove this
    private boolean movingRight; //moving right?
    private boolean movingLeft; //moving left?

    /**
     * Creates a ship for a spaces invaders game.
     *
     * @param stage a reference to the main stage
     */
    public Ship(SpaceStage stage){
        super(50, 50);
        ship = new Image("spaceinvaders_ship.png", 50, 50, true, false);
        this.setFill(new ImagePattern(ship));
        currentX = 0;
        movingRight = false;
        movingLeft = false;
        stage.getMain().getChildren().add(this);
        stage.getMain().setAlignment(this, Pos.BOTTOM_CENTER);
    }

    /**
     * Updates the position of the ship.
     */
    public void update(){
        if(movingRight){
            currentX += SHIP_SPEED;
            this.setTranslateX(currentX); //getTranslateX
        }
        if(movingLeft){
            currentX -= SHIP_SPEED;
            this.setTranslateX(currentX);
        }
    }

    /**
     * Sets whether the ship is moving right.
     *
     * @param b true or false if its moving right
     */
    public void setMovingRight(boolean b){
        this.movingRight = b;
    }

    /**
     * Gets whether the ship is moving right.
     */
    public boolean getMovingRight(){
        return movingRight;
    }

    /**
     * Sets whether the ship is moving left.
     *
     * @param b true or false if its moving left
     */
    public void setMovingLeft(boolean b){
        this.movingLeft = b;
    }

    /**
     * Gets whether the ship is moving left.
     */
    public boolean getMovingLeft(){
        return movingLeft;
    }

    /**
     * Returns the current x position of the ship.
     */
    public int getCurrentX(){
        return currentX;
    }
}
