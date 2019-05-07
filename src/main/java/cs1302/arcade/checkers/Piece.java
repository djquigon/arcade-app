package cs1302.arcade;

import cs1302.arcade.*;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;


/**
 * Represents the two different team colors.
 */
enum PieceType{
    //Color name being associated with movement
    BLUE(1), //1 represents moving down
    RED(-1); //-1 represents moving up
    
    private int pieceDirection;

    /**
     *Associates piece with a matching direction(color).
     *
     *@param pieceDirection value that corresponds to either blue or red
     */
    PieceType(int pieceDirection){
        this.pieceDirection = pieceDirection;
    }//PieceDirection
    
}//Piece

/**
 * This class creates the checkers piece for both teams with corresponding 
 * functionality for each.
 */
public class Piece extends StackPane{
    
    //Instance variables
    private Board board; //the board
    private PieceType type; //the type of piece
    private Ellipse bottom; //adds 3d effect
    private Ellipse top; //top ellipse of piece
    private Ellipse king; //king symbol
    private Boolean isKing;
    private int currentX; //current x position
    private int currentY; //current y position
    private Tile option1;
    private Tile option2;
    private Tile option3;
    private Tile option4;

    /**
     * Constructs a checkers piece with the neccesary variables to keep track of it.
     *
     *@param board board object for piece to be placed on
     *@param type color of the piece
     *@param x the corresponding x coordinate of where the piece is
     *@param y the corresponding y coordinate of where the piece is
     */
    public Piece(Board board, PieceType type, int x, int y){
        //Intializes pieces and sets them on board at proper points
        this.board = board; 
        this.type = type; 
        this.isKing = false; 
        currentX = x;
        currentY = y;
        this.relocate(x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT); 
        this.setTop();
        this.setBottom();
        this.getChildren().addAll(bottom, top);
        this.findOptions();
    }//Piece Constructor
    
    /**
     * Returns the color for checkers piece.
     *
     * @return reference to what color the piece is
     */
    public PieceType getType(){
        return type;
    }//GetType
    
    /**
     * Clears all options for avaliable moves.
     */
    public void clearOptions(){
        //Resets Options
        option1 = null;
        option2 = null;
        option3 = null;
        option4 = null;
    }//ClearOptions
    
    /**
     * Checks whether a piece is a king or not.
     *
     * @return whether a piece is a king or not
     */
    public boolean getIsKing(){
        return isKing;
    }//GetIsKing
    
    /**
     * Creates the bottom half visual for piece. 
     */
    public void setBottom(){
        //Creates bottom portion of piece based on color
        bottom = new Ellipse(Tile.TILE_WIDTH * .3, Tile.TILE_HEIGHT * .2);
        if(type == PieceType.RED){
            bottom.setFill(Color.RED);
            bottom.setStroke(Color.DARKRED);
        }//if
        if(type == PieceType.BLUE){
            bottom.setFill(Color.ROYALBLUE);
            bottom.setStroke(Color.MEDIUMBLUE);
        }//if
        bottom.setStrokeWidth(Tile.TILE_WIDTH * .03);
        bottom.setTranslateX((Tile.TILE_WIDTH - Tile.TILE_WIDTH * .3 * 2) / 2);
        bottom.setTranslateY((Tile.TILE_HEIGHT - Tile.TILE_HEIGHT * .2 * 2) / 1.5);
    }//SetBottom
    
    /**
     * Creates the top half of visual for piece.
     */
    public void setTop(){
        //Creates top portion of piece based on color
        top = new Ellipse(Tile.TILE_WIDTH * .3, Tile.TILE_HEIGHT * .2);
        if(type == PieceType.BLUE){
            top.setFill(Color.ROYALBLUE);
            top.setStroke(Color.MEDIUMBLUE);
        }//if
        else{
            top.setFill(Color.RED);
            top.setStroke(Color.DARKRED);
        }//else
        top.setStrokeWidth(Tile.TILE_WIDTH * .03);
        top.setTranslateX((Tile.TILE_WIDTH - Tile.TILE_WIDTH * .3 * 2) / 2);
        top.setTranslateY((Tile.TILE_HEIGHT - Tile.TILE_HEIGHT * .2 * 2) / 2);
    }//SetTop
    
    /**
     * Creates the visual for when a piece becomes a king.
     */
    public void setToKing(){
        //Changes piece to a king
        this.isKing = true;
        //Adding king symbol to piece
        king = new Ellipse(Tile.TILE_WIDTH * .15, Tile.TILE_HEIGHT * .1);
        Image kingImg = new Image("checkers_king.jpg", 75, 75, true, false);
        king.setFill(new ImagePattern(kingImg));
        king.setTranslateX((Tile.TILE_WIDTH - Tile.TILE_WIDTH * .3 * 2) / 2);
        king.setTranslateY((Tile.TILE_HEIGHT - Tile.TILE_HEIGHT * .2 * 2) / 2);
        this.getChildren().add(king);
    }//SetToKing

    /**
     * Completes the task of showing avaliable moves for a piece.
     */
    public void findOptions(){
        this.setOnMousePressed(e-> {
                //Shows options for red piece user clicked
                if(type == PieceType.RED && board.isRedTurn()){
                    board.clearHighlights();
                    this.moveRed();
                }//if
                //Shows options for blue piece user clicked
                if(type == PieceType.BLUE && board.isBlueTurn()){
                    board.clearHighlights();
                    this.moveBlue();
                }//if
            });//MouseEvent
    }//FindOptions
    
    /**
     * Checks if a blue piece becomes a king.
     */
    public void kingCheckBlue(){
        //Checks if blue piece has met condition for becoming king
        if(currentY == 7){
            this.setToKing();
        }//if
    }//KingCheckBlue
    
    /**
     * Checks if a Red piece becomes a king.
     */
    public void kingCheckRed(){
        //Checks if red piece has met condition for becoming king
        if(currentY == 0){
            this.setToKing();
        }//if
    }//KingCheckRed
    
    /**
     * End blue player's turn and sets board for new move.
     */
    public void endBlueTurn(){
        //Set environment for turn change
        board.setIsBlueTurn(false);
        board.setIsRedTurn(true);
        this.clearOptions();
        board.clearHighlights();
    }//EndBlueTurn
    
    /**
     * End red player's turn and sets board for new move.
     */
    public void endRedTurn(){
        //Sets environment for turn change
        board.setIsBlueTurn(true);
        board.setIsRedTurn(false);
        this.clearOptions();
        board.clearHighlights();
    }//EndRedTurn
    
    /**
     * Moves blue piece after displaying avaliable moves to user and having them select one.
     */
    public void moveBlue(){
        //Creates opportunity for blue piece to move based on position  
        if(board.isBlueTurn()){
            //If piece is a king
            if(isKing){
                this.checkRight();
                this.checkLeft();
                this.checkCenter();
            }//if
            //If piece is not a king
            else{
                if(currentX == 0){
                    this.moveTopLeft();
                }//if
                else if(currentX == 7 && currentY < 7){
                    this.moveTopRight();
                }//else-if
                else{
                    this.moveDown();
                }//else
            }//else
        }//if
    }//MoveBlue
    
    /**
     * Moves red piece after displaying avaliable moves to user and having them select one.
     */
    public void moveRed(){
        //Creates opportunity for red piece to move based on position
        if(board.isRedTurn()){
            //If piece is a king
            if(isKing){
                this.checkRight();
                this.checkLeft();
                this.checkCenter();
            }//if
            //If piece is not a king
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
     * Handles the movement options for when a piece is located in the top
     * left corner.
     */
    public void moveTopLeft(){
        //Handles movement for blue pieces at x = 0, y = 0
        option1 = board.getIndex(currentX+1, currentY+1);
        //Checks for normal move
        if(option1.isOpen()){
            this.topLeft();
        }//if
        //Checks for attack
        else if(option1.getPiece().getType() != this.getType()){
            if(board.getIndex(currentX+2, currentY+2).isOpen()){
                this.attackDown();
            }//if
        }//else-if
    }//MoveTopLeft
    
    /**
     * Handles the movement options for when a piece is located in the bottom
     * left corner.
     */
    public void moveBotLeft(){
        //Handles movement for red pieces at x = 0, y = 7
        option3 = board.getIndex(currentX+1, currentY-1);
        //Checks for normal move
        if(option3.isOpen()){
            this.botLeft();
        }//if
        //Checks for attack
        else if(option3.getPiece().getType() != this.getType() && currentY > 1){
            if(board.getIndex(currentX+2, currentY-2).isOpen()){
                this.attackUp();
            }//if
        }//else-if
    }//MoveBotLeft
    
    /**
     * Handles the movement options for when a piece is located in the top right
     * corner.
     */
    public void moveTopRight(){
        //Handles movement for blue pieces at x = 7, y = 0
        option2 = board.getIndex(currentX-1, currentY+1);
        //Checks for normal move
        if(option2.isOpen()){
            this.topRight();
        }//if
        //Checks for attack
        else if(option2.getPiece().getType() != this.getType() && currentY < 6){
            if(board.getIndex(currentX-2, currentY+2).isOpen()){
                this.attackDown2();
            }//if
        }//else-if
    }//MoveTopRight
    
    /**
     * Handles the movement options for when a piece is located in the bottom
     * right corner.
     */
    public void moveBotRight(){
        //Handles movement for red pieces on x = 7, y = 7
        option4 = board.getIndex(currentX-1, currentY-1);
        //Checks for normal move
        if(option4.isOpen()){
            this.botRight();
        }//if
        //Checks for attack
        else if(option4.getPiece().getType() != this.getType() && currentY > 1){
            if(board.getIndex(currentX-2, currentY-2).isOpen()){
                this.attackUp2();
            }//if
        }//else-if
    }//MoveBotRight
    
    /**
     * Highlights option for piece in top left corner, and if user
     * selects option, it then updates board.
     */
    public void topLeft(){
        //Movement action for x = 0, y = 0
        option1.setStrokeWidth(3);
        option1.setOnMousePressed(e-> {
                if(option1 != null){
                    //Update coordinates
                    currentX++;
                    currentY++;
                    this.kingCheckBlue();
                    this.relocate((currentX) *  Tile.TILE_WIDTH,
                                  (currentY) *  Tile.TILE_HEIGHT);
                    //Update board
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX-1, currentY-1).setPiece(null);
                    //End turn
                    if(option1.getPiece().getType() == PieceType.BLUE){
                        this.endBlueTurn();
                    }//if
                    else{
                        this.endRedTurn();
                    }//else
                }//if
            });//MouseEvent   
    }//TopLeft
    
    /**
     * Highlights option for piece in bottom left corner, and if user
     * selects option, it then updates board.
     */
    public void botLeft(){
        //Movement action for x = 0, y = 7
        option3.setStrokeWidth(3);
        option3.setOnMousePressed(e-> {
                if(option3 != null){
                    //Update coordinates
                    currentX++;
                    currentY--;
                    this.kingCheckRed();
                    this.relocate((currentX) *  Tile.TILE_WIDTH,
                                  (currentY) *  Tile.TILE_HEIGHT);
                    //Update board
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX-1, currentY+1).setPiece(null);
                    //End turn
                    if(option3.getPiece().getType() == PieceType.BLUE){
                        this.endBlueTurn();
                    }//if
                    else{
                        this.endRedTurn();
                    }//else
                }//if
            });//MouseEvent
    }//BotLeft
    
    /**
     * Highlights option for piece in top right corner, and if user
     * selects option, it then updates board.
     */
    public void topRight(){
        //Movement action for x = 7, y = 0
        option2.setStrokeWidth(3);
        option2.setOnMousePressed(e-> {
                if(option2 != null){
                    //Updates coordinates
                    currentX--;
                    currentY++;
                    this.kingCheckBlue();
                    this.relocate((currentX) *  Tile.TILE_WIDTH,
                                  (currentY) *  Tile.TILE_HEIGHT);
                    //Updates board
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX+1, currentY-1).setPiece(null);
                    //End turn
                    if(option2.getPiece().getType() == PieceType.BLUE){
                        this.endBlueTurn();
                    }//if
                    else{
                        this.endRedTurn();
                    }//else
                }//if
            });//MouseEvent
    }//TopRight
    
    /**
     * Highlights option for piece in bottom right corner, and if user
     * selects option, it then updates board.
     */
    public void botRight(){
        //Movement action for x = 7, y = 7
        option4.setStrokeWidth(3);
        option4.setOnMousePressed(e-> {
                if(option4 != null){
                    //Update coordinates
                    currentX--;
                    currentY--;
                    this.kingCheckRed();
                    this.relocate((currentX) *  Tile.TILE_WIDTH,
                                  (currentY) *  Tile.TILE_HEIGHT);
                    //Updates board
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX+1, currentY+1).setPiece(null);
                    //End turn
                    if(option4.getPiece().getType() == PieceType.BLUE){
                        this.endBlueTurn();
                    }//if
                    else{
                        this.endRedTurn();
                    }//else
                }//if
            });//MouseEvent
    }//BotRight
    
    /**
     * Checks for valid movements when piece is moving upwards and if an
     * attack is possible.
     */
    public void moveUp(){
        //Handles movement for pieces moving up between x = 0 and x = 7
        option3 = board.getIndex(currentX+1, currentY-1);
        option4 = board.getIndex(currentX-1, currentY-1);
        //First option checks for normal move
        if(option3.isOpen()){
            this.genMoveUp();
        }//if
        //First option checks for attack
        else if(currentX != 6 && currentY > 1){
            if(option3.getPiece().getType() != this.getType()){
                if(board.getIndex(currentX+2, currentY-2).isOpen()){
                    this.attackUp();
                }//if
            }//if
        }//else-if
        //Second option checks for normal move
        if(option4.isOpen()){
            this.genMoveUp2();
        }//if
        //Second option checks for attack
        else if(currentX != 1 && currentY > 1){
            if(option4.getPiece().getType() != this.getType()){
                if(board.getIndex(currentX-2, currentY-2).isOpen()){
                    this.attackUp2();
                }//if
            }//if
        }//else-if
    }//MoveUp
    
    /**
     * Creates an option for when piece is moving upwards and it is not on
     * x = 0 or x = 7.
     */
    public void genMoveUp(){
        //First movement option for pieces moving upwards between x = 0 and x = 7
        option3.setStrokeWidth(3);
        option3.setOnMousePressed(e-> {
                if(option3 != null && option4 != null){
                    //Update coordinates
                    currentX++;
                    currentY--;
                    this.kingCheckRed();
                    this.relocate((currentX) *  Tile.TILE_WIDTH,
                                  (currentY) *  Tile.TILE_HEIGHT);
                    //Updates board
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX-1, currentY+1).setPiece(null);
                    //End turn
                    if(option3.getPiece().getType() == PieceType.BLUE){
                        this.endBlueTurn();
                    }//if
                    else{
                        this.endRedTurn();
                    }//else
                }//if
            });//MouseEvent    
    }//GenMoveUp
    
    /**
     * Creates option for an attack when a piece is moving upwards. 
     */
    public void attackUp(){
        //Attack action for upward moving pieces 
        option3 = board.getIndex(currentX+2, currentY-2);
        option3.setStrokeWidth(3);
        option3.setOnMousePressed(e-> {
                if(option3 != null){
                    //Updates coordinates
                    currentX = currentX+2;
                    currentY = currentY-2;
                    this.kingCheckRed();
                    this.relocate((currentX) *  Tile.TILE_WIDTH,
                                  (currentY) *  Tile.TILE_HEIGHT);
                    //Updates board
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX-1, currentY+1).getPiece().getChildren().clear();
                    board.getIndex(currentX-1, currentY+1).setPiece(null);
                    board.getIndex(currentX-2, currentY+2).setPiece(null);
                    //End turn
                    if(option3.getPiece().getType() == PieceType.BLUE){
                        board.setRPiecesLeft(board.getRPiecesLeft()-1);
                        this.endBlueTurn();
                    }//if
                    else{
                        board.setBPiecesLeft(board.getBPiecesLeft()-1);
                        this.endRedTurn();
                    }//else
                }//if
            });//MouseEvent
    }//AttackUp
    
    /**
     * Creates secondary option for when piece is moving upwards and it is not on
     * x = 0 or x = 7.
     */
    public void genMoveUp2(){
        //Secondary movement action for pieces moving upwards between x = 0 and x = 7
        option4.setStrokeWidth(3);
        option4.setOnMousePressed(e-> {
                if(option4 != null && option3 != null){
                    //Updates coordinates
                    currentX--;
                    currentY--;
                    this.kingCheckRed();
                    this.relocate((currentX) *  Tile.TILE_WIDTH,
                                  (currentY) *  Tile.TILE_HEIGHT);
                    //Updates board
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX+1, currentY+1).setPiece(null);
                    //End turn
                    if(option4.getPiece().getType() == PieceType.BLUE){
                        this.endBlueTurn();
                    }//if
                    else{
                        this.endRedTurn();
                    }//else
                }//if
            });//MouseEvent
    }//GenMoveUp2
    
    /**
     * Creates secondary option for an attack when a piece is moving upwards. 
     */
    public void attackUp2(){
        //Secondary attack action for upward moving pieces
        option4 = board.getIndex(currentX-2, currentY-2);
        option4.setStrokeWidth(3);
        option4.setOnMousePressed(e-> {
                if(option4 != null){
                    //Update coordinates
                    currentX = currentX-2;
                    currentY = currentY-2;
                    this.kingCheckRed();
                    this.relocate((currentX) *  Tile.TILE_WIDTH,
                                  (currentY) *  Tile.TILE_HEIGHT);
                    //Update board
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX+1, currentY+1).getPiece().getChildren().clear();
                    board.getIndex(currentX+1, currentY+1).setPiece(null);
                    board.getIndex(currentX+2, currentY+2).setPiece(null);
                    //End turn
                    if(option4.getPiece().getType() == PieceType.BLUE){
                        board.setRPiecesLeft(board.getRPiecesLeft()-1);
                        this.endBlueTurn();
                    }//if
                    else{
                        board.setBPiecesLeft(board.getBPiecesLeft()-1);
                        this.endRedTurn();
                    }//else
                }//if
            });//MouseEvent
    }//AttackUp2
    
    /**
     * Checks for valid movements when piece is moving downwards and if an
     * attack is possible.
     */
    public void moveDown(){
        //Handles movement for pieces moving down between x = 0 and x = 7
        option1 = board.getIndex(currentX+1, currentY+1);
        option2 = board.getIndex(currentX-1, currentY+1);
        //First option checks for normal move
        if(option1.isOpen()){
            this.genMoveDown();
        }//if
        //First option checks for attack
        else if(currentX != 6 && currentY < 6){
            if(option1.getPiece().getType() != this.getType()){
                if(board.getIndex(currentX+2, currentY+2).isOpen()){
                    this.attackDown();
                }//if
            }//if
        }//else-if
        //Second option checks for normal move
        if(option2.isOpen()){
            this.genMoveDown2();
        }//if
        //Second option checks for attack
        else if(currentX != 1 && currentY < 6){
            if(option2.getPiece().getType() != this.getType()){
                if(board.getIndex(currentX-2, currentY+2).isOpen()){
                    this.attackDown2();
                }//if
            }//if
        }//else-if
    }//MoveDown
    
    /**
     * Creates an option for when piece is moving downwards and it is not on
     * x = 0 or x = 7.
     */
    public void genMoveDown(){
        //First movement option for pieces moving downwards between x = 0 and x = 7
        option1.setStrokeWidth(3);
        option1.setOnMousePressed(e-> {
                if(option1 != null && option2 != null){
                    //Updates coordinates
                    currentX++;
                    currentY++;
                    this.kingCheckBlue();
                    this.relocate((currentX) *  Tile.TILE_WIDTH,
                                  (currentY) *  Tile.TILE_HEIGHT);
                    //Updates board
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX-1, currentY-1).setPiece(null);
                    //End turn
                    if(option1.getPiece().getType() == PieceType.BLUE){
                        this.endBlueTurn();
                    }//if
                    else{
                        this.endRedTurn();
                    }//else
                }//if
            });//MouseEvent 
    }//GenMoveDown
    
    /**
     * Creates option for an attack when a piece is moving downwards. 
     */
    public void attackDown(){
        //Attack action for downward moving pieces 
        option1 = board.getIndex(currentX+2, currentY+2);
        option1.setStrokeWidth(3);
        option1.setOnMousePressed(e-> {
                if(option1 != null){
                    //Update coordinates
                    currentX = currentX+2;
                    currentY = currentY+2;
                    this.kingCheckBlue();
                    this.relocate((currentX) *  Tile.TILE_WIDTH,
                                  (currentY) *  Tile.TILE_HEIGHT);
                    //Update board
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX-1, currentY-1).getPiece().getChildren().clear();
                    board.getIndex(currentX-1, currentY-1).setPiece(null);
                    board.getIndex(currentX-2, currentY-2).setPiece(null);
                    //End Turn
                    if(option1.getPiece().getType() == PieceType.BLUE){
                        board.setRPiecesLeft(board.getRPiecesLeft()-1);
                        this.endBlueTurn();
                    }//if
                    else{
                        board.setBPiecesLeft(board.getBPiecesLeft()-1);
                        this.endRedTurn();
                    }//else
                }//if
            });//MouseEvent
    }//AttackDown
    
    /**
     * Creates secondary option for when piece is moving downwards and it is 
     * not on x = 0 or x = 7.
     */
    public void genMoveDown2(){
        //Secondary movement option for pieces moving downwards between x = 0 and x = 7
        option2.setStrokeWidth(3);
        option2.setOnMousePressed(e-> {
                if(option1 != null && option2 != null){
                    //Update coordinates
                    currentX--;
                    currentY++;
                    this.kingCheckBlue();
                    this.relocate((currentX) *  Tile.TILE_WIDTH,
                                  (currentY) *  Tile.TILE_HEIGHT);
                    //Update board
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX+1, currentY-1).setPiece(null);
                    //End turn
                    if(option2.getPiece().getType() == PieceType.BLUE){
                        this.endBlueTurn();
                    }//if
                    else{
                        this.endRedTurn();
                    }//else
                }//if
            });//MouseEvent
    }//GenMoveDown2
    
    /**
     * Creates secondary option for an attack when a piece is moving downwards. 
     */
    public void attackDown2(){
        //Attack action for downward moving pieces 
        option2 = board.getIndex(currentX-2, currentY+2);
        option2.setStrokeWidth(3);
        option2.setOnMousePressed(e-> {
                if(option2 != null){
                    //Update coordinates
                    currentX = currentX-2;
                    currentY = currentY+2;
                    this.kingCheckBlue();
                    this.relocate((currentX) *  Tile.TILE_WIDTH,
                                  (currentY) *  Tile.TILE_HEIGHT);
                    //Update board
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX+1, currentY-1).getPiece().getChildren().clear();
                    board.getIndex(currentX+1, currentY-1).setPiece(null);
                    board.getIndex(currentX+2, currentY-2).setPiece(null);
                    //End turn
                    if(option2.getPiece().getType() == PieceType.BLUE){
                        board.setRPiecesLeft(board.getRPiecesLeft()-1);
                        this.endBlueTurn();
                    }//if
                    else{
                        board.setBPiecesLeft(board.getBPiecesLeft()-1);
                        this.endRedTurn();
                    }//else
                }//if
            });//MouseEvent
    }//AttackDown2

    /**
     * Checks what open moves are avaliable for a king piece when it is on x = 0.
     */
    public void checkLeft(){
        if(currentX == 0){ 
            if(currentY == 0){
                //If (0,0) use moveTopLeft()
                this.moveTopLeft();
            }//if
            else if(currentY == 7){
                //If (0,7) use moveBotLeft()
                this.moveBotLeft();
            }//else-if
            else{
                //If x is 0 and y is between 0 and 7, attempt 4 options
                this.moveTopLeft();
                this.moveBotLeft();
            }//else
        }//if
    }//CheckLeft
    
    /**
     * Checks what open moves are avaliable for a king piece when it is on x = 7.
     */
    public void checkRight(){
        if(currentX == 7){
            if(currentY == 0){
                //If (7,0) use moveTopRight()
                this.moveTopRight();
            }//if
            else if(currentY == 7){
                //If (7,7) use moveBotRight()
                this.moveBotRight();
            }//else-if
            else{
                //If x is 7 and y is between 0 and 7, attempt 4 options
                this.moveTopRight();
                this.moveBotRight();
            }//else
        }//if
    }//CheckRight
    
    /**
     * Checks what open moves are avaliable for a king piece when x is between
     * 0 and 7.
     */
    public void checkCenter(){
        if(currentX > 0 && currentX < 7){
            if(currentY == 0){
                //If y is 0 and x is between 0 and 7 use moveDown()
                this.moveDown();
            }//if
            else if(currentY == 7){
                //If x is between 0 and 7 and y = 0 use moveUp()
                this.moveUp();
            }//else-if
            else{
                //If x & y is between 0 and 7 attempt 4 options
                this.moveDown();
                this.moveUp();
            }//else
        }//if
    }//CheckCenter
    
}//Class
