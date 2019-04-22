package cs1302.arcade;

import javafx.scene.paint.Color;

public class TetrisBlocks{
    private Color[] color = {
        Color.RED, Color.YELLOW, Color.MAGENTA, Color.PINK,
        Color.CYAN, Color.GREEN, Color.ORANGE
    };//Color Array
    
    private int[][][] pieces = {
        //If works, go back create methods and add rotations
        //I-Shape
        {
            {0,0,0,0},
            {1,1,1,1},
            {0,0,0,0},
            {0,0,0,0},
        },
        //J-Shape
        {
            {1,0,0},
            {1,1,1},
            {0,0,0},
        },
        //L-Shape
        {
            {0,0,1},
            {1,1,1},
            {0,0,0},
        },
        //Sq-Shape
        {
            {0,0,0,0},
            {0,1,1,0},
            {0,1,1,0},
            {0,0,0,0},
        },
        //S-Shape
        {
            {0,1,1},
            {1,1,0},
            {0,0,0},
        },
        //T-Shape
        {
            {0,1,0},
            {1,1,1},
            {0,0,0},
        },
        //Z-Shape
        {
            {1,1,0},
            {0,1,1},
            {0,0,0},
        }
    };//Shape Array
            

    
}
