package cs1302.arcade;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.Group;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.control.Separator;
import javafx.geometry.Orientation;
import javafx.scene.text.Text;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Background;
import cs1302.arcade.*;

public class MainMenu extends VBox{

    MenuBar menuBar;
    Group games;
    HBox bottomBar;

    public MainMenu(){
        super();
        BackgroundImage bi= new BackgroundImage(new Image("background_menu.png",800,680,false,true),
                                                  BackgroundRepeat.NO_REPEAT,
                                                  BackgroundRepeat.NO_REPEAT,
                                                  BackgroundPosition.DEFAULT,
                                                  BackgroundSize.DEFAULT);
        this.setBackground(new Background(bi));
        //create menuBar
        menuBar = new MenuBar();
        this.setMenuBar(menuBar);
        
        //create games
        games = new Group();
        this.setGames(games);
        
        //create bottomBar
        //bottomBar = new HBox();
        //this.setBottomBar(bottomBar);
        
        //add all components to layout
        this.getChildren().addAll(menuBar, games); //bottombar
    }

    public void setMenuBar(MenuBar menuBar){
        Menu file = new Menu("File");
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e-> System.exit(0));
        file.getItems().add(exit);
        menuBar.getMenus().addAll(file);
    }
    
    public void setGames(Group games){
        ImageView iv1 = new ImageView(new Image("Checkers.png", 115, 115, false, true));
        ImageView iv2 = new ImageView(new Image("Checkers.png", 115, 115, false, true)); 
        Button b1 = new Button("Play: Tetris");
        b1.setOnAction(e -> {
                TetrisStage tetris = new TetrisStage();
                tetris.showAndWait();
                    });
        b1.setMaxSize(200, 1000);
        Button b2 = new Button("Play: Checkers");
        b2.setOnAction(e -> {
                CheckersStage checkers = new CheckersStage();
                checkers.showAndWait();
            });
        games.getChildren().addAll(iv1, b1, iv2, b2);
        b2.setMaxSize(200, 1000);
        iv1.setTranslateX(400);
        b1.setTranslateX(400);
        iv2.setTranslateX(400);
        b2.setTranslateX(400);
        iv1.setTranslateY(400);
        b1.setTranslateY(400);
        iv2.setTranslateY(400);
        b2.setTranslateY(400);
    }

    public void setBottomBar(HBox bottomBar){
        Text credit = new Text("Courtesy of team Lano");
        bottomBar.getChildren().addAll(credit);
    }
}
