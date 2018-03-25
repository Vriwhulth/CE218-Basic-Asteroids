package game1;

import utilities.SoundManager;
import utilities.Vector2D;

import java.awt.*;

/**
 * Created by ap16718.
 */

// life up class.

public class LifeUpPowerUp extends PowerUp {

    public LifeUpPowerUp(Vector2D pos, Vector2D vel, double rad, int width, int height, Image image, Ship ship){
        super(pos,vel,rad,width,height,image,ship);
    }

    // if the player hits the power up with the bullet a life is added.
    public void hit(){
        if(player!=null){
            player.lives +=1;
        }
        this.dead = true;
        SoundManager.powerUp();
    }
}
