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
    private Boolean isKing = false;
    private int currentX = 0;
    private int currentY = 0;

    public CheckersPiece(CheckersBoard board, Piece piece, int x, int y){
        this.board = board; //gets reference to board
        this.piece = piece; //sets piece
        currentX = x;
        currentY = y;
        this.relocate(x * CheckersTile.TILE_WIDTH, y * CheckersTile.TILE_HEIGHT); //places on tile
        this.setTop();
        this.setBottom();
        this.getChildren().addAll(bottom, top);
        this.setOnMousePressed(e-> {
                if(piece == Piece.RED){
                    this.moveRed();
                }
                if(piece == Piece.BLUE){
                    this.moveBlue();
                }
            });
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
        bottom.setTranslateY((CheckersTile.TILE_HEIGHT - CheckersTile.TILE_HEIGHT * .2 * 2) / 1.5);
    }

    public void setTop(){
        top = new Ellipse(CheckersTile.TILE_WIDTH * .3, CheckersTile.TILE_HEIGHT * .2);
        if(piece == Piece.BLUE){
            top.setFill(Color.BLUE);
            top.setStroke(Color.WHITE);
        }
        else{
            top.setFill(Color.RED);
            top.setStroke(Color.WHITE);
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
        if(currentX == 0){
            CheckersTile option1 = board.getBoardIndex(currentX+1, currentY+1);
            if(option1.isOpen()){
                option1.setStrokeWidth(3);
                option1.setOnMousePressed(e-> {        
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
                    });
            }
        }
        else if(currentX == 7){
            CheckersTile option1 = board.getBoardIndex(currentX-1, currentY+1);
            if(option1.isOpen()){
                option1.setStrokeWidth(3);
                option1.setOnMousePressed(e-> {        
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
                    });
            }
        }
        else{
            CheckersTile option1 = board.getBoardIndex(currentX+1, currentY+1);
            CheckersTile option2 = board.getBoardIndex(currentX-1, currentY+1);
            if(option1.isOpen()){
                option1.setStrokeWidth(3);
                option1.setOnMousePressed(e-> {        
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
                    });
            }
            if(option2.isOpen()){
                option2.setStrokeWidth(3);
                option2.setOnMousePressed(e-> {        
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
                    });
            }
        }
    }

    public void moveRed(){
        if(currentX == 0){
            CheckersTile option1 = board.getBoardIndex(currentX+1, currentY-1);
            if(option1.isOpen()){
                option1.setStrokeWidth(3);
                option1.setOnMousePressed(e-> {        
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
                    });
            }
        }
        else if(currentX == 7){
            CheckersTile option1 = board.getBoardIndex(currentX-1, currentY-1);
            if(option1.isOpen()){
                option1.setStrokeWidth(3);
                option1.setOnMousePressed(e-> {        
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
                    });
            }
        }
        else{
            CheckersTile option1 = board.getBoardIndex(currentX+1, currentY-1);
            CheckersTile option2 = board.getBoardIndex(currentX-1, currentY-1);
            if(option1.isOpen()){
                option1.setStrokeWidth(3);
                option1.setOnMousePressed(e-> {        
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
                    });
            }
            if(option2.isOpen()){
                option2.setStrokeWidth(3);
                option2.setOnMousePressed(e-> {        
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
                    });
            }
        }
    }
}



enum Piece{
    
    BLUE(1), //1 represents moving down
    RED(-1); //-1 represents moving up
    
    private int pieceDirection;
    
    Piece(int pieceDirection){
        this.pieceDirection = pieceDirection;
    }
} //Piece
