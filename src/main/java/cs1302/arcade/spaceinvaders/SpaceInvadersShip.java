package cs1302.arcade;

import cs1302.arcade.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.geometry.Pos;

public class SpaceInvadersShip extends Rectangle{

    public static final int MAX_X_RIGHT = 375; //375
    public static final int MAX_X_LEFT = -375; //-375
    
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
            currentX+=15;
            this.setTranslateX(currentX);
        }
        if(movingLeft){
            currentX-=15;
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
