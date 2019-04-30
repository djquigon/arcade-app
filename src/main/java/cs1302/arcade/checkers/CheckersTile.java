package cs1302.arcade;

import cs1302.arcade.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

/**
 *JavaDoc
 *
 */
public class CheckersTile extends Rectangle{

    public static final int TILE_WIDTH = 78; //width of a tile
    public static final int TILE_HEIGHT = 78; //height of a tile

    private CheckersBoard board; //main board reference
    private CheckersPiece piece; //piece in tile

    /**
     *JavaDoc
     *
     */
    public CheckersTile(CheckersBoard board, boolean isWhite, int x, int y){
        super();
        this.board = board;
        this.setWidth(TILE_WIDTH);
        this.setHeight(TILE_HEIGHT);
        this.relocate(x * TILE_WIDTH, y * TILE_HEIGHT);
        if(isWhite){
            this.setFill(Color.WHITE);
        }//if
        else{
            this.setFill(Color.TRANSPARENT);
        }//else
    }//Constructor

    /**
     *JavaDoc
     *
     */
    public void setPiece(CheckersPiece piece){
        this.piece = piece;
    }//SetPiece

    /**
     *JavaDoc
     *
     */
    public CheckersPiece getPiece(){
        return piece;
    }//GetPiece

    /**
     *JavaDoc
     *
     */
    public boolean isOpen(){
        return this.getPiece() == null;
    }//IsOpen

}//Class
