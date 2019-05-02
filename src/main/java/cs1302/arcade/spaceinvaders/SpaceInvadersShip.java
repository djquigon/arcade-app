package cs1302.arcade;

import cs1302.arcade.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.geometry.Pos;

public class SpaceInvadersShip extends Rectangle{

    private UserFunctions functions;
    private Image ship;
    private int currentX;
    private boolean movingRight;
    
    public SpaceInvadersShip(SpaceInvadersStage stage){
        super(400, 670, 50, 50);
        functions = new UserFunctions();
        ship = new Image("ship.png", 50, 50, true, false);
        this.setFill(new ImagePattern(ship));
        movingRight = false;
        currentX = 400;
        stage.getMain().getChildren().add(this);
        stage.getMain().setAlignment(this, Pos.BOTTOM_CENTER);
        functions.checkEvents(this);
        this.update();
    }

    public void update(){
        if(movingRight){
            currentX+=5;
            this.setX(currentX);
        }
    }

    public void setMovingRight(boolean b){
        this.movingRight = b;
    }

    public boolean getMovingRight(){
        return movingRight;
    }
}
