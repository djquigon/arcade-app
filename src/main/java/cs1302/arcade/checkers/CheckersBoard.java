package cs1302.arcade;

import cs1302.arcade.*;
import javafx.scene.Group;
import javafx.scene.layout.VBox;

public class CheckersBoard extends VBox{

    public static final int BOARD_WIDTH = 8;
    public static final int BOARD_HEIGHT = 8;

    private Group pieces;
    private Group tiles;

    public CheckersBoard(){
        super();
        pieces = new Group();
        tiles = new Group();
        for(int x = 0; x < BOARD_WIDTH; x++){
            for(int y = 0; y < BOARD_HEIGHT; y++){
                CheckersTile tile = new CheckersTile((x+y) % 2 == 0, x, y);
                tiles.getChildren().add(tile);
            }
        }
        this.getChildren().add(tiles);
    }
}
