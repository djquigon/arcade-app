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
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.scene.input.KeyCode;

/**
 * Represents a stage object for a space invaders game.
 */
public class SpaceStage extends Stage{

    //Constants
    public static final int MAX_X_RIGHT = 375; //right limit
    public static final int MAX_X_LEFT = -375; //left limit
    public static final int MAX_Y_UP = -320; //top limit
    public static final int MAX_Y_DOWN = 280; //where ship starts

    //Instance Variables
    private User user;
    private Ship ship; //the user's ship
    private AlienGroup aliens; //the group of aliens
    private Scene scene; //the main scene
    private Scene help; //the help scene
    private VBox window; //contains main and menubar
    private MenuBar menuBar; //menubar
    private StackPane main; //contains the ship and aliens
    private int score;
    private Text tScore;
    private int lives;
    private Text tLives;
    private int level;
    private Text tLevel;
    private boolean startGame;

    /**
     * Creates the main Stage for the a Space Invaders game.
     */
    public SpaceStage(){
        //Constructs stage
        super();
        this.setMenuBar(); //initializes menuBar
        window = new VBox();
        main = new StackPane();
        main.setPrefSize(800,680);
        BackgroundImage bi= new BackgroundImage
            (new Image("background_spaceinvaders.png",800,680,false,true),
             BackgroundRepeat.NO_REPEAT,
             BackgroundRepeat.NO_REPEAT,
             BackgroundPosition.DEFAULT,
             BackgroundSize.DEFAULT);
        main.setBackground(new Background(bi)); //creates background
        level = 1;
        score = 0;
        lives = 3;
        this.setTexts();
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
    }//SpaceStage Constructor
/*
  public void displayHelp(){
        VBox container = new VBox();
        Image background = new Image("background_spaceinvaders.png",800,680,false,true);
        BackgroundImage bi= new BackgroundImage(background,
        BackgroundRepeat.NO_REPEAT,
                                                BackgroundRepeat.NO_REPEAT,
                                                BackgroundPosition.DEFAULT,
                                                BackgroundSize.DEFAULT);
        container.setBackground(new Background(bi)); //creates background
        Text helpText = new Text(" How to Play:\n " +
                                 "You are earth's last hope against the invading aliens! " +
                                 "Clear all three levels to claim victory" +  
                                 "and restore peace once again, but be careful you " +
                                 "only have 300 health!\n" +
                                 "\nControls:\n" +
                                 "Left Arrow Key = moves ship to the left\n" +
                                 "Right Arrow Key = moves ship to the right\n" +
                                 "SpaceBar = Shoot lasers\n" +
                                 "\nTips:\n" +
                                 "1) Each level becomes a bit harder so be vigilant\n" +
                                 "2) Don't get hit by an alien's projectiles \n" +
                                 "3) Each hit from an alien laser lowers your health 100 points\n" + 
                                 "4) Don't make direct contact  with an alien spacecraft\n" +
                                 "\nPress the SpaceBar to start!");
        helpText.setFill(Color.LAWNGREEN);
        container.getChildren().add(helpText);
        Scene help = new Scene(container, 800, 680);
        this.setScene(help);
        startGame = false;
        while(startGame == false){
            help.setOnKeyPressed(event-> {
                    if(event.getCode() == KeyCode.SPACE){ //if spacebar clicked
                        startGame = true;
                        this.setScene(scene);
                        }
                });
                }
    }
*/
    /**
     * Sets the text objects of the stage.
     */
    public void setTexts(){
        tLevel = new Text("Level: " + level);
        tLevel.setFill(Color.LAWNGREEN);
        tScore = new Text("Score: " + score);
        tScore.setFill(Color.LAWNGREEN);
        tLives = new Text("HP: " + (lives*100));
        tLives.setFill(Color.LAWNGREEN);
        main.getChildren().addAll(tLevel, tScore, tLives);
        main.setAlignment(tLevel, Pos.BOTTOM_RIGHT);
        main.setAlignment(tScore, Pos.BOTTOM_RIGHT);
        main.setAlignment(tLives, Pos.BOTTOM_RIGHT);
        main.setMargin(tLives, new Insets(0, 15, 20, 0));
        main.setMargin(tLevel, new Insets(0, 15, 40, 0));
        main.setMargin(tScore, new Insets(0, 15, 60, 0));
    }//SetText
    
    /**
     * Sets the contents of the {@code MenuBar}.
     */
    public void setMenuBar(){
        //Sets menu bar for space invaders stage
        menuBar = new MenuBar();
        Menu file = new Menu("File");
        MenuItem exit = new MenuItem("Exit to Main Menu");
        exit.setOnAction(e -> {
                aliens.getAlienAttack().stop();
                aliens.getMoveAliens().stop();
                this.close();
            });//SetOnAction
        file.getItems().add(exit);
        Menu help = new Menu("Help");
        MenuItem about = new MenuItem("How to Play");
        help.getItems().add(about);
        menuBar.getMenus().addAll(file, help);
        menuBar.prefWidthProperty().bind(this.widthProperty());
    }//SetMenuBar

    /**
     * Levels up user to new difficulty and checks win condition.
     */
    public void levelUp(){
        level++;
        tLevel.setText("Level: " + level);
        //If user has finished level 3, then victory
        if(level == 4){
            this.victory();
            return;
        }//if
        aliens.repopulate(this, ship);
    }//LevelUp
    
    /**
     * A message pops up that prompts the user to exit or play
     * again once he/she wins.
     */
    public void victory(){
        Runnable r = () -> {
            ButtonType playAgain = new ButtonType("Play Again");
            ButtonType exitToMenu = new ButtonType("Exit to menu");
            Alert win = new Alert(AlertType.CONFIRMATION, "You won! " +
                                  "Play again?",
                                  playAgain, exitToMenu);
            win.setTitle("VICTORY");
            win.showAndWait().ifPresent(response -> {                                
                    if(response == playAgain){ //if they want to play again
                        this.close();
                        SpaceStage newGame = new SpaceStage();
                        newGame.show();
                    }//if
                    if(response == exitToMenu){ //if they want to exit
                        this.close();
                    }//if
                });
        };
        Platform.runLater(r);
    }//Victory
    
    /**
     * A message pops up that prompts the user to exit or play
     * again once he/she loses.
     */
    public void lose(){
        Runnable r = () -> {
            ButtonType playAgain = new ButtonType("Play Again");
            ButtonType exitToMenu = new ButtonType("Exit to menu");
            Alert lose = new Alert(AlertType.CONFIRMATION, "You have been defeated!  " +
                                   "Play again?",
                                   playAgain, exitToMenu);
            lose.setTitle("GAME OVER");
            lose.showAndWait().ifPresent(response -> {                                
                    if(response == playAgain){ //if they want to play again
                        this.close();
                        SpaceStage newGame = new SpaceStage();
                        newGame.show();
                    }//if
                    if(response == exitToMenu){ //if they want to exit
                        this.close();
                    }//if
                });
        };
        Platform.runLater(r);
    }//Lose
    
    /**
     * Returns the main {@code StackPane}.
     *
     * @return main the stackpane from the map
     */
    public StackPane getMain(){
        return main;
    }//GetMain

    /**
     * Returns the alien {@code Group} attacking the users ship.
     *
     * @return the alien {@code Group} attacking the users ship.
     */
    public AlienGroup getAlienGroup(){
        return aliens;
    }//GetAlienGroup
    
    /**
     * Returns the user's score.
     *
     * @return score the amount of points the user has gotten
     */
    public int getScore(){
        return score;
    }//GetScore
    
    /**
     * Sets the user's score.
     *
     * @param the user's score
     */
    public void setScore(int score){
        this.score = score;
    }//SetScore
    
    /**
     * Returns the user's lives.
     *
     * @return lives the number of lives the user has remaining
     */
    public int getLives(){
        return lives;
    }//GetLives
    
    /**
     * Sets the user's lives.
     *
     * @param the user's lives
     */
    public void setLives(int lives){
        this.lives = lives;
    }//SetLives
    
    /**
     * Returns the current level.
     *
     * @return level the current level of the game
     */
    public int getLevel(){
        return level;
    }//GetLevel
    
    /**
     * Sets the level of the game.
     *
     * @param level the level of the game
     */
    public void setLevel(int level){
        this.level = level;
    }//SetLevel
    
    /**
     * Sets the text object for lives.
     *
     * @param lives the number of lives
     */
    public void setLivesText(int lives){
        tLives.setText("HP: " + (lives * 100));
    }//SetLivesText
    
    /**
     * Sets the text object for score.
     *
     * @param score the total score
     */
    public void setScoreText(int score){
        tScore.setText("Score: " + score);
    }//SetScoreText
}//Class
