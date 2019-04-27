package cs1302.arcade;

import cs1302.arcade.*;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class CheckersBoard extends Group{

    public static final int BOARD_WIDTH = 8;
    public static final int BOARD_HEIGHT = 8;

    private CheckersTile[][] board = new CheckersTile[BOARD_WIDTH][BOARD_HEIGHT]; //track tiles
    private Group pieces; //group for the pieces
    private Group tiles; //group for the tiles

    public CheckersBoard(){
        super();
        pieces = new Group();
        tiles = new Group();
        for(int x = 0; x < BOARD_WIDTH; x++){
            for(int y = 0; y < BOARD_HEIGHT; y++){
                CheckersTile tile = new CheckersTile((x+y) % 2 == 0, x, y);
                tile.setStroke(Color.LAWNGREEN);
                tile.setStrokeWidth(0);
                board[x][y] = tile;
                tiles.getChildren().add(tile);
                CheckersPiece piece = null;
                if(y<=2 && (x+y)%2 != 0){
                    piece = new CheckersPiece(this, Piece.BLUE, x, y);
                }

                if(y>=5 && (x+y)%2 != 0){
                    piece = new CheckersPiece(this, Piece.RED, x, y);
                }

                if(piece != null){
                    tile.setPiece(piece);
                    pieces.getChildren().add(piece);
                }
            }
        }
        this.getChildren().addAll(tiles, pieces);
    }

    public CheckersTile getBoardIndex(int x, int y){
        return board[x][y];
    }
}
