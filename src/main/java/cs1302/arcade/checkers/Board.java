package cs1302.arcade;

import cs1302.arcade.*;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.application.Platform;

/**
 * Represents a board for a checkers game. A board consists of
 * 64 tiles, in an 8x8 format.
 *
 */
public class Board extends VBox{

    //Constants
    public static final int BOARD_WIDTH = 8;
    public static final int BOARD_HEIGHT = 8;

    //Instance variables
    private CheckersStage stage;
    private Tile[][] board = new Tile[BOARD_WIDTH][BOARD_HEIGHT]; //track tiles
    private Group container; //container for pieces and tiles
    private Group pieces; //group for the pieces
    private Group tiles; //group for the tiles
    private boolean isRedTurn;
    private boolean isBlueTurn;
    private int rPiecesLeft; //red pieces left
    private int bPiecesLeft; //blue pieces left

    /**
     * Creates a board for a checkers game with all
     * properties set.
     *
     * @param ref a reference to the main {@code CheckersStage}
     */
    public Board(CheckersStage ref){
        //Board constructor
        super();
        stage = ref;
        container = new Group();
        pieces = new Group();
        tiles = new Group();
        this.createPiecesAndTiles();
        container.getChildren().addAll(tiles, pieces);
        this.getChildren().add(container);
        String cssLayout = "-fx-border-color: #262626;\n" + //boarder for board
            "-fx-border-insets: 0;\n" +
            "-fx-border-width: 12;\n" +
            "-fx-border-style: solid;\n"; 
        this.setStyle(cssLayout);
        isRedTurn = true; //red's turn first
        isBlueTurn = false;
        rPiecesLeft = 12;
        bPiecesLeft = 12;
    }//Contructor

    /**
     * Creates all tile and pieces and adds them to their
     * respective {@code Group}
     */
    public void createPiecesAndTiles(){
        for(int x = 0; x < BOARD_WIDTH; x++){ //populate rows
            for(int y = 0; y < BOARD_HEIGHT; y++){ //populate columns
                Tile tile = new Tile(this, (x+y) % 2 == 0, x, y);
                tile.setStroke(Color.LAWNGREEN);
                tile.setStrokeWidth(0);
                board[x][y] = tile;
                tiles.getChildren().add(tile);
                Piece piece = null;
                //Sets the correct positioning for blue pieces
                if(y<=2 && (x+y)%2 != 0){
                    piece = new Piece(this, PieceType.BLUE, x, y);
                }//if
                //Sets the correct positioning for red pieces
                if(y>=5 && (x+y)%2 != 0){
                    piece = new Piece(this, PieceType.RED, x, y);
                }//if
                if(piece != null){
                    tile.setPiece(piece);
                    pieces.getChildren().add(piece);
                }//if
            }//for
        }//for
    }//CreatePieces
    
    /**
     * Returns the tile at a given index in the board.
     *
     * @param x the x value in the board array
     * @param y the y value in the board array
     * @return the {@code Tile} at the given x,y position
     */
    public Tile getIndex(int x, int y){
        return board[x][y];
    }//GetIndex

    /**
     * Returns whether or not it is red's turn.
     *
     * @return true or false if it's red turn
     */
    public boolean isRedTurn(){
        return isRedTurn;
    }//IsRedTurn
    
    /**
     * Returns whether or not it is blue's turn.
     *
     * @return true or false if it's blue turn
     */
    public boolean isBlueTurn(){
        return isBlueTurn;
    }//IsBlueTurn
    
    /**
     * Sets whether it is red's turn to true or false.
     *
     * @param b true or false whether it should be red's turn
     */
    public void setIsRedTurn(boolean b){
        isRedTurn = b;
        if(!isRedTurn){
            stage.getTurnText().setText("Blue's Turn");
            stage.getTurnText().setFill(Color.ROYALBLUE);
        }//if
    }//SetIsRedTurn
    
    /**
     * Sets whether it is blue's turn to true or false
     *
     * @param b true or false whether it should be blue's turn
     */
    public void setIsBlueTurn(boolean b){
        isBlueTurn = b;
        if(!isBlueTurn){
            stage.getTurnText().setText("Red's Turn");
            stage.getTurnText().setFill(Color.RED);
        }//if
    }//SetIsBlueTurn

    /**
     * Sets the stroke of every tile on the board to 0.
     */
    public void clearHighlights(){
        //Clears the stroke on all tiles
        for(int x = 0; x < BOARD_WIDTH; x++){
            for(int y = 0; y < BOARD_HEIGHT; y++){
                Tile tile = board[x][y];
                tile.setStrokeWidth(0);
                if(tile.getPiece() != null){
                    Piece piece = tile.getPiece();
                    piece.clearOptions();
                }//if
            }//for
        }//for
    }//ClearHighlights
   
    /**
     * Sets the number of red pieces that are left.
     *
     * @param rPiecesLeft the number of red pieces left
     */
    public void setRPiecesLeft(int rPiecesLeft){
        this.rPiecesLeft = rPiecesLeft;
        stage.getPiecesTaken().setText
            ("Blue Pieces Taken: " + (12 - this.getBPiecesLeft()) + "\n"
             + "Red Pieces Taken: " + (12 - this.getRPiecesLeft()));
        if(this.rPiecesLeft == 0){ //if blue has won
            this.clearHighlights();
            ButtonType playAgain = new ButtonType("Play Again");
            ButtonType exitToMenu = new ButtonType("Exit to menu");
            Alert blueWins = new Alert(AlertType.CONFIRMATION, "Blue has won! " +
                                       "Play again?",
                                       playAgain, exitToMenu);
            blueWins.setTitle("GAME OVER");
            blueWins.showAndWait().ifPresent(response -> {                                
                    if(response == playAgain){ //if they want to play again
                        stage.close();
                        CheckersStage newGame = new CheckersStage();
                        newGame.show();
                    }//if
                    if(response == exitToMenu){ //if they want to exit
                        stage.close();
                    }//if
                });
        }
    }//SetRPiecesLeft
       
    /**
     * Sets the number of blue pieces that are left.
     *
     * @param bPiecesLeft the number of blue pieces left
     */
    public void setBPiecesLeft(int bPiecesLeft){
        this.bPiecesLeft = bPiecesLeft;
        stage.getPiecesTaken().setText
            ("Blue Pieces Taken: " + (12 - this.getBPiecesLeft()) + "\n"
             + "Red Pieces Taken: " + (12 - this.getRPiecesLeft()));
        if(this.bPiecesLeft == 0){ //if red has won
            this.clearHighlights();
            ButtonType playAgain = new ButtonType("Play Again");
            ButtonType exitToMenu = new ButtonType("Exit to menu");
            Alert redWins = new Alert(AlertType.CONFIRMATION, "Red has won! " +
                                      "Play again?",
                                      playAgain, exitToMenu);
            redWins.setTitle("GAME OVER");
            redWins.showAndWait().ifPresent(response -> {                                
                    if(response == playAgain){ //if they want to play again
                        stage.close();
                        CheckersStage newGame = new CheckersStage();
                        newGame.show();
                    }//if
                    if(response == exitToMenu){ //if they want to exit
                        stage.close();
                    }//of
                });
        }//if
    }//SetBPiecesLeft
    
    /**
     * Returns the number of red pieces left.
     *
     * @return the number of red pieces left
     */
    public int getRPiecesLeft(){
        return rPiecesLeft;
    }//GetRPiecesLeft

    /**
     * Returns the number of blue pieces left.
     *
     * @return the number of blue pieces left
     */
    public int getBPiecesLeft(){
        return bPiecesLeft;
    }//GetBPiecesLeft
    
}//Class
