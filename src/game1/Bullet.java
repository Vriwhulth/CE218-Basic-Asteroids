package game1;

import utilities.Vector2D;

import java.awt.*;
import java.util.Date;

/**
 * Created by ap16718.
 */
public class Bullet extends GameObject {

    protected long created = 0;
    protected long death = 0;
    public boolean giveScore = false;


    public Bullet(Vector2D pos, Vector2D vel, double rad) {
        super(pos, vel, rad);
        // Get the time and use that as the bullets creation time
        created = System.currentTimeMillis();
        // Set its death time-stamp to be 1 seconds after the bullet has been created
        death = created + 1000;
    }


    public void update() {
        super.update();
        // If the current time is greater/equal to the death time of the bullet
        if (System.currentTimeMillis() >= death) {
            // Set the bullet's dead variable to true
            dead = true;
        }
        // If the bullet is set to "dead" and its death was smaller than the normal time to despawn
        // inform the game that a score must be added
        if(System.currentTimeMillis() < death && dead){
            giveScore = true;
        }
    }


    // draw method
    public void draw(Graphics2D g) {
        // draws the bullets as white dots.
        g.setColor(Color.white);
        g.fillOval((int) position.x-8  - (int) radius, (int) position.y-8 - (int) radius, 2* (int) radius, 2* (int) radius);
    }

    // overlap method
    public boolean overlap(GameObject other) {

         if(this instanceof Bullet && other instanceof Ship){
            return false;
        }
        if (this instanceof Bullet && other instanceof AI) {
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
