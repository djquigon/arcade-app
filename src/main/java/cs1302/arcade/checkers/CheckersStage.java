package cs1302.arcade;

import cs1302.arcade.*;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Paint;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Button;

/**
 * Represents a stage object for a checkers game. Contains 
 * a menubar, a board and a section containing info about the game.
 */
public class CheckersStage extends Stage{

    //string for howTo Text
    private static final String howToPlay = "HOW TO PLAY:\n\n"
        + "REQUIRES TWO PLAYERS\n\n"
        + "Win condition: Take all of the\n"+"opposing team's pieces. A\n"
        + "piece can be taken by jumping\n"+ "over it to an empty tile using\n"
        + "one of your team's pieces.\n\n"
        + "Lose condition: No pieces\n"+"or moves remaining.\n\n"
        + "USER CONTROLS:\n\n"
        + "Click on a piece to move and\n"+"see possible movements,\n"
        + "and select where you want it\n"+"to go. Pieces can only be moved\n"+" diagonally.\n\n"
        + "MISC:\n\n"
        + "How to turn a piece into a king?\n"
        + "Reach the opposite side\n"+"of the board.\n\n"
        + "What can a king do?\n"
        + "A king can move either forwards\n"+"or backwards.\n\n"
        + "What if all my pieces are stuck?\n"
        + "If all your remaining pieces\n"+"have no possible moves,\n"
        + "this is technically a loss.\n"+"You must click the forfeit\n"+"button to end the game.";
        
    //Instance Variables
    private Scene scene; //scene
    private VBox window; //window to contain main
    private HBox main; //container for the main displayed objects
    private MenuBar menuBar; //menus
    private CheckersBoard board; //the board
    private VBox info; //contains info about the game
    private Text turn; //whose turn it is
    private Text piecesTaken; //num pieces taken for both teams
    private Button forfeit; //button to forfeit

    /**
     * Creates a stage for a checkers game with all properties set.
     */
    public CheckersStage(){
        super();
        window = new VBox();
        menuBar = new MenuBar();
        this.setMenuBar();
        main = new HBox();
        board = new CheckersBoard(this);
        this.setInfo();
        main.getChildren().addAll(board, info);
        window.getChildren().addAll(menuBar, main);
        BackgroundImage bi= new BackgroundImage(new Image("background_checkers.png",800,680,false,true),
                                                BackgroundRepeat.NO_REPEAT,
                                                BackgroundRepeat.NO_REPEAT,
                                                BackgroundPosition.DEFAULT,
                                                BackgroundSize.DEFAULT);
        window.setBackground(new Background(bi)); //creates background
        scene = new Scene(window, 800, 680);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("C H E C K E R S");
        this.setMinWidth(800);
        this.setMinHeight(680);
        this.setResizable(false);
        this.sizeToScene();
        this.setScene(scene);
    }//Constructor

    /**
     * Sets the properties of the {@code menuBar}.
     */
    public void setMenuBar(){
        Menu file = new Menu("File");
        MenuItem exit = new MenuItem("Exit to Main Menu");
        exit.setOnAction(e -> this.close());
        file.getItems().add(exit);
        menuBar.getMenus().addAll(file);
        menuBar.prefWidthProperty().bind(this.widthProperty());
    }//SetMenuBar
        
    /**
     * Sets the contents of the {@code info} VBox.
     */
    public void setInfo(){
        turn = new Text("Red's Turn"); //red's turn at start
        turn.setFont(new Font(20));
        turn.setFill(Color.RED);
        piecesTaken = new Text("Blue Pieces Taken: " + 0 + "\n" //0 pieces taken at start
                               + "Red Pieces Taken: " + 0);
        piecesTaken.setFill(Color.WHITE);
        piecesTaken.setTextAlignment(TextAlignment.CENTER);
        Text howTo = new Text(howToPlay);
        howTo.setFill(Color.WHITE);
        howTo.setFont(new Font(9));
        howTo.setTextAlignment(TextAlignment.CENTER);
        forfeit = new Button("Forfeit"); //button for forfeiting
        forfeit.setOnAction(e-> {
                if(board.isRedTurn()){
                    board.setRPiecesLeft(0); //0 pieces causes the game to end
                }
                else{
                    board.setBPiecesLeft(0);
                }
            });
        info = new VBox(25);
        info.setMargin(turn, new Insets(0, 50, 0, 20));
        info.setMargin(piecesTaken, new Insets(0, 0, 0, 10));
        info.setMargin(howTo, new Insets(10, 0, 0, 3));
        info.setMargin(forfeit, new Insets(50, 50, 20, 40));
        info.getChildren().addAll(turn, piecesTaken, howTo, forfeit);
        info.setBackground(new Background(new BackgroundFill(Paint.valueOf("#1a1a1a"), null, null)));
    }//SetInfo

    /**
     * Returns the {@code turn} instance variable.
     *
     * @return whose turn it is
     */
    public Text getTurnText(){
        return turn;
    }//GetTurnText

    /**
     * Returns the {@code piecesTaken} instance variable.
     *
     * @return how many pieces have been taken
     */
    public Text getPiecesTaken(){
        return piecesTaken;
    }//getPiecesTaken

}//Class
