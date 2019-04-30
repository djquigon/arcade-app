package cs1302.arcade;

import cs1302.arcade.*;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;


/**
 *JavaDoc
 *
 */
enum Piece{
    
    BLUE(1), //1 represents moving down
    RED(-1); //-1 represents moving up
    
    private int pieceDirection;
    
    Piece(int pieceDirection){
        this.pieceDirection = pieceDirection;
    }
}//Piece

/**
 *JavaDoc
 *
 */
public class CheckersPiece extends StackPane{

    //Instance variables
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
    private CheckersTile option3;
    private CheckersTile option4;
    
    /**
     *JavaDoc
     *
     */
    public CheckersPiece(CheckersBoard board, Piece type, int x, int y){
        //Intializes pieces and sets them on board at proper points
        this.board = board; 
        this.type = type; 
        this.isKing = false; 
        currentX = x;
        currentY = y;
        this.relocate(x * CheckersTile.TILE_WIDTH, y * CheckersTile.TILE_HEIGHT); 
        this.setTop();
        this.setBottom();
        this.getChildren().addAll(bottom, top);
        this.findOptions();
    }//Piece Constructor

    /**
     *JavaDoc
     *
     */
    public Piece getType(){
        return type;
    }//GetType

    /**
     *JavaDoc
     *
     */
    public void findOptions(){
        this.setOnMousePressed(e-> {
                if(type == Piece.RED && board.isRedTurn()){
                    System.out.println("Red Pieces Left: "+board.getRPiecesLeft());
                    board.clearHighlights();
                    this.moveRed();
                }//if
                if(type == Piece.BLUE && board.isBlueTurn()){
                    System.out.println("Blue Pieces Left: "+board.getBPiecesLeft());
                    board.clearHighlights();
                    this.moveBlue();
                }//if
            });//MouseEvent
    }//Find Options

    /**
     *JavaDoc
     *
     */
    public void setBottom(){
        //Creates bottom portion of piece based on color
        bottom = new Ellipse(CheckersTile.TILE_WIDTH * .3, CheckersTile.TILE_HEIGHT * .2);
        if(type == Piece.RED){
            bottom.setFill(Color.RED);
            bottom.setStroke(Color.DARKRED);
        }//if
        if(type == Piece.BLUE){
            bottom.setFill(Color.ROYALBLUE);
            bottom.setStroke(Color.MEDIUMBLUE);
        }//if
        bottom.setStrokeWidth(CheckersTile.TILE_WIDTH * .03);
        bottom.setTranslateX((CheckersTile.TILE_WIDTH - CheckersTile.TILE_WIDTH * .3 * 2) / 2);
        bottom.setTranslateY((CheckersTile.TILE_HEIGHT - CheckersTile.TILE_HEIGHT * .2 * 2) / 1.5);
    }//SetBottom

    /**
     *JavaDoc
     *
     */
    public void setTop(){
        //Creates top portion of piece based on color
        top = new Ellipse(CheckersTile.TILE_WIDTH * .3, CheckersTile.TILE_HEIGHT * .2);
        if(type == Piece.BLUE){
            top.setFill(Color.ROYALBLUE);
            top.setStroke(Color.MEDIUMBLUE);
        }//if
        else{
            top.setFill(Color.RED);
            top.setStroke(Color.DARKRED);
        }//else
        top.setStrokeWidth(CheckersTile.TILE_WIDTH * .03);
        top.setTranslateX((CheckersTile.TILE_WIDTH - CheckersTile.TILE_WIDTH * .3 * 2) / 2);
        top.setTranslateY((CheckersTile.TILE_HEIGHT - CheckersTile.TILE_HEIGHT * .2 * 2) / 2);
    }//SetTop

    /**
     *JavaDoc
     *
     */
    public void setToKing(){
        //Changes piece to a king
        this.isKing = true;
        king = new Ellipse(CheckersTile.TILE_WIDTH * .15, CheckersTile.TILE_HEIGHT * .1);
        Image kingImg = new Image("checkers_king.jpg", 75, 75, true, false);
        king.setFill(new ImagePattern(kingImg));
        king.setTranslateX((CheckersTile.TILE_WIDTH - CheckersTile.TILE_WIDTH * .3 * 2) / 2);
        king.setTranslateY((CheckersTile.TILE_HEIGHT - CheckersTile.TILE_HEIGHT * .2 * 2) / 2);
        this.getChildren().add(king);
    }//SetToKing

    /**
     *JavaDoc
     *
     */
    public boolean getIsKing(){
        return isKing;
    }//King Getter

    /**
     *JavaDoc
     *
     */
    public void kingCheckBlue(){
        //Checks if blue piece has met condition for becoming king
        if(currentY == 7){
            this.setToKing();
        }//if
    }//KingCheckBlue

    /**
     *JavaDoc
     *
     */
    public void kingCheckRed(){
        //Checks if red piece has met condition for becoming king
        if(currentY == 0){
            this.setToKing();
        }//if
    }//KingCheckRed
    
    /**
     *JavaDoc
     *
     */
    public void clearOptions(){
        //Resets Options
        option1 = null;
        option2 = null;
    }//ClearOptions

    /**
     *JavaDoc
     *
     */
    public void endBlueTurn(){
        //Set environment for turn change
        board.setIsBlueTurn(false);
        board.setIsRedTurn(true);
        this.clearOptions();
        board.clearHighlights();
    }//EndBlueTurn

    /**
     *JavaDoc
     *
     */
    public void endRedTurn(){
        //Sets environment for turn change
        board.setIsBlueTurn(true);
        board.setIsRedTurn(false);
        this.clearOptions();
        board.clearHighlights();
    }//EndRedTurn

    /**
     *JavaDoc
     *
     */
    public void moveBlue(){
        //Creates opportunity for blue piece to move based on position  
        if(board.isBlueTurn()){
            if(currentX == 0){
                this.moveTopLeft();
            }//if
            else if(currentX == 7 && currentY < 7){
                this.moveTopRight();
            }//else-if
            else{
                this.moveDown();
            }//else
        }//if
    }//MoveBlue

    /**
     *JavaDoc
     *
     */
    public void moveRed(){
        //Creates opportunity for red piece to move based on position
        if(board.isRedTurn()){
            if(isKing){
                if(currentX == 0){ 
                    //MoveDown
                    if(currentY == 0){
                        this.moveTopLeft();
                    }
                    else if(currentY == 7){
                        this.moveBotLeft();
                    }
                    else{
                        this.moveTopLeft();
                        this.moveBotLeft();
                        //Both of these methods reference option1, check for bugs
                    }
                    //MoveDown
                }
                else if(currentX == 7){
                    if(currentY == 0){
                        this.moveTopRight();
                    }
                    else if(currentY == 7){
                        this.moveBotRight();
                    }
                    //MoveUp
                    else{
                        this.moveTopRight();
                        this.moveBotRight();
                    }
                }
                else{
                    if(currentY == 0){
                        this.moveDown();
                    }
                    else if(currentY == 7){
                        this.moveUp();
                    }
                    else{
                        this.moveDown();
                        this.moveUp();
                    }
                }
            }
            else{
                if(currentX == 0){
                    this.moveBotLeft();
                }//if
                else if(currentX == 7){
                    this.moveBotRight();
                }//else-if
                else{
                    this.moveUp();
                }//else
            }//else
        }//IsRedTurn
    }//MoveRed
    
    /**
     *JavaDox
     *
     */
    public void moveBotLeft(){
        //Handles movement for red pieces at x = 0
        option1 = board.getIndex(currentX+1, currentY-1);
        if(option1.isOpen()){
            this.botLeft();
        }//if
        else if(option1.getPiece().getType() != this.getType() && currentY > 1){
            if(board.getIndex(currentX+2, currentY-2).isOpen()){
                this.attackUp();
            }//if
        }//else-if
    }//MoveBotLeft

    /**
     *JavaDoc
     *
     */
    public void moveBotRight(){
        //Handles movement for red pieces on x = 7
        option2 = board.getIndex(currentX-1, currentY-1);
        if(option2.isOpen()){
            this.botRight();
        }//if
        else if(option2.getPiece().getType() != this.getType() && currentY > 1){
            if(board.getIndex(currentX-2, currentY-2).isOpen()){
                this.attackUp2();
            }//if
        }//else-if
    }//MoveBotRight

    /**
     *JavaDoc
     *
     */
    public void moveUp(){
        //Handles movement for red pieces where x is between 1 and 7
        option1 = board.getIndex(currentX+1, currentY-1);
        option2 = board.getIndex(currentX-1, currentY-1);
        if(option1.isOpen()){
            this.genMoveUp();
        }//if
        else if(currentX != 6 && currentY > 1){
            if(option1.getPiece().getType() != this.getType()){
                if(board.getIndex(currentX+2, currentY-2).isOpen()){
                    this.attackUp();
                }//if
            }//if
        }//else-if        
        if(option2.isOpen()){
            this.genMoveUp2();
        }//if
        else if(currentX != 1 && currentY > 1){
            if(option2.getPiece().getType() != this.getType()){
                if(board.getIndex(currentX-2, currentY-2).isOpen()){
                    this.attackUp2();
                }//if
            }//if
        }//else-if
    }//RedPaths

    /**
     *JavaDoc
     *
     */
    public void botLeft(){
        //Movement for red piece when x = 0
        option1.setStrokeWidth(3);
        option1.setOnMousePressed(e-> {
                if(option1 != null){
                    currentX++;
                    currentY--;
                    this.kingCheckRed();
                    this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                      (currentY) *  CheckersTile.TILE_HEIGHT);
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX-1, currentY+1).setPiece(null);
                    if(option1.getPiece().getType() == Piece.BLUE){
                        this.endBlueTurn();
                    }
                    else{
                        this.endRedTurn();
                    }
                }//if
            });//MouseEvent
    }//BotLeft

    /**
     *JavaDoc
     *
     */
    public void botRight(){
        //Movement for red piece when x = 7
        option2.setStrokeWidth(3);
        option2.setOnMousePressed(e-> {
                if(option2 != null){
                    currentX--;
                    currentY--;
                    this.kingCheckRed();
                    this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                  (currentY) *  CheckersTile.TILE_HEIGHT);
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX+1, currentY+1).setPiece(null);
                    if(option2.getPiece().getType() == Piece.BLUE){
                        this.endBlueTurn();
                    }
                    else{
                        this.endRedTurn();
                    }
                }//if
            });//MouseEvent
    }//BotRight

    /**
     *JavaDoc
     *
     */
    public void genMoveUp(){
        //Movement option 1 for red pieces not on x = 1 or x = 7
        option1.setStrokeWidth(3);
        option1.setOnMousePressed(e-> {
                if(option1 != null && option2 != null){
                    currentX++;
                    currentY--;
                    this.kingCheckRed();
                    this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                  (currentY) *  CheckersTile.TILE_HEIGHT);
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX-1, currentY+1).setPiece(null);
                    if(option1.getPiece().getType() == Piece.BLUE){
                        this.endBlueTurn();
                    }
                    else{
                        this.endRedTurn();
                    }
                }//if
            });//MouseEvent    
    }//GenMoveUp

    /**
     *JavaDoc
     *
     */
    public void genMoveUp2(){
        //Movement option 2 for red pieces not on x = 1 or x = 7
        option2.setStrokeWidth(3);
        option2.setOnMousePressed(e-> {
                if(option2 != null && option1 != null){
                    currentX--;
                    currentY--;
                    this.kingCheckRed();
                    this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                  (currentY) *  CheckersTile.TILE_HEIGHT);
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX+1, currentY+1).setPiece(null);
                    if(option2.getPiece().getType() == Piece.BLUE){
                        this.endBlueTurn();
                    }
                    else{
                        this.endRedTurn();
                    }
                }//if
            });//MouseEvent
    }//GenMoveUp2

    /**
     *JavaDoc
     *
     */
    public void attackUp(){
        //Movement & Actions for red pieces attacking
        option1 = board.getIndex(currentX+2, currentY-2);
        option1.setStrokeWidth(3);
        option1.setOnMousePressed(e-> {
                if(option1 != null){
                    currentX = currentX+2;
                    currentY = currentY-2;
                    this.kingCheckRed();
                    this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                  (currentY) *  CheckersTile.TILE_HEIGHT);
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX-1, currentY+1).getPiece().getChildren().clear();
                    board.getIndex(currentX-1, currentY+1).setPiece(null);
                    board.getIndex(currentX-2, currentY+2).setPiece(null);
                    board.setBPiecesLeft(board.getBPiecesLeft()-1);
                    if(option1.getPiece().getType() == Piece.BLUE){
                        this.endBlueTurn();
                    }
                    else{
                        this.endRedTurn();
                    }
                }//if
            });//MouseEvent
    }//AttackUp

    /**
     *JavaDoc
     *
     */
    public void attackUp2(){
        //Secondary movements & actions for red pieces attacking
        option2 = board.getIndex(currentX-2, currentY-2);
        option2.setStrokeWidth(3);
        option2.setOnMousePressed(e-> {
                if(option2 != null){
                    currentX = currentX-2;
                    currentY = currentY-2;
                    this.kingCheckRed();
                    this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                  (currentY) *  CheckersTile.TILE_HEIGHT);
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX+1, currentY+1).getPiece().getChildren().clear();
                    board.getIndex(currentX+1, currentY+1).setPiece(null);
                    board.getIndex(currentX+2, currentY+2).setPiece(null);
                    board.setBPiecesLeft(board.getBPiecesLeft()-1);
                    if(option2.getPiece().getType() == Piece.BLUE){
                        this.endBlueTurn();
                    }
                    else{
                        this.endRedTurn();
                    }
                }//if
            });//MouseEvent
    }//RedAttack2

    /**
     *JavaDoc
     *
     */
    public void moveTopLeft(){
        //Handles movement for blue pieces at x = 0
        option1 = board.getIndex(currentX+1, currentY+1);
        if(option1.isOpen()){
            this.topLeft();
        }//if
        else if(option1.getPiece().getType() != this.getType()){
            if(board.getIndex(currentX+2, currentY+2).isOpen()){
                this.attackDown();
            }//if
        }//else-if
    }//MoveTopLeft

    /**
     *JavaDoc
     *
     */
    public void moveTopRight(){
        //Handles movement for blue pieces at x = 7
        option2 = board.getIndex(currentX-1, currentY+1);
        if(option2.isOpen()){
            this.topRight();
        }//if
        else if(option2.getPiece().getType() != this.getType() && currentY < 6){
            if(board.getIndex(currentX-2, currentY+2).isOpen()){
                this.attackDown2();
            }//if
        }//else-if
    }//MoveTopRight

    /**
     *JavaDoc
     *
     */
    public void moveDown(){
        //Handles movement for blue pieces when x is between 1 and 7
        option1 = board.getIndex(currentX+1, currentY+1);
        option2 = board.getIndex(currentX-1, currentY+1);
        if(option1.isOpen()){
            this.genMoveDown();
        }//if
        else if(currentX != 6 && currentY < 6){
            if(option1.getPiece().getType() != this.getType()){
                if(board.getIndex(currentX+2, currentY+2).isOpen()){
                    this.attackDown();
                }//if
            }//if
        }//else-if           
        if(option2.isOpen()){
            this.genMoveDown2();
        }//if
        else if(currentX != 1 && currentY < 6){
            if(option2.getPiece().getType() != this.getType()){
                if(board.getIndex(currentX-2, currentY+2).isOpen()){
                    this.attackDown2();
                }//if
            }//if
        }//else-if
    }//MoveDown

    /**
     *JavaDoc
     *
     */
    public void topLeft(){
        //Movement for blue piece when x = 0
        option1.setStrokeWidth(3);
        option1.setOnMousePressed(e-> {
                if(option1 != null){
                    currentX++;
                    currentY++;
                    this.kingCheckBlue();
                    this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                  (currentY) *  CheckersTile.TILE_HEIGHT);
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX-1, currentY-1).setPiece(null);
                    if(option1.getPiece().getType() == Piece.BLUE){
                        this.endBlueTurn();
                    }
                    else{
                        this.endRedTurn();
                    }
                }//if
            });//MouseEvent   
    }//TopLeft

    /**
     *JavaDoc
     *
     */
    public void topRight(){
        //Movement for blue piece when x = 7
        option2.setStrokeWidth(3);
        option2.setOnMousePressed(e-> {
                if(option2 != null){
                    currentX--;
                    currentY++;
                    this.kingCheckBlue();
                    this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                  (currentY) *  CheckersTile.TILE_HEIGHT);
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX+1, currentY-1).setPiece(null);
                     if(option2.getPiece().getType() == Piece.BLUE){
                        this.endBlueTurn();
                    }
                    else{
                        this.endRedTurn();
                    }
                }//if
            });//MouseEvent
    }//TopRight

    /**
     *JavaDoc
     *
     */
    public void genMoveDown(){
        //Movement option 1 for blue pieces not on x = 1 or x = 7
        option1.setStrokeWidth(3);
        option1.setOnMousePressed(e-> {
                if(option1 != null && option2 != null){
                    currentX++;
                    currentY++;
                    this.kingCheckBlue();
                    this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                  (currentY) *  CheckersTile.TILE_HEIGHT);
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX-1, currentY-1).setPiece(null);
                    if(option1.getPiece().getType() == Piece.BLUE){
                        this.endBlueTurn();
                    }
                    else{
                        this.endRedTurn();
                    }
                }//if
            });//MouseEvent 
    }//GenMoveDown

    /**
     *JavaDoc
     *
     */
    public void genMoveDown2(){
        //Movement option 2 for blue pieces not on x = 1 or x = 7
        option2.setStrokeWidth(3);
        option2.setOnMousePressed(e-> {
                if(option1 != null && option2 != null){
                    currentX--;
                    currentY++;
                    this.kingCheckBlue();
                    this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                  (currentY) *  CheckersTile.TILE_HEIGHT);
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX+1, currentY-1).setPiece(null);
                    if(option2.getPiece().getType() == Piece.BLUE){
                        this.endBlueTurn();
                    }
                    else{
                        this.endRedTurn();
                    }
                }//if
            });//MouseEvent
    }//BlueCenter2

    /**
     *JavaDoc
     *
     */
    public void attackDown(){
        //Movements & actions for blue pieces attacking
        option1 = board.getIndex(currentX+2, currentY+2);
        option1.setStrokeWidth(3);
        option1.setOnMousePressed(e-> {
                if(option1 != null){
                    currentX = currentX+2;
                    currentY = currentY+2;
                    this.kingCheckBlue();
                    this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                  (currentY) *  CheckersTile.TILE_HEIGHT);
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX-1, currentY-1).getPiece().getChildren().clear();
                    board.getIndex(currentX-1, currentY-1).setPiece(null);
                    board.getIndex(currentX-2, currentY-2).setPiece(null);
                    board.setRPiecesLeft(board.getRPiecesLeft()-1);
                    if(option1.getPiece().getType() == Piece.BLUE){
                        this.endBlueTurn();
                    }
                    else{
                        this.endRedTurn();
                    }
                }//if
            });//MouseEvent
    }//AttackDown

    /**
     *JavaDoc
     *
     */
    public void attackDown2(){
        //Secondary movements & actions for blue pieces attacking
        option2 = board.getIndex(currentX-2, currentY+2);
        option2.setStrokeWidth(3);
        option2.setOnMousePressed(e-> {
                if(option2 != null){
                    currentX = currentX-2;
                    currentY = currentY+2;
                    this.kingCheckBlue();
                    this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                  (currentY) *  CheckersTile.TILE_HEIGHT);
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX+1, currentY-1).getPiece().getChildren().clear();
                    board.getIndex(currentX+1, currentY-1).setPiece(null);
                    board.getIndex(currentX+2, currentY-2).setPiece(null);
                    board.setRPiecesLeft(board.getRPiecesLeft()-1);
                    if(option2.getPiece().getType() == Piece.BLUE){
                        this.endBlueTurn();
                    }
                    else{
                        this.endRedTurn();
                    }
                }//if
            });//MouseEvent
    }//AttackDown2
    
}//Class
