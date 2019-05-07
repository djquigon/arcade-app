package cs1302.arcade;

import cs1302.arcade.*;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Background;
import javafx.scene.image.Image;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.WindowEvent;

/**
 * Represents a stage object for a space invaders game.
 */
public class SpaceStage extends Stage{
    
    public static final int MAX_X_RIGHT = 375; //right limit
    public static final int MAX_X_LEFT = -375; //left limit
    public static final int MAX_Y_UP = -320; //top limit
    public static final int MAX_Y_DOWN = 280; //where ship starts

    private User user;
    private Ship ship; //the user's ship
    private AlienGroup aliens; //the group of aliens
    private Scene scene; //the main scene
    private VBox window; //contains main and menubar
    private MenuBar menuBar; //menubar
    private StackPane main; //contains the ship and aliens
    private int score;
    private int lives;
    private int level;

    /**
     * Creates the main Stage for the a Space Invaders game.
     */
    public SpaceStage(){
        super();
        this.setMenuBar(); //initializes menuBar
        window = new VBox();
        main = new StackPane();
        main.setPrefSize(800,680);
        BackgroundImage bi= new BackgroundImage(new Image("background_spaceinvaders.png",800,680,false,true),
                                                BackgroundRepeat.NO_REPEAT,
                                                BackgroundRepeat.NO_REPEAT,
                                                BackgroundPosition.DEFAULT,
                                                BackgroundSize.DEFAULT);
        main.setBackground(new Background(bi)); //creates background
        level = 1;
        score = 0;
        lives = 3;
        ship = new Ship(this);
        aliens = new AlienGroup(this, ship);
        window.getChildren().addAll(menuBar, main);
        scene = new Scene(window, 800, 680);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("S P A C E  I N V A D E R S");
        this.setMinWidth(800);
        this.setMinHeight(680);
        this.setResizable(false);
        this.sizeToScene();
        this.setScene(scene);
        user = new User();
        user.checkEvents(this, ship, aliens);
        this.setOnCloseRequest((WindowEvent event) -> {
                aliens.getAlienAttack().stop();
                aliens.getMoveAliens().stop(); 
            });
    }

    /**
     * Sets the contents of the {@code MenuBar}.
     */
    public void setMenuBar(){
        menuBar = new MenuBar();
        Menu file = new Menu("File");
        MenuItem exit = new MenuItem("Exit to Main Menu");
        exit.setOnAction(e -> {
                this.close();
            });
        file.getItems().add(exit);
        Menu help = new Menu("Help");
        MenuItem about = new MenuItem("How to Play");
        help.getItems().add(about);
        menuBar.getMenus().addAll(file, help);
        menuBar.prefWidthProperty().bind(this.widthProperty());
    }

    public void levelUp(){
        aliens = new AlienGroup(this, ship);
    }

    public void victory(){
        ButtonType playAgain = new ButtonType("Play Again");
        ButtonType exitToMenu = new ButtonType("Exit to menu");
        Alert win = new Alert(AlertType.CONFIRMATION, "You won! " +
                               "Would you like to play again?",
                               playAgain, exitToMenu);
        win.setTitle("VICTORY");
        win.showAndWait().ifPresent(response -> {                                
                if(response == playAgain){ //if they want to play again
                    this.close();
                    SpaceStage newGame = new SpaceStage();
                    newGame.show();
                }
                if(response == exitToMenu){ //if they want to exit
                    this.close();
                }
            });
    }

    public void lose(){
        Runnable r = () -> {
            ButtonType playAgain = new ButtonType("Play Again");
            ButtonType exitToMenu = new ButtonType("Exit to menu");
            Alert lose = new Alert(AlertType.CONFIRMATION, "You have been defeated!  " +
                                   "Would you like to play again?",
                                   playAgain, exitToMenu);
            lose.setTitle("GAME OVER");
            lose.showAndWait().ifPresent(response -> {                                
                    if(response == playAgain){ //if they want to play again
                        this.close();
                        SpaceStage newGame = new SpaceStage();
                        newGame.show();
                    }
                    if(response == exitToMenu){ //if they want to exit
                        this.close();
                    }
                });
        };
        Platform.runLater(r);
    }
    
    /**
     * Returns the main {@code StackPane}.
     *
     * @return main the stackpane from the map
     */
    public StackPane getMain(){
        return main;
    }

    /**
     * Returns the alien {@code Group} attacking the users ship.
     *
     * @return the alien {@code Group} attacking the users ship.
     */
    public AlienGroup getAlienGroup(){
        return aliens;
    }

    /**
     * Returns the user's score.
     *
     * @return score the amount of points the user has gotten
     */
    public int getScore(){
        return score;
    }
    
    /**
     * Sets the user's score.
     *
     * @param the user's score
     */
    public void setScore(int score){
        this.score = score;
    }
    /**
     * Returns the user's lives.
     *
     * @return lives the number of lives the user has remaining
     */
    public int getLives(){
        return lives;
    }
    
    /**
     * Sets the user's lives.
     *
     * @param the user's lives
     */
    public void setLives(int lives){
        this.lives = lives;
    }

    /**
     * Returns the current level.
     *
     * @return level the current level of the game
     */
    public int getLevel(){
        return level;
    }

    /**
     * Sets the level of the game.
     *
     * @param level the level of the game
     */
    public void setLevel(int level){
        this.level = level;
    }
}
