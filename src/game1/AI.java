package game1;

import utilities.SoundManager;
import utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;



import static game1.Constants.DT;


/**
 * Created by ap16718.
 */


/**
 * AI not fully implemented.. it shoots the bullets too quickly to be added into the game.
 */


public class AI extends GameObject {
    // Variables used by the ship class
    public static final double STEER_RATE = 2 * (180 / Math.PI) * DT;
    public static final double MAG_BUL = 250;
    public static final double DRAG = 0.01;



    // vector to calculate the direction of the ship
    public Vector2D direction;
    // controls the shooting mechanic of the enemy ship
    private RotateNShoot ctrl;
    int count = 0;

    // Bullet variable, initialised when the ship fires
    public Bullet bullet = null;


    // Variables used to map the polygon points so the ship can be drawn
    private int[] XP = new int[]{0,25,0,-25};
    private int[] YP = new int[]{-45,25,15,25};

    // Sets the scale size of the ship
    private double DRAWING_SCALE = 0.5;

    // public constructor that calls the parent's super-constructor
    public AI(Vector2D pos, Vector2D vel, double rad, RotateNShoot ctrl) {
        super(pos, vel, rad);
        this.ctrl = ctrl;
        this.direction = new Vector2D(1, 0);
    }

    // update method
    public void update() {
        super.update();
        direction.rotate((STEER_RATE * ctrl.action().turn) * DT);
        velocity.mult(1 - DRAG);
            this.mkBullet();
        }


    // draw method
    public void draw(Graphics2D g) {
        AffineTransform at = g.getTransform();

        g.translate(position.x, position.y);
        double rot = direction.angle() + Math.PI / 2;
        g.rotate(rot);
        g.scale(DRAWING_SCALE, DRAWING_SCALE);
        g.setColor(Color.RED);
        g.setStroke(new BasicStroke(4));
        g.fillPolygon(XP, YP, XP.length);
        g.setTransform(at);
    }

    // make bullet method
    private void mkBullet() {
        bullet = new Bullet(new Vector2D(position), new Vector2D(velocity), 4);
        // In theory, the bullet should be placed at the ships position + what ever its radius
        bullet.position.add(radius, radius);
        bullet.velocity.addScaled(direction, (MAG_BUL));
        SoundManager.fire();
    }



    // overlap method
    public boolean overlap(GameObject other) {
            if(this instanceof AI && other instanceof Asteroid){
                return false;
            }

            if(this instanceof AI && other instanceof PowerUp) {
                return false;
            }

            if(this instanceof AI && other instanceof Bullet) {
                return false;
            }
        /*
            Else assume a normal collision is happening -:
        */
        if (this.position.x + this.radius + other.radius > other.position.x &&
                this.position.x < other.position.x + this.radius + other.radius &&
                this.position.y + this.radius + other.radius > other.position.y &&
                this.position.y < other.position.y + this.radius + other.radius) {
            return true;
        }
        return false;
    }

}
