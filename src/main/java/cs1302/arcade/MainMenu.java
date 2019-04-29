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
    Group game1;
    Group game2;
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
        game1 = new Group();
        game2 = new Group();
        this.setGames(game1, game2);
        
        //create bottomBar
        //bottomBar = new HBox();
        //this.setBottomBar(bottomBar);
        
        //add all components to layout
        this.getChildren().addAll(menuBar, game1, game2); //bottombar
    }

    public void setMenuBar(MenuBar menuBar){
        Menu file = new Menu("File");
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e-> System.exit(0));
        file.getItems().add(exit);
        menuBar.getMenus().addAll(file);
    }
    
    public void setGames(Group game1, Group game2){
        game1.setTranslateX(345);
        game1.setTranslateY(145);
        game2.setTranslateX(345);
        game2.setTranslateY(200);
        ImageView iv1 = new ImageView(new Image("Checkers.png", 115, 115, false, true));
        ImageView iv2 = new ImageView(new Image("Checkers.png", 115, 115, false, true)); 
        Button b1 = new Button("Space Invaders");
        b1.setOnAction(e -> {
                TetrisStage tetris = new TetrisStage();
                tetris.showAndWait();
                    });
        b1.setMinSize(5, 5);
        b1.setTranslateY(10);
        Button b2 = new Button("Checkers");
        b2.setOnAction(e -> {
                CheckersStage checkers = new CheckersStage();
                checkers.showAndWait();
            });
        b2.setMinSize(5, 5);
        b2.setTranslateY(10);
        game1.getChildren().addAll(iv1, b1);
        game2.getChildren().addAll(iv2, b2);
    }

    public void setBottomBar(HBox bottomBar){
        Text credit = new Text("Courtesy of team Lano");
        bottomBar.getChildren().addAll(credit);
    }
}
