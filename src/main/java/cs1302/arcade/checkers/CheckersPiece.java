package cs1302.arcade;

import cs1302.arcade.*;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;

public class CheckersPiece extends StackPane{

    private CheckersBoard board;
    private Piece type;
    private Ellipse bottom;
    private Ellipse top;
    private Ellipse king;
    private Boolean isKing;
    private int currentX;
    private int currentY;
    private CheckersTile option1;
    private CheckersTile option2;

    public CheckersPiece(CheckersBoard board, Piece type, int x, int y){
        this.board = board; //gets reference to board
        this.type = type; //sets type
        this.isKing = false; //at start is not a king
        currentX = x;
        currentY = y;
        this.relocate(x * CheckersTile.TILE_WIDTH, y * CheckersTile.TILE_HEIGHT); //places on tile
        this.setTop();
        this.setBottom();
        this.getChildren().addAll(bottom, top);
        this.setOnMousePressed(e-> {
                if(type == Piece.RED && board.isRedTurn()){
                    board.removeAllHighlights();
                    this.moveRed();
                }
                if(type == Piece.BLUE && board.isBlueTurn()){
                    board.removeAllHighlights();
                    this.moveBlue();
                }
            });
    }

    public Piece getType(){
        return type;
    }

    public void setBottom(){
        bottom = new Ellipse(CheckersTile.TILE_WIDTH * .3, CheckersTile.TILE_HEIGHT * .2);
        if(type == Piece.RED){
            bottom.setFill(Color.RED);
            bottom.setStroke(Color.DARKRED);
        }
        if(type == Piece.BLUE){
            bottom.setFill(Color.ROYALBLUE);
            bottom.setStroke(Color.MEDIUMBLUE);
        }
        bottom.setStrokeWidth(CheckersTile.TILE_WIDTH * .03);
        bottom.setTranslateX((CheckersTile.TILE_WIDTH - CheckersTile.TILE_WIDTH * .3 * 2) / 2);
        bottom.setTranslateY((CheckersTile.TILE_HEIGHT - CheckersTile.TILE_HEIGHT * .2 * 2) / 1.5);
    }

    public void setTop(){
        top = new Ellipse(CheckersTile.TILE_WIDTH * .3, CheckersTile.TILE_HEIGHT * .2);
        if(type == Piece.BLUE){
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
                option1 = board.getBoardIndex(currentX+1, currentY+1);
                if(option1.isOpen()){
                    this.moveBlueAt0();
                }
                else if(option1.getPiece().getType() == Piece.RED && board.getBoardIndex(currentX+2, currentY+2).isOpen()){
                    this.attackBlue();
                }
            }//X=0

            //right corner got stuck
            else if(currentX == 7 && currentY < 6){
                option2 = board.getBoardIndex(currentX-1, currentY+1);
                if(option2.isOpen()){
                    this.moveBlueAt7();
                }
                else if(option2.getPiece().getType() == Piece.RED && board.getBoardIndex(currentX-2, currentY+2).isOpen()){
                    this.attackBlue2();
                }
            }
            
            else{
                option1 = board.getBoardIndex(currentX+1, currentY+1);
                option2 = board.getBoardIndex(currentX-1, currentY+1);
                if(option1.isOpen()){
                    this.moveBlueMid();
                }

                else if(currentX != 6 && currentY < 6){
                    if(option1.getPiece().getType() == Piece.RED && board.getBoardIndex(currentX+2, currentY+2).isOpen()){
                        this.attackBlue();
                    }
                }
                
                if(option2.isOpen()){
                    this.moveBlueMid2();
                }
                
                else if(currentX != 1 && currentY < 6){
                    if(option2.getPiece().getType() == Piece.RED && board.getBoardIndex(currentX-2, currentY+2).isOpen()){
                        this.attackBlue2();
                    }
                }
            }
        }
    }
        
    public void moveRed(){
        if(board.isRedTurn()){
            if(currentX == 0){
                option1 = board.getBoardIndex(currentX+1, currentY-1);
                if(option1.isOpen()){
                    this.moveRedAt0();
                }
                else if(option1.getPiece().getType() == Piece.BLUE && board.getBoardIndex(currentX+2, currentY-2).isOpen()){
                    this.attackRed();
                }
            }
            else if(currentX == 7){
                option2 = board.getBoardIndex(currentX-1, currentY-1);
                if(option2.isOpen()){
                    this.moveRedAt7();
                }
                else if(option2.getPiece().getType() == Piece.BLUE && board.getBoardIndex(currentX-2, currentY-2).isOpen()){
                    this.attackRed2();
                }
            }
            
            else{
                option1 = board.getBoardIndex(currentX+1, currentY-1);
                option2 = board.getBoardIndex(currentX-1, currentY-1);
                if(option1.isOpen()){
                    this.moveRedMid();
                }
                
                else if(currentX != 6 && currentY > 1){
                    if(option1.getPiece().getType() == Piece.BLUE && board.getBoardIndex(currentX+2, currentY-2).isOpen()){
                        this.attackRed();
                        System.out.println(currentX);
                    }
                }
                
                if(option2.isOpen()){
                    this.moveRedMid2();
                }
                
                else if(currentX != 1 && currentY > 1){
                    if(option2.getPiece().getType() == Piece.BLUE && board.getBoardIndex(currentX-2, currentY-2).isOpen()){
                        this.attackRed2();
                        System.out.println(currentX);
                    }
                }
            }
        }
    }
    
    public void moveRedAt0(){
        option1.setStrokeWidth(3);
        option1.setOnMousePressed(e-> {
                if(board.isRedTurn() && option1 != null){
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
                    this.removeOptions();
                    board.removeAllHighlights();
                }
            });
    }
    
    public void attackRed(){
        option1 = board.getBoardIndex(currentX+2, currentY-2);
        option1.setStrokeWidth(3);
        option1.setOnMousePressed(e-> {
                if(board.isRedTurn() && option1 != null){
                    currentX = currentX+2;
                    currentY = currentY-2;
                    if(currentY == 0){
                        this.setToKing();
                    }
                    this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                  (currentY) *  CheckersTile.TILE_HEIGHT);
                    board.getBoardIndex(currentX, currentY).setPiece(this);
                    board.getBoardIndex(currentX-1, currentY+1).getPiece().getChildren().clear();
                    board.getBoardIndex(currentX-1, currentY+1).setPiece(null);
                    board.getBoardIndex(currentX-2, currentY+2).setPiece(null);
                    option1.setStrokeWidth(0);
                    board.setIsBlueTurn(true);
                    board.setIsRedTurn(false);
                    this.removeOptions();
                    board.removeAllHighlights();
                }
            });
    }
    
    public void moveRedAt7(){
        option2.setStrokeWidth(3);
        option2.setOnMousePressed(e-> {
                if(board.isRedTurn() && option2 != null){
                    currentX--;
                    currentY--;
                    if(currentY == 0){
                        this.setToKing();
                    }
                    this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                  (currentY) *  CheckersTile.TILE_HEIGHT);
                    board.getBoardIndex(currentX, currentY).setPiece(this);
                    board.getBoardIndex(currentX+1, currentY+1).setPiece(null);
                    option2.setStrokeWidth(0);
                    board.setIsBlueTurn(true);
                    board.setIsRedTurn(false);
                    this.removeOptions();
                    board.removeAllHighlights();
                }
            });
    }
    
    public void attackRed2(){
        option2 = board.getBoardIndex(currentX-2, currentY-2);
        option2.setStrokeWidth(3);
        option2.setOnMousePressed(e-> {
                if(board.isRedTurn() && option2 != null){
                    currentX = currentX-2;
                    currentY = currentY-2;
                    if(currentY == 0){
                        this.setToKing();
                    }
                    this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                  (currentY) *  CheckersTile.TILE_HEIGHT);
                    board.getBoardIndex(currentX, currentY).setPiece(this);
                    board.getBoardIndex(currentX+1, currentY+1).getPiece().getChildren().clear();
                    board.getBoardIndex(currentX+1, currentY+1).setPiece(null);
                    board.getBoardIndex(currentX+2, currentY+2).setPiece(null);
                    option2.setStrokeWidth(0);
                    board.setIsBlueTurn(true);
                    board.setIsRedTurn(false);
                    this.removeOptions();
                    board.removeAllHighlights();
                }
            });
    }

    public void moveRedMid(){
        option1.setStrokeWidth(3);
        option1.setOnMousePressed(e-> {
                if(board.isRedTurn() && option1 != null && option2 != null){
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
                    this.removeOptions();
                    board.removeAllHighlights();
                }
            });    
    }
    
    public void moveRedMid2(){
        option2.setStrokeWidth(3);
        option2.setOnMousePressed(e-> {
                if(board.isRedTurn() && option2 != null && option1 != null){
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
                    this.removeOptions();
                    board.removeAllHighlights();
                }
            });
    }

    public void moveBlueAt0(){
            option1.setStrokeWidth(3);
            option1.setOnMousePressed(e-> {
                    if(board.isBlueTurn() && option1 != null){
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
                        this.removeOptions();
                        board.removeAllHighlights();
                    }
                });   
    }
    
    public void moveBlueAt7(){
            option2.setStrokeWidth(3);
            option2.setOnMousePressed(e-> {
                    if(board.isBlueTurn() && option2 != null){
                        currentX--;
                        currentY++;
                        if(currentY == 7){
                            this.setToKing();
                        }
                        this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                      (currentY) *  CheckersTile.TILE_HEIGHT);
                        board.getBoardIndex(currentX, currentY).setPiece(this);
                        board.getBoardIndex(currentX+1, currentY-1).setPiece(null);
                        option2.setStrokeWidth(0);
                        board.setIsRedTurn(true);
                        board.setIsBlueTurn(false);
                        this.removeOptions();
                        board.removeAllHighlights();
                    }
                });
    }

    public void attackBlue(){
        option1 = board.getBoardIndex(currentX+2, currentY+2);
        option1.setStrokeWidth(3);
        option1.setOnMousePressed(e-> {
                if(board.isBlueTurn() && option1 != null){
                    currentX = currentX+2;
                    currentY = currentY+2;
                    if(currentY == 7){
                        this.setToKing();
                    }
                    this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                  (currentY) *  CheckersTile.TILE_HEIGHT);
                    board.getBoardIndex(currentX, currentY).setPiece(this);
                    board.getBoardIndex(currentX-1, currentY-1).getPiece().getChildren().clear();
                    board.getBoardIndex(currentX-1, currentY-1).setPiece(null);
                    board.getBoardIndex(currentX-2, currentY-2).setPiece(null);
                    option1.setStrokeWidth(0);
                    board.setIsBlueTurn(false);
                    board.setIsRedTurn(true);
                    this.removeOptions();
                    board.removeAllHighlights();
                }
            });
    }

    public void attackBlue2(){
        option2 = board.getBoardIndex(currentX-2, currentY+2);
        option2.setStrokeWidth(3);
        option2.setOnMousePressed(e-> {
                if(board.isBlueTurn() && option2 != null){
                    currentX = currentX-2;
                    currentY = currentY+2;
                    if(currentY == 7){
                        this.setToKing();
                    }
                    this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                  (currentY) *  CheckersTile.TILE_HEIGHT);
                    board.getBoardIndex(currentX, currentY).setPiece(this);
                    board.getBoardIndex(currentX+1, currentY-1).getPiece().getChildren().clear();
                    board.getBoardIndex(currentX+1, currentY-1).setPiece(null);
                    board.getBoardIndex(currentX+2, currentY-2).setPiece(null);
                    option2.setStrokeWidth(0);
                    board.setIsBlueTurn(false);
                    board.setIsRedTurn(true);
                    this.removeOptions();
                    board.removeAllHighlights();
                }
            });
    }
    
    public void moveBlueMid(){
        option1.setStrokeWidth(3);
        option1.setOnMousePressed(e-> {
                if(board.isBlueTurn() && option1 != null && option2 != null){
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
                    this.removeOptions();
                    board.removeAllHighlights();
                }
            }); 
    }

    public void moveBlueMid2(){
        option2.setStrokeWidth(3);
        option2.setOnMousePressed(e-> {
                if(board.isBlueTurn() && option1 != null && option2 != null){
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
                    this.removeOptions();
                    board.removeAllHighlights();
                }
            });
    }
    

    public void removeOptions(){
        option1 = null;
        option2 = null;
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
