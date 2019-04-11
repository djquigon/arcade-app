package cs1302.arcade;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.control.Separator;
import javafx.geometry.Orientation;

public class MainMenu extends VBox{

    MenuBar menuBar;
    HBox games;
    HBox bottomBar;

    public MainMenu(){
        super();

        //create menuBar
        menuBar = new MenuBar();
        Menu file = new Menu("File");
        MenuItem exit = new MenuItem("Exit");
        file.getItems().add(exit);
        menuBar.getMenus().addAll(file);

        //create games
        games = new HBox();
        VBox game1 = new VBox();
        VBox game2 = new VBox();
        ImageView iv1 = new ImageView(); //remeber to set this to image1
        ImageView iv2 = new ImageView(); //remeber to set this to image2
        Image i1 = new Image("http://cobweb.cs.uga.edu/~mec/cs1302/gui/pikachu.png"); //pass in picture of game 1
        Image i2 = new Image("http://cobweb.cs.uga.edu/~mec/cs1302/gui/pikachu.png"); //pass in picture of game 2
        iv1.setImage(i1);
        iv2.setImage(i2);
        Button b1 = new Button("Play: (insert game)");
        b1.setMaxSize(200, 1000);
        Button b2 = new Button("Play: (insert game)");
        b2.setMaxSize(200, 1000);

        Separator sep1 = new Separator(Orientation.HORIZONTAL);
        Separator sep2 = new Separator(Orientation.HORIZONTAL);
        
        game1.getChildren().addAll(iv1, b1, sep1);
        game2.getChildren().addAll(iv2, b2, sep2);
        game1.setMargin(iv1, new Insets(50));
        game1.setMargin(b1, new Insets(0, 0, 50, 100));
        game2.setMargin(iv2, new Insets(50));
        game2.setMargin(b2, new Insets(0, 0, 50, 100));
        games.getChildren().addAll(game1, game2);
        
        //create bottomBar
        //bottomBar = new HBox();

        //add all components to layout
        this.getChildren().addAll(menuBar, games); //bottomBar
    }
}
