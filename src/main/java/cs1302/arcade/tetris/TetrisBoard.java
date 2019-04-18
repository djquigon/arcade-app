package cs1302.arcade;

public class TetrisBoard{
    
    public final int HEIGHT = 20;
    public final int WIDTH = 10;
    public int[][] matrix;

    /**
     *Creates a 2D array representing the tiles of the tetris board.
     */
    public TetrisBoard() {
        matrix = new int[HEIGHT][WIDTH];
        for (int i = 0; i< HEIGHT; i++){
            for (int j = 0; j < WIDTH; j++){
                matrix[i][j] = 0;
            }//for
        }//for
    }//Board
    
    public int getBlock(int x, int y) {
        return matrix[y][x];
    }//Block

    /*public int getHeight(){
        return this.HEIGHT;
    }//Height

    public int getWidth(){
        return this.WIDTH;
    }//Width
    */
    
}
