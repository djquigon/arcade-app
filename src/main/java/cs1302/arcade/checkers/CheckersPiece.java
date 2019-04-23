package cs1302.arcade;

import cs1302.arcade.*;

public class CheckersPiece extends StackPane{
    
    public enum Piece{
        
        BLACK(1), //1 represents moving down
        RED(-1); //-1 represents moving up
        
        private int pieceDirection;
        
        Piece(pieceDirection){
            this.pieceDirection = pieceDirection;
        }
    } //Piece

    //private 

}
