package cs1302.arcade;

public class TetrisBoard{
    
    public final static int HEIGHT = 20;
    public final static int WIDTH = 10;
    private int[][] boardMatrix;

    /**
     *Creates a 2D array representing the tiles of the tetris board.
     */
    public TetrisBoard() {
        boardMatrix = new int[HEIGHT][WIDTH];
        for (int i = 0; i< HEIGHT; i++){
            for (int j = 0; j < WIDTH; j++){
                boardMatrix[i][j] = 0;
            }
        }
    }// Board
    
    public int getBlock(int y, int x) {
        //System.out.println("board.getBlock[][] = " + boardMatrix[y][x]);
        return boardMatrix[y][x];
    }// getBlock
    
}
