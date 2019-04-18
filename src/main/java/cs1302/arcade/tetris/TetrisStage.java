package cs1302.arcade;

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
//
import javafx.scene.layout.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
//

public class TetrisStage extends Stage{

    
    Group layout;
    MenuBar menuBar;

    public final int HEIGHT = 20;
    public final int WIDTH = 10;
    private final Color[] colors = {Color.WHITE, Color.ORANGE, Color.GREEN, Color.CYAN, Color.MAGENTA, Color.BLUE, Color.YELLOW, Color.RED};
    //private static final int X_OFFSET = 20, Y_OFFSET = 20;
    /* private static final int X_OFFSET = 20, Y_OFFSET = 20, NEXTPIECEX = 360, NEXTPIECEY = 40;
    private static final int HEIGHT = 680, WIDTH = 640, BLOCK_SIZE = 32;
    private static int level = 1;
    private static double score;
    private static int lines;
    Canvas canvas = new Canvas(WIDTH, HEIGHT);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    */    

    HBox hb = new HBox();
    VBox vb = new VBox();
    VBox vb2 = new VBox();
    
    //TetrisBoard board = new TetrisBoard();
    public TetrisStage(){
        super();
        //This should go in board class just experimenting here
        Rectangle[][] rect = new Rectangle[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            hb = new HBox();
            for (int j = 0; j < WIDTH; j++) {
                rect[i][j] = new Rectangle(35.0,35.0, Color.TRANSPARENT);
                rect[i][j].setStroke(Color.BLACK);
                hb.getChildren().add(rect[i][j]);
            }
            vb.getChildren().add(hb);
        }

        //Gonna need this so seperate to call multiple times
        
        /*for (int i = 0; i < rect.length; i++) {
            hBox = new HBox();
            for (int j = 0; j < rect[0].length; j++) {
                hBox.getChildren().add(rect[i][j]);
            }
            vBox.getChildren().add(hBox);

            }*/
       
         //
         layout = new Group();
         //drawBoard(gc);
         this.setMenuBar();
         //layout.getChildren().addAll(menuBar,vb);
         Scene window = new Scene(vb, 800, 680);
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
    
    /*private void drawBoard(GraphicsContext g2d) {
        // g2d.setFont(new Font("Comic Sans MS", 20));
        g2d.setFill(Color.GRAY);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);
        g2d.setFill(Color.WHITE);
        // g2d.fillText("Next Piece:", NEXTPIECEX, NEXTPIECEY-10);
        g2d.fillText("Score: "+(int)score, NEXTPIECEX, 390);
        g2d.fillText("Lines: "+lines, NEXTPIECEX, 430);
        g2d.fillText("Level: "+level, NEXTPIECEX, 470);
        for(int j=0; j<board.HEIGHT; j++)
            for(int i=0; i<board.WIDTH; i++) {
                g2d.setFill(colors[board.getBlock(i, j)]);
                g2d.fillRect(X_OFFSET+i*BLOCK_SIZE, Y_OFFSET+j*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
            }
            }// drawBoard*/
    
    
}
