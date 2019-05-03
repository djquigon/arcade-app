package cs1302.arcade;

import cs1302.arcade.*;
import javafx.scene.Group;

/**
 * This class represents a group of aliens which attack the user ship.
 */
public class SpaceInvadersAlienGroup extends Group{

    public static final int ALIENS_WIDTH = 6;
    public static final int ALIENS_HEIGHT = 5;
    public static final int ALIENS_HORZ_SPACING = 50;
    public static final int ALIENS_VERT_SPACING = 50;
    
    private SpaceInvadersAlien[][] aliens; //track aliens

    /**
     * Creates a group of aliens which attack the user ship.
     *
     * @param stage reference to the main stage
     */
    public SpaceInvadersAlienGroup(SpaceInvadersStage stage){ //could make enum for different types of aliens
        aliens = new SpaceInvadersAlien[ALIENS_WIDTH][ALIENS_HEIGHT];
        for(int x = 0; x < ALIENS_WIDTH; x++){
            for(int y = 0; y < ALIENS_HEIGHT; y++){
                aliens[x][y] = new SpaceInvadersAlien();
                aliens[x][y].relocate(x * ALIENS_HORZ_SPACING, y * ALIENS_VERT_SPACING);
                this.getChildren().add(aliens[x][y]);
            }
        }
        stage.getMain().getChildren().add(this);
    }
}
