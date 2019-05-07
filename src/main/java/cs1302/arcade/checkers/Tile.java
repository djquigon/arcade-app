package cs1302.arcade;

import cs1302.arcade.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

/**
 * Represents a single tile for a checkers game. There are
 * 64 tiles in a board.
 */
public class Tile extends Rectangle{

    public static final int TILE_WIDTH = 78; //width of a tile
    public static final int TILE_HEIGHT = 78; //height of a tile

    private Board board; //main board reference
    private Piece piece; //piece in tile

    /**
     * Creates a tile with all properties set.
     *
     * @param board a {@code Board} object
     * @param isWhite whether the tile isWhite or not
     * @param x the x index of the tile
     * @param y the y index of the tile
     */
    public Tile(Board board, boolean isWhite, int x, int y){
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
     * Sets the piece instance variable for a tile.
     *
     * @param piece a Piece
     */
    public void setPiece(Piece piece){
        this.piece = piece;
    }//SetPiece

    /**
     * Returns the piece instance variable of a tile.
     *
     * @return the piece property of the tile
     */
    public Piece getPiece(){
        return piece;
    }//GetPiece

    /**
     * Returns whether or not a tile contains a piece.
     *
     * @return true or false if the tile doesn't contain a piece
     */
    public boolean isOpen(){
        return this.getPiece() == null;
    }//IsOpen

}//Class
