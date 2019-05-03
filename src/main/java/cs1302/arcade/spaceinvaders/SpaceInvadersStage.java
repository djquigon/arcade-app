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

public class SpaceInvadersStage extends Stage{

    public static final int MAX_X_RIGHT = 375;
    public static final int MAX_X_LEFT = -375;
    public static final int MAX_Y_UP = -323;
    public static final int MAX_Y_DOWN = 280; //where ship starts

    
    private SpaceInvadersShip ship; 
    private Scene scene; //the main scene
    private VBox window; //window
    private MenuBar menuBar; //menubar
    private StackPane main; //contains board, sep and info

    /**
     * Creates the main Stage for the a Space Invaders game.
     */
    public SpaceInvadersStage(){
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
        ship = new SpaceInvadersShip(this);
        window.getChildren().addAll(menuBar, main);
        scene = new Scene(window, 800, 680);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("S P A C E  I N V A D E R S");
        this.setMinWidth(800);
        this.setMinHeight(680);
        this.setResizable(false);
        this.sizeToScene();
        this.setScene(scene);
        UserFunctions.checkEvents(this, ship);
    }

    //public void updateScreen(){}

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

    public StackPane getMain(){
        return main;
    }
}
