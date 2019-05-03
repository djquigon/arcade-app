package cs1302.arcade;

import cs1302.arcade.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.geometry.Pos;

public class SpaceInvadersShip extends Rectangle{

    public static final double SHIP_SPEED = 15;
    public static final double LASER_SPEED = .01; //something like this
    
    private UserFunctions functions;
    private Image ship;
    private int currentX;
    private boolean movingRight;
    private boolean movingLeft;
    
    public SpaceInvadersShip(SpaceInvadersStage stage){
        super(400, 670, 50, 50);
        functions = new UserFunctions();
        ship = new Image("ship.png", 50, 50, true, false);
        this.setFill(new ImagePattern(ship));
        movingRight = false;
        currentX = 0;
        stage.getMain().getChildren().add(this);
        stage.getMain().setAlignment(this, Pos.BOTTOM_CENTER);
    }

    public void update(){
        if(movingRight){
            currentX += SHIP_SPEED;
            this.setTranslateX(currentX);
        }
        if(movingLeft){
            currentX -= SHIP_SPEED;
            this.setTranslateX(currentX);
        }
    }

    public void setMovingRight(boolean b){
        this.movingRight = b;
    }

    public boolean getMovingRight(){
        return movingRight;
    }

    public void setMovingLeft(boolean b){
        this.movingLeft = b;
    }

    public boolean getMovingLeft(){
        return movingLeft;
    }

    public int getCurrentX(){
        return currentX;
    }
}
