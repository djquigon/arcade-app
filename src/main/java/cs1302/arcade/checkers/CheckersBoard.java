package cs1302.arcade;

import cs1302.arcade.*;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *JavaDoc
 *
 */
public class CheckersBoard extends VBox{

    public static final int BOARD_WIDTH = 8;
    public static final int BOARD_HEIGHT = 8;

    //Instance variables
    private CheckersStage stageRef;
    private CheckersTile[][] board = new CheckersTile[BOARD_WIDTH][BOARD_HEIGHT]; //track tiles
    private Group container; //container for pieces and tiles
    private Group pieces; //group for the pieces
    private Group tiles; //group for the tiles
    private boolean isRedTurn;
    private boolean isBlueTurn;
    private int rPiecesLeft;
    private int bPiecesLeft;

    /**
     *JavaDoc
     *
     */
    public CheckersBoard(CheckersStage ref){
        super();
        stageRef = ref;
        container = new Group();
        pieces = new Group();
        tiles = new Group();
        this.createPieces();
        container.getChildren().addAll(tiles, pieces);
        this.getChildren().add(container);
        
        String cssLayout = "-fx-border-color: #262626;\n" +
            "-fx-border-insets: 0;\n" +
            "-fx-border-width: 12;\n" +
            "-fx-border-style: solid;\n" +
            "-fx-outline-style: solid;\n" +
            "-fx-outline-width: 6;\n" +
            "-fx-outline-color: red;\n";
        
        this.setStyle(cssLayout); //outline not working?
        isRedTurn = true; //red's turn first
        isBlueTurn = false;
        rPiecesLeft = 12;
        bPiecesLeft = 12;
    }//Contructor

    /**
     *JavaDoc
     *
     */
    public void createPieces(){
        for(int x = 0; x < BOARD_WIDTH; x++){ //populate rows
            for(int y = 0; y < BOARD_HEIGHT; y++){ //populate columns
                CheckersTile tile = new CheckersTile(this, (x+y) % 2 == 0, x, y);
                tile.setStroke(Color.LAWNGREEN);
                tile.setStrokeWidth(0);
                board[x][y] = tile;
                tiles.getChildren().add(tile);
                CheckersPiece piece = null;
                if(y<=2 && (x+y)%2 != 0){
                    piece = new CheckersPiece(this, Piece.BLUE, x, y);
                }//if
                if(y>=5 && (x+y)%2 != 0){
                    piece = new CheckersPiece(this, Piece.RED, x, y);
                }//if
                if(piece != null){
                    tile.setPiece(piece);
                    pieces.getChildren().add(piece);
                }//if
            }//for
        }//for
    }//CreatePieces

    /**
     *JavaDoc
     *
     */
    public CheckersTile getIndex(int x, int y){
        return board[x][y];
    }//GetIndex

    /**
     *JavaDoc
     *
     */
    public boolean isRedTurn(){
        return isRedTurn;
    }//IsRedTurn
    
    /**
     *JavaDoc
     *
     */
    public boolean isBlueTurn(){
        return isBlueTurn;
    }//IsBlueTurn
    
    /**
     *JavaDoc
     *
     */
    public void setIsRedTurn(boolean b){
        isRedTurn = b;
        if(!isRedTurn){
            stageRef.getTurnText().setText("Blue's Turn");
            stageRef.getTurnText().setFill(Color.ROYALBLUE);
        }//if
    }//SetIsRedTurn

    /**
     *JavaDoc
     *
     */
    public void setIsBlueTurn(boolean b){
        isBlueTurn = b;
        if(!isBlueTurn){
            stageRef.getTurnText().setText("Red's Turn");
            stageRef.getTurnText().setFill(Color.RED);
        }//if
    }//SetIsBlueTurn

    /**
     *JavaDoc
     *
     */
    public void clearHighlights(){
        for(int x = 0; x < BOARD_WIDTH; x++){
            for(int y = 0; y < BOARD_HEIGHT; y++){
                CheckersTile tile = board[x][y];
                tile.setStrokeWidth(0);
                if(tile.getPiece() != null){
                    CheckersPiece piece = tile.getPiece();
                    piece.clearOptions();
                }//if
            }//for
        }//for
    }//ClearHighlights
   
    /**
     *JavaDoc
     *
     */
    public void setRPiecesLeft(int rPiecesLeft){
        this.rPiecesLeft = rPiecesLeft;
        if(this.rPiecesLeft == 0){
            //alert box game over, red wins! play again/close stage
        }
    }//SetRPiecesLeft
    
    /**
     *JavaDoc
     *
     */
    public void setBPiecesLeft(int bPiecesLeft){
        this.bPiecesLeft = bPiecesLeft;
        if(this.bPiecesLeft == 0){
            //alert box game over, red wins! play again/close stage
        }
    }//SetBPiecesLeft
    
    /**
     *JavaDoc
     *
     */
    public int getRPiecesLeft(){
        return rPiecesLeft;
    }//GetRPiecesLeft

    /**
     *JavaDoc
     *
     */
    public int getBPiecesLeft(){
        return bPiecesLeft;
    }//GetBPiecesLeft
    
}//Class
