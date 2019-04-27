package cs1302.arcade;

import cs1302.arcade.*;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.paint.Color;

public class CheckersPiece extends StackPane{

    private Piece piece;
    private Ellipse bottom;
    private Ellipse top;

    public CheckersPiece(Piece piece, int x, int y){
        this.piece = piece; //sets piece
        this.relocate(x * CheckersTile.TILE_WIDTH, y * CheckersTile.TILE_HEIGHT); //places on tile
        this.setTop();
        this.setBottom();
        this.getChildren().addAll(bottom, top);
    }

    public Piece getPiece(){
        return piece;
    }

    public void setBottom(){
        bottom = new Ellipse(CheckersTile.TILE_WIDTH * .3, CheckersTile.TILE_HEIGHT * .2);
        bottom.setFill(Color.BLACK);
        bottom.setStroke(Color.WHITE);
        bottom.setStrokeWidth(CheckersTile.TILE_WIDTH * .03);
        bottom.setTranslateX((CheckersTile.TILE_WIDTH - CheckersTile.TILE_WIDTH * .3 * 2) / 2);
        bottom.setTranslateY((CheckersTile.TILE_HEIGHT - CheckersTile.TILE_HEIGHT * .2 * 2) / 1.5); //2.5
    }

    public void setTop(){
        top = new Ellipse(CheckersTile.TILE_WIDTH * .3, CheckersTile.TILE_HEIGHT * .2);
        if(piece == Piece.BLACK){
            top.setFill(Color.WHITE);
        }
        else{
            top.setFill(Color.RED);
        }
        top.setStroke(Color.WHITE);
        top.setStrokeWidth(CheckersTile.TILE_WIDTH * .03);
        top.setTranslateX((CheckersTile.TILE_WIDTH - CheckersTile.TILE_WIDTH * .3 * 2) / 2);
        top.setTranslateY((CheckersTile.TILE_HEIGHT - CheckersTile.TILE_HEIGHT * .2 * 2) / 2);
    }
}



enum Piece{
    
    BLACK(1), //1 represents moving down
    RED(-1); //-1 represents moving up
    
    private int pieceDirection;
    
    Piece(int pieceDirection){
        this.pieceDirection = pieceDirection;
    }
} //Piece
