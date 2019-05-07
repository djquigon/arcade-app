package cs1302.arcade;

import cs1302.arcade.*;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.text.Text;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;

/**
 * Creates the main menu display for the user.
 */
public class MainMenu extends VBox{

    //Instance variables
    MenuBar menuBar;
    ImageView iv1;
    Button b1;
    ImageView iv2;
    Button b2;
    HBox bottomBar;
    Text credit;
    
    /**
     * Constructs an instance of the main menu to be used.
     */
    public MainMenu(){
        //Main Menu constructor
        super();
        BackgroundImage bi= new BackgroundImage
            (new Image("background_menu.png",800,680,false,true),
             BackgroundRepeat.NO_REPEAT,
             BackgroundRepeat.NO_REPEAT,
             BackgroundPosition.DEFAULT,
             BackgroundSize.DEFAULT);
        this.setBackground(new Background(bi));
        this.setMenuBar(); //create menuBar
        this.setGames(); //create games
    }//MainMenu Constructor
    
    /**
     * Sets the menu bar on the application.
     */
    public void setMenuBar(){
        //Creates menu bar with an action for the "exit"
        //and adds it to the main menu
        menuBar = new MenuBar();
        Menu file = new Menu("File");
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e-> System.exit(0));
        file.getItems().add(exit);
        menuBar.getMenus().addAll(file);
        menuBar.setTranslateY(0);
        this.getChildren().add(menuBar);
    }//SetMenuBar
    
    /**
     * Sets the main menu graphics for both games.
     */
    public void setGames(){
        //Sets the games up in main menu with the correct formating
        this.spaceInvaders();
        this.checkers();
        credit = new Text("By Logan Scheid and Ano Karadaghi");
        credit.setFont(new Font(8));
        credit.setTranslateX(25);
        credit.setTranslateY(360);
        credit.setFill(Color.WHITE);
        this.getChildren().addAll(iv1, b1, iv2, b2, credit);
    }//SetGames
    
    /**
     * Sets an image for the Space Invaders game and an action
     * to start the game once the button is pressed.
     */
    public void spaceInvaders(){
        //Sets up the Space Invaders game image with a launch action
        //associated with the button
        iv1 = new ImageView(new Image("spaceinvaders.png", 115, 115, false, true));
        b1 = new Button("Space Invaders");
        b1.setStyle("-fx-background-color: #ff0000;\n" + 
                    "-fx-focus-color: transparent;\n" + 
                    "-fx-faint-focus-color: transparent;\n");
        b1.setOnAction(e -> {
                SpaceStage si = new SpaceStage();
                si.showAndWait();
            });
        iv1.setTranslateX(345);
        iv1.setTranslateY(130);
        b1.setPrefSize(135, 25);
        b1.setTranslateY(140);
        b1.setTranslateX(335);
    }//SpaceInvaders

    
    /**
     * Sets an image for the Checkers game and an action
     * to start the game once the button is pressed.
     */
    public void checkers(){
        //Sets up the checkers game image with a launch action
        //associated with the button
        iv2 = new ImageView(new Image("Checkers.png", 115, 115, false, true)); 
        b2 = new Button("Checkers");
        b2.setStyle("-fx-background-color: #ff0000;\n" + 
                    "-fx-focus-color: transparent;\n" + 
                    "-fx-faint-focus-color: transparent;\n");
        b2.setOnAction(e -> {
                CheckersStage checkers = new CheckersStage();
                checkers.showAndWait();
            });
        iv2.setTranslateX(345);
        iv2.setTranslateY(160);
        b2.setPrefSize(135, 25);
        b2.setTranslateY(170);
        b2.setTranslateX(335);
    }//Checkers
}//Class
