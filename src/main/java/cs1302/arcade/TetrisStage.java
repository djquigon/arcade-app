package cs1302.arcade;

//import javafx.scene.canvas.*;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.shape.Rectangle;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import cs1302.arcade.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;

public class TetrisStage extends Stage{

    
    Group layout;
    MenuBar menuBar;
    /*
    private final static Color[] colors = {Color.WHITE, Color.ORANGE, Color.GREEN, Color.CYAN, Color.MAGENTA, Color.BLUE, Color.YELLOW, Color.RED};
    private static final int X_OFFSET = 20, Y_OFFSET = 20;
    private static final int HEIGHT = 680, WIDTH = 640, BLOCK_SIZE = 32;
    Canvas canvas = new Canvas(WIDTH, HEIGHT);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    TetrisBoard board = new TetrisBoard();
    */
    
    public TetrisStage(){
        super();
        layout = new Group();
        /*
        drawBoard(gc);
        */
        this.setMenuBar();
        layout.getChildren().addAll(menuBar);
        Scene window = new Scene(layout, 800, 680);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("T e t r i s");
        this.setMinWidth(800);
        this.setMinHeight(680);
        this.setResizable(false);
        this.sizeToScene();
        this.setScene(window);
    }

    public void setMenuBar(){
        menuBar = new MenuBar();
        Menu file = new Menu("File");
        MenuItem exit = new MenuItem("Exit to Main Menu");
        exit.setOnAction(e -> this.close());
        file.getItems().add(exit);
        Menu help = new Menu("Help");
        MenuItem about = new MenuItem("How to Play");
        help.getItems().add(about);
        menuBar.getMenus().addAll(file, help);
        menuBar.prefWidthProperty().bind(this.widthProperty());
    }
    /*
    private void drawBoard(GraphicsContext g2d) {
    for(int j=0; j<TetrisBoard.HEIGHT; j++)
        for(int i=0; i<TetrisBoard.WIDTH; i++) {

        //System.out.println(board.getBlock(j, i));
        //System.out.println(colors[board.getBlock(j, i)]);
        //System.out.println("made it.");

        g2d.setFill(colors[board.getBlock(j, i)]);
        g2d.fillRect(X_OFFSET+i*BLOCK_SIZE, Y_OFFSET+j*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
        }
    }// drawBoard
    */
    
}
