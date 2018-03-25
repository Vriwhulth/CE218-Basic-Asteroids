package game1;

import utilities.SoundManager;
import utilities.Vector2D;

import java.awt.*;

import static game1.Constants.DT;

/**
 * Created by ap16718.
 */
public abstract class PowerUp extends GameObject {

    Sprite im;
    Sprite pic;
    int width;
    int height;
    Ship player;
    long timeSpawned;
    long timeDespawn;
    public PowerUp(Vector2D pos, Vector2D vel, double rad, int width, int height, Image image,Ship ship){
        super(pos,vel,rad);
        this.width = width;
        this.height = height;
         im = new Sprite(image,this.position,this.velocity,this.width,this.height);
         pic = new Sprite(image,this.position,this.velocity,this.width,this.height);
        this.player = ship;
        timeSpawned = System.currentTimeMillis();
        timeDespawn = timeSpawned + 6000;
    }
    // draw method
    public void draw(Graphics2D g){
        im.draw(g);
        pic.draw(g);
    }

    // update method
    public void update(){
        if(position.y > Constants.FRAME_HEIGHT){
            dead = true;
        }
        position.addScaled(velocity, DT);
    }
    // overlap method
    public boolean overlap(GameObject other) {

        // if the power up is overlapped with the ship ignore the overlap
        if(this instanceof PowerUp && other instanceof Ship){
            return false;
        }
        // if the power up is overlapped with the ai ignore the overlap
        if(this instanceof PowerUp && other instanceof AI){
            return false;
        }
        // if the power up is overlapped with the asteroid ignore the overlap.
        if(this instanceof PowerUp && other instanceof Asteroid){
            return false;
        }
        if (this.position.x + this.radius + other.radius > other.position.x &&
                this.position.x < other.position.x + this.radius + other.radius &&
                this.position.y + this.radius + other.radius > other.position.y &&
                this.position.y < other.position.y + this.radius + other.radius) {
            return true;
        }
        return false;

    }

}
