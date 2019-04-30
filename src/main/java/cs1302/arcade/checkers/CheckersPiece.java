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
                    board.clearHighlights();
                    this.moveRed();
                }//if
                if(type == Piece.BLUE && board.isBlueTurn()){
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
                this.bluePath0();
            }//if
            else if(currentX == 7 && currentY < 7){
                this.bluePath7();
            }//else-if
            else{
                this.bluePaths();
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
            if(currentX == 0){
                this.redPath0();
            }//if
            else if(currentX == 7){
                this.redPath7();
            }//else-if
            else{
                this.redPaths();
            }//else
        }//if
    }//MoveRed

    /**
     *JavaDox
     *
     */
    public void redPath0(){
        //Handles movement for red pieces at x = 0
        option1 = board.getIndex(currentX+1, currentY-1);
        if(option1.isOpen()){
            this.redLeftSide();
        }//if
        else if(option1.getPiece().getType() == Piece.BLUE && currentY > 1){
            if(board.getIndex(currentX+2, currentY-2).isOpen()){
                this.redAttack();
            }//if
        }//else-if
    }//RedPath0

    /**
     *JavaDoc
     *
     */
    public void redPath7(){
        //Handles movement for red pieces on x = 7
        option2 = board.getIndex(currentX-1, currentY-1);
        if(option2.isOpen()){
            this.redRightSide();
        }//if
        else if(option2.getPiece().getType() == Piece.BLUE && currentY > 1){
            if(board.getIndex(currentX-2, currentY-2).isOpen()){
                this.redAttack2();
            }//if
        }//else-if
    }//RedPath7

    /**
     *JavaDoc
     *
     */
    public void redPaths(){
        //Handles movement for red pieces where x is between 1 and 7
        option1 = board.getIndex(currentX+1, currentY-1);
        option2 = board.getIndex(currentX-1, currentY-1);
        if(option1.isOpen()){
            this.redCenter();
        }//if
        else if(currentX != 6 && currentY > 1){
            if(option1.getPiece().getType() == Piece.BLUE){
                if(board.getIndex(currentX+2, currentY-2).isOpen()){
                    this.redAttack();
                }//if
            }//if
        }//else-if        
        if(option2.isOpen()){
            this.redCenter2();
        }//if
        else if(currentX != 1 && currentY > 1){
            if(option2.getPiece().getType() == Piece.BLUE){
                if(board.getIndex(currentX-2, currentY-2).isOpen()){
                    this.redAttack2();
                }//if
            }//if
        }//else-if
    }//RedPaths

    /**
     *JavaDoc
     *
     */
    public void redLeftSide(){
        //Movement for red piece when x = 0
        option1.setStrokeWidth(3);
        option1.setOnMousePressed(e-> {
                if(board.isRedTurn() && option1 != null){
                    currentX++;
                    currentY--;
                    this.kingCheckRed();
                    this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                      (currentY) *  CheckersTile.TILE_HEIGHT);
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX-1, currentY+1).setPiece(null);
                    this.endRedTurn();
                }//if
            });//MouseEvent
    }//RedLeftSide

    /**
     *JavaDoc
     *
     */
    public void redRightSide(){
        //Movement for red piece when x = 7
        option2.setStrokeWidth(3);
        option2.setOnMousePressed(e-> {
                if(board.isRedTurn() && option2 != null){
                    currentX--;
                    currentY--;
                    this.kingCheckRed();
                    this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                  (currentY) *  CheckersTile.TILE_HEIGHT);
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX+1, currentY+1).setPiece(null);
                    this.endRedTurn();
                }//if
            });//MouseEvent
    }//RedRightSide

    /**
     *JavaDoc
     *
     */
    public void redCenter(){
        //Movement option 1 for red pieces not on x = 1 or x = 7
        option1.setStrokeWidth(3);
        option1.setOnMousePressed(e-> {
                if(board.isRedTurn() && option1 != null && option2 != null){
                    currentX++;
                    currentY--;
                    this.kingCheckRed();
                    this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                  (currentY) *  CheckersTile.TILE_HEIGHT);
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX-1, currentY+1).setPiece(null);
                    this.endRedTurn();
                }//if
            });//MouseEvent    
    }//RedCenter

    /**
     *JavaDoc
     *
     */
    public void redCenter2(){
        //Movement option 2 for red pieces not on x = 1 or x = 7
        option2.setStrokeWidth(3);
        option2.setOnMousePressed(e-> {
                if(board.isRedTurn() && option2 != null && option1 != null){
                    currentX--;
                    currentY--;
                    this.kingCheckRed();
                    this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                  (currentY) *  CheckersTile.TILE_HEIGHT);
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX+1, currentY+1).setPiece(null);
                    this.endRedTurn();
                }//if
            });//MouseEvent
    }//RedCenter2

    /**
     *JavaDoc
     *
     */
    public void redAttack(){
        //Movement & Actions for red pieces attacking
        option1 = board.getIndex(currentX+2, currentY-2);
        option1.setStrokeWidth(3);
        option1.setOnMousePressed(e-> {
                if(board.isRedTurn() && option1 != null){
                    currentX = currentX+2;
                    currentY = currentY-2;
                    this.kingCheckRed();
                    this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                  (currentY) *  CheckersTile.TILE_HEIGHT);
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX-1, currentY+1).getPiece().getChildren().clear();
                    board.getIndex(currentX-1, currentY+1).setPiece(null);
                    board.getIndex(currentX-2, currentY+2).setPiece(null);
                    this.endRedTurn();
                }//if
            });//MouseEvent
    }//RedAttack

    /**
     *JavaDoc
     *
     */
    public void redAttack2(){
        //Secondary movements & actions for red pieces attacking
        option2 = board.getIndex(currentX-2, currentY-2);
        option2.setStrokeWidth(3);
        option2.setOnMousePressed(e-> {
                if(board.isRedTurn() && option2 != null){
                    currentX = currentX-2;
                    currentY = currentY-2;
                    this.kingCheckRed();
                    this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                  (currentY) *  CheckersTile.TILE_HEIGHT);
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX+1, currentY+1).getPiece().getChildren().clear();
                    board.getIndex(currentX+1, currentY+1).setPiece(null);
                    board.getIndex(currentX+2, currentY+2).setPiece(null);
                    this.endRedTurn();
                }//if
            });//MouseEvent
    }//RedAttack2

    /**
     *JavaDoc
     *
     */
    public void bluePath0(){
        //Handles movement for blue pieces at x = 0
        option1 = board.getIndex(currentX+1, currentY+1);
        if(option1.isOpen()){
            this.blueLeftSide();
        }//if
        else if(option1.getPiece().getType() == Piece.RED){
            if(board.getIndex(currentX+2, currentY+2).isOpen()){
                this.blueAttack();
            }//if
        }//else-if
    }//BluePath0

    /**
     *JavaDoc
     *
     */
    public void bluePath7(){
        //Handles movement for blue pieces at x = 7
        option2 = board.getIndex(currentX-1, currentY+1);
        if(option2.isOpen()){
            this.blueRightSide();
        }//if
        else if(option2.getPiece().getType() == Piece.RED && currentY < 6){
            if(board.getIndex(currentX-2, currentY+2).isOpen()){
                this.blueAttack2();
            }//if
        }//else-if
    }//BluePath7

    /**
     *JavaDoc
     *
     */
    public void bluePaths(){
        //Handles movement for blue pieces when x is between 1 and 7
        option1 = board.getIndex(currentX+1, currentY+1);
        option2 = board.getIndex(currentX-1, currentY+1);
        if(option1.isOpen()){
            this.blueCenter();
        }//if
        else if(currentX != 6 && currentY < 6){
            if(option1.getPiece().getType() == Piece.RED){
                if(board.getIndex(currentX+2, currentY+2).isOpen()){
                    this.blueAttack();
                }//if
            }//if
        }//else-if           
        if(option2.isOpen()){
            this.blueCenter2();
        }//if
        else if(currentX != 1 && currentY < 6){
            if(option2.getPiece().getType() == Piece.RED){
                if(board.getIndex(currentX-2, currentY+2).isOpen()){
                    this.blueAttack2();
                }//if
            }//if
        }//else-if
    }//BluePaths

    /**
     *JavaDoc
     *
     */
    public void blueLeftSide(){
        //Movement for blue piece when x = 0
        option1.setStrokeWidth(3);
        option1.setOnMousePressed(e-> {
                if(board.isBlueTurn() && option1 != null){
                    currentX++;
                    currentY++;
                    this.kingCheckBlue();
                    this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                  (currentY) *  CheckersTile.TILE_HEIGHT);
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX-1, currentY-1).setPiece(null);
                    this.endBlueTurn();
                }//if
            });//MouseEvent   
    }//BlueLeftSide

    /**
     *JavaDoc
     *
     */
    public void blueRightSide(){
        //Movement for blue piece when x = 7
        option2.setStrokeWidth(3);
        option2.setOnMousePressed(e-> {
                if(board.isBlueTurn() && option2 != null){
                    currentX--;
                    currentY++;
                    this.kingCheckBlue();
                    this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                  (currentY) *  CheckersTile.TILE_HEIGHT);
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX+1, currentY-1).setPiece(null);
                    this.endBlueTurn();
                }//if
            });//MouseEvent
    }//BlueRightSide

    /**
     *JavaDoc
     *
     */
    public void blueCenter(){
        //Movement option 1 for blue pieces not on x = 1 or x = 7
        option1.setStrokeWidth(3);
        option1.setOnMousePressed(e-> {
                if(board.isBlueTurn() && option1 != null && option2 != null){
                    currentX++;
                    currentY++;
                    this.kingCheckBlue();
                    this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                  (currentY) *  CheckersTile.TILE_HEIGHT);
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX-1, currentY-1).setPiece(null);
                    this.endBlueTurn();
                }//if
            });//MouseEvent 
    }//BlueCenter

    /**
     *JavaDoc
     *
     */
    public void blueCenter2(){
        //Movement option 2 for blue pieces not on x = 1 or x = 7
        option2.setStrokeWidth(3);
        option2.setOnMousePressed(e-> {
                if(board.isBlueTurn() && option1 != null && option2 != null){
                    currentX--;
                    currentY++;
                    this.kingCheckBlue();
                    this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                  (currentY) *  CheckersTile.TILE_HEIGHT);
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX+1, currentY-1).setPiece(null);
                    this.endBlueTurn();
                }//if
            });//MouseEvent
    }//BlueCenter2

    /**
     *JavaDoc
     *
     */
    public void blueAttack(){
        //Movements & actions for blue pieces attacking
        option1 = board.getIndex(currentX+2, currentY+2);
        option1.setStrokeWidth(3);
        option1.setOnMousePressed(e-> {
                if(board.isBlueTurn() && option1 != null){
                    currentX = currentX+2;
                    currentY = currentY+2;
                    this.kingCheckBlue();
                    this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                  (currentY) *  CheckersTile.TILE_HEIGHT);
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX-1, currentY-1).getPiece().getChildren().clear();
                    board.getIndex(currentX-1, currentY-1).setPiece(null);
                    board.getIndex(currentX-2, currentY-2).setPiece(null);
                    this.endBlueTurn();
                }//if
            });//MouseEvent
    }//BlueAttack

    /**
     *JavaDoc
     *
     */
    public void blueAttack2(){
        //Secondary movements & actions for blue pieces attacking
        option2 = board.getIndex(currentX-2, currentY+2);
        option2.setStrokeWidth(3);
        option2.setOnMousePressed(e-> {
                if(board.isBlueTurn() && option2 != null){
                    currentX = currentX-2;
                    currentY = currentY+2;
                    this.kingCheckBlue();
                    this.relocate((currentX) *  CheckersTile.TILE_WIDTH,
                                  (currentY) *  CheckersTile.TILE_HEIGHT);
                    board.getIndex(currentX, currentY).setPiece(this);
                    board.getIndex(currentX+1, currentY-1).getPiece().getChildren().clear();
                    board.getIndex(currentX+1, currentY-1).setPiece(null);
                    board.getIndex(currentX+2, currentY-2).setPiece(null);
                    this.endBlueTurn();
                }//if
            });//MouseEvent
    }//BlueAttack2
}//Class
