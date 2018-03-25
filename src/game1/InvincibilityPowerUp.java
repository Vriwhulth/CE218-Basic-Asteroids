package game1;

import utilities.SoundManager;
import utilities.Vector2D;

import java.awt.*;

/**
 * Created by ap16718.
 */


// InvincibilityPowerUp class
public class InvincibilityPowerUp extends PowerUp {
    public InvincibilityPowerUp(Vector2D pos, Vector2D vel, double rad, int width, int height, Image image, Ship ship){
        super(pos,vel,rad,width,height,image,ship);
    }


    // if the InvincibilityPowerUp is hit than the player invincibility power up is set to true.
    public void hit(){
        if(player!=null){
            player.invincibility = true;
            player.turnOffInvibility();
        }
        this.dead = true;
        SoundManager.powerUp();
    }
}
