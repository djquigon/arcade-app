package cs1302.arcade;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import cs1302.arcade.*;

/**
 * Represents an arcade app for cs1302.
 */
public class ArcadeApp extends Application {
    
    MainMenu mainMenu;
    
    /** {@inheritdoc} */
    @Override
    public void start(Stage stage) {
        mainMenu = new MainMenu();
        Scene scene = new Scene(mainMenu, 800, 680);
        stage.setResizable(false);
        stage.setTitle("cs1302-arcade!");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
        mainMenu.requestFocus();
    } // start

} // ArcadeApp
