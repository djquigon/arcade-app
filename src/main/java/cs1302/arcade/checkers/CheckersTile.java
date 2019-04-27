package cs1302.arcade;

import cs1302.arcade.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class CheckersTile extends Rectangle{

    public static final int TILE_WIDTH = 82; //width of a tile
    public static final int TILE_HEIGHT = 82; //height of a tile

    private CheckersPiece piece;

    public CheckersTile(boolean isWhite, int x, int y){
        super();
        this.setWidth(TILE_WIDTH);
        this.setHeight(TILE_HEIGHT);
        this.relocate(x * TILE_WIDTH, y * TILE_HEIGHT);
        if(isWhite){
            this.setFill(Color.WHITE);
        }
        else{
            this.setFill(Color.BLACK);
        }
    }

    public void setPiece(CheckersPiece piece){
        this.piece = piece;
    }
}
