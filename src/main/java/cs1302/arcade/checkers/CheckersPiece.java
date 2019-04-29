package cs1302.arcade;

import cs1302.arcade.*;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;

public class CheckersPiece extends StackPane{

    private CheckersBoard board;
    private Piece piece;
    private Ellipse bottom;
    private Ellipse top;
    private Ellipse king;
    private Boolean isKing;
    private int currentX;
    private int currentY;

    public CheckersPiece(CheckersBoard board, Piece piece, int x, int y){
        this.board = board; //gets reference to board
        this.piece = piece; //sets piece
        this.isKing = false; //at start is not a king
        currentX = x;
        currentY = y;
        this.relocate(x * CheckersTile.TILE_WIDTH, y * CheckersTile.TILE_HEIGHT); //places on tile
        this.setTop();
        this.setBottom();
        this.getChildren().addAll(bottom, top);
        this.setOnMousePressed(e-> {
                if(piece == Piece.RED && board.isRedTurn()){
                    this.moveRed();
                }
                if(piece == Piece.BLUE && board.isBlueTurn()){
                    this.moveBlue();
                }
            });
    }

    public Piece getPiece(){
        return piece;
    }

    public void setBottom(){
        bottom = new Ellipse(CheckersTile.TILE_WIDTH * .3, CheckersTile.TILE_HEIGHT * .2);
        if(piece == Piece.RED){
            bottom.setFill(Color.RED);
            bottom.setStroke(Color.DARKRED);
        }
        if(piece == Piece.BLUE){
            bottom.setFill(Color.ROYALBLUE);
            bottom.setStroke(Color.MEDIUMBLUE);
        }
        bottom.setStrokeWidth(CheckersTile.TILE_WIDTH * .03);
        bottom.setTranslateX((CheckersTile.TILE_WIDTH - CheckersTile.TILE_WIDTH * .3 * 2) / 2);
        bottom.setTranslateY((CheckersTile.TILE_HEIGHT - CheckersTile.TILE_HEIGHT * .2 * 2) / 1.5);
    }

    public void setTop(){
        top = new Ellipse(CheckersTile.TILE_WIDTH * .3, CheckersTile.TILE_HEIGHT * .2);
        if(piece == Piece.BLUE){
            top.setFill(Color.ROYALBLUE);
            top.setStroke(Color.MEDIUMBLUE);
        }
        else{
            top.setFill(Color.RED);
            top.setStroke(Color.DARKRED);
        }
        top.setStrokeWidth(CheckersTile.TILE_WIDTH * .03);
        top.setTranslateX((CheckersTile.TILE_WIDTH - CheckersTile.TILE_WIDTH * .3 * 2) / 2);
        top.setTranslateY((CheckersTile.TILE_HEIGHT - CheckersTile.TILE_HEIGHT * .2 * 2) / 2);
    }

    public void setToKing(){
        this.isKing = true;
        king = new Ellipse(CheckersTile.TILE_WIDTH * .15, CheckersTile.TILE_HEIGHT * .1);
        Image kingImg = new Image("checkers_king.jpg", 75, 75, true, false);
        king.setFill(new ImagePattern(kingImg));
        king.setTranslateX((CheckersTile.TILE_WIDTH - CheckersTile.TILE_WIDTH * .3 * 2) / 2);
        king.setTranslateY((CheckersTile.TILE_HEIGHT - CheckersTile.TILE_HEIGHT * .2 * 2) / 2);
        this.getChildren().add(king);
    }

    public boolean getIsKing(){
        return isKing;
    }

    public void moveBlue(){
        if(board.isBlueTurn()){
            if(currentX == 0){
                CheckersTile option1 = board.getBoardIndex(currentX+1, currentY+1);
                if(option1.isOpen()){
                    option1.setStrokeWidth(3);
                    option1.setOnMousePressed(e-> {
                            if(board.isBlueTurn()){
                                currentX++;
                                currentY++;
                                if(currentY == 7){
                                    this.setToKing();
                                }
                                this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                              (currentY) *  CheckersTile.TILE_HEIGHT);
                                board.getBoardIndex(currentX, currentY).setPiece(this);
                                board.getBoardIndex(currentX-1, currentY-1).setPiece(null);
                                option1.setStrokeWidth(0);
                                board.setIsRedTurn(true);
                                board.setIsBlueTurn(false);
                            }
                        });
                }
            }
            else if(currentX == 7){
                CheckersTile option1 = board.getBoardIndex(currentX-1, currentY+1);
                if(option1.isOpen()){
                    option1.setStrokeWidth(3);
                    option1.setOnMousePressed(e-> {
                            if(board.isBlueTurn()){
                                currentX--;
                                currentY++;
                                if(currentY == 7){
                                    this.setToKing();
                                }
                                this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                              (currentY) *  CheckersTile.TILE_HEIGHT);
                                board.getBoardIndex(currentX, currentY).setPiece(this);
                                board.getBoardIndex(currentX+1, currentY-1).setPiece(null);
                                option1.setStrokeWidth(0);
                                board.setIsRedTurn(true);
                                board.setIsBlueTurn(false);
                            }
                        });
                }
            }
            else{
                CheckersTile option1 = board.getBoardIndex(currentX+1, currentY+1);
                CheckersTile option2 = board.getBoardIndex(currentX-1, currentY+1);
                if(option1.isOpen()){
                    option1.setStrokeWidth(3);
                    option1.setOnMousePressed(e-> {
                            if(board.isBlueTurn()){
                                currentX++;
                                currentY++;
                                if(currentY == 7){
                                    this.setToKing();
                                }
                                this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                              (currentY) *  CheckersTile.TILE_HEIGHT);
                                board.getBoardIndex(currentX, currentY).setPiece(this);
                                board.getBoardIndex(currentX-1, currentY-1).setPiece(null);
                                option1.setStrokeWidth(0);
                                option2.setStrokeWidth(0);
                                board.setIsRedTurn(true);
                                board.setIsBlueTurn(false);
                            }
                        });
                }
                if(option2.isOpen()){
                    option2.setStrokeWidth(3);
                    option2.setOnMousePressed(e-> {
                            if(board.isBlueTurn()){
                                currentX--;
                                currentY++;
                                if(currentY == 7){
                                    this.setToKing();
                                }
                                this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                              (currentY) *  CheckersTile.TILE_HEIGHT);
                                board.getBoardIndex(currentX, currentY).setPiece(this);
                                board.getBoardIndex(currentX+1, currentY-1).setPiece(null);
                                option1.setStrokeWidth(0);
                                option2.setStrokeWidth(0);
                                board.setIsRedTurn(true);
                                board.setIsBlueTurn(false);
                            }
                        });
                }
            } //else
        } //if
    } //moveBlue

    public void moveRed(){
        if(board.isRedTurn()){
            if(currentX == 0){
                CheckersTile option1 = board.getBoardIndex(currentX+1, currentY-1);
                if(option1.isOpen()){
                    option1.setStrokeWidth(3);
                    option1.setOnMousePressed(e-> {
                            if(board.isRedTurn()){
                                currentX++;
                                currentY--;
                                if(currentY == 0){
                                    this.setToKing();
                                }
                                this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                              (currentY) *  CheckersTile.TILE_HEIGHT);
                                board.getBoardIndex(currentX, currentY).setPiece(this);
                                board.getBoardIndex(currentX-1, currentY+1).setPiece(null);
                                option1.setStrokeWidth(0);
                                board.setIsBlueTurn(true);
                                board.setIsRedTurn(false);
                            }
                        });
                }
            } //if
            else if(currentX == 7){
                CheckersTile option1 = board.getBoardIndex(currentX-1, currentY-1);
                if(option1.isOpen()){
                    option1.setStrokeWidth(3);
                    option1.setOnMousePressed(e-> {
                            if(board.isRedTurn()){
                                currentX--;
                                currentY--;
                                if(currentY == 0){
                                    this.setToKing();
                                }
                                this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                              (currentY) *  CheckersTile.TILE_HEIGHT);
                                board.getBoardIndex(currentX, currentY).setPiece(this);
                                board.getBoardIndex(currentX+1, currentY+1).setPiece(null);
                                option1.setStrokeWidth(0);
                                board.setIsBlueTurn(true);
                                board.setIsRedTurn(false);
                            }
                        });
                }
            } //else if
            else{
                CheckersTile option1 = board.getBoardIndex(currentX+1, currentY-1);
                CheckersTile option2 = board.getBoardIndex(currentX-1, currentY-1);
                if(option1.isOpen()){
                    option1.setStrokeWidth(3);
                    option1.setOnMousePressed(e-> {
                            if(board.isRedTurn()){
                                currentX++;
                                currentY--;
                                if(currentY == 0){
                                    this.setToKing();
                                }
                                this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                              (currentY) *  CheckersTile.TILE_HEIGHT);
                                board.getBoardIndex(currentX, currentY).setPiece(this);
                                board.getBoardIndex(currentX-1, currentY+1).setPiece(null);
                                option1.setStrokeWidth(0);
                                option2.setStrokeWidth(0);
                                board.setIsBlueTurn(true);
                                board.setIsRedTurn(false);
                            }
                        });
                } //if
                if(option2.isOpen()){
                    option2.setStrokeWidth(3);
                    option2.setOnMousePressed(e-> {
                            if(board.isRedTurn()){
                                currentX--;
                                currentY--;
                                if(currentY == 0){
                                    this.setToKing();
                                }
                                this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                              (currentY) *  CheckersTile.TILE_HEIGHT);
                                board.getBoardIndex(currentX, currentY).setPiece(this);
                                board.getBoardIndex(currentX+1, currentY+1).setPiece(null);
                                option1.setStrokeWidth(0);
                                option2.setStrokeWidth(0);
                                board.setIsBlueTurn(true);
                                board.setIsRedTurn(false);
                            }
                        });
                } //if
            } //else
        } //if
    } //moveRed
}



enum Piece{
    
    BLUE(1), //1 represents moving down
    RED(-1); //-1 represents moving up
    
    private int pieceDirection;
    
    Piece(int pieceDirection){
        this.pieceDirection = pieceDirection;
    }
} //Piece
