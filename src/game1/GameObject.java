package game1;

import utilities.Vector2D;

import java.awt.*;

import static game1.Constants.DT;
import static game1.Constants.FRAME_WIDTH;
import static game1.Constants.FRAME_HEIGHT;

/**
 * Created by ap16718.
 */
public abstract class GameObject {
    public Vector2D position;
    public Vector2D velocity;
    public boolean dead;
    public double radius;
    boolean change = false;

    public GameObject(Vector2D pos, Vector2D vel, double rad) {
        this.position = pos;
        this.velocity = vel;
        this.radius = rad;
        dead = false;
    }
    // update method
    public void update() {
        position.addScaled(velocity, DT);
        position.wrap(FRAME_WIDTH, FRAME_HEIGHT);
    }
    // draw method
    public abstract void draw(Graphics2D g);
    // hit method
    public void hit() {
        dead = true;
    }
    // overlap method
    public boolean overlap(GameObject other) {

        if (this.position.x + this.radius + other.radius > other.position.x &&
                this.position.x < other.position.x + this.radius + other.radius &&
                this.position.y + this.radius + other.radius > other.position.y &&
                this.position.y < other.position.y + this.radius + other.radius) {
            return true;
        }
        return false;
    }
    // collisionHandling method that checks if the collision is happening.
    public void collisionHandling(GameObject other) {

            if (this.getClass() != other.getClass() && this.overlap(other)) {
            this.hit();
            other.hit();
        }

    }
}
