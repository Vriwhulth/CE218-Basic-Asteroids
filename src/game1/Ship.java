package game1;

import utilities.SoundManager;
import utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Timer;
import java.util.TimerTask;


import static game1.Constants.DT;

// the ship class which the player controlls.
/**
 * Created by ap16718.
 */
public class Ship extends GameObject {
    // Variables used by the ship class
    public static final double STEER_RATE = 2 * (180 / Math.PI) * DT;
    public static final double MAG_ACC = 180;
    public static final double MAG_BUL = 250;
    public static final double DRAG = 0.01;
    public static final Color COLOR = Color.WHITE;




    public int lives = 3;
    // Vector to figure out the direction
    public Vector2D direction;
    // controller to give the ship controls
    private Controller ctrl;


    boolean invincibility = false;
    // Bullet variable, initialised when the ship fires
    public Bullet bullet = null;
    // Values which are used to plot out the polygon of the spaceship
    private int[] playerX = new int[]{0,25,0,-25};
    private int[] playerY = new int[]{-45,25,15,25};

    // Values which are used to plot out the polygon of the thrust.
    private int[] thrustX = new int[]{-20,0,20};
    private int[] thrustY = new int[]{70,150, 70};
    // Sets the scale size of the ship
    private double DRAWING_SCALE = 0.5;

    // public constructor that calls the parent's super-constructor
    public Ship(Vector2D pos, Vector2D vel, double rad, Controller ctrl) {
        super(pos, vel, rad);
        this.ctrl = ctrl;
        this.direction = new Vector2D(1, 0);
    }

    // update method
    public void update() {
        super.update();
        //System.out.println("position: " + position.x + ", " + position.y + " direction: " + direction.x + ", " + direction.y + " velocity: " + velocity.x + ", " + velocity.y + ", add?: "+ (MAG_ACC*DT) * ctrl.action().thrust);

        direction.rotate((STEER_RATE * ctrl.action().turn) * DT);
        velocity.addScaled(direction, (MAG_ACC * DT) * ctrl.action().thrust);
        velocity.mult(1 - DRAG);

        if(ctrl.action().thrust==1)SoundManager.startThrust();
        else SoundManager.stopThrust();

        if (ctrl.action().shoot) {

                this.mkBullet();

                ctrl.action().shoot = false;

            }
        }


    // draw method

    public void draw(Graphics2D g) {
        AffineTransform at = g.getTransform();

        g.translate(position.x, position.y);
        double rot = direction.angle() + Math.PI / 2;
        g.rotate(rot);
        g.scale(DRAWING_SCALE, DRAWING_SCALE);
        g.setColor(COLOR);
        g.setStroke(new BasicStroke(4));
        g.drawPolygon(playerX, playerY, playerX.length);
        if(invincibility == true) {
            g.setColor(Color.GREEN);
            g.setStroke(new BasicStroke(4));
            g.fillPolygon(playerX, playerY, playerX.length);
        }
        if (ctrl.action().thrust == 1) {
            g.scale(0.4, 0.4);
            g.setColor(Color.red);
            g.fillPolygon(thrustX, thrustY, 3);
        }
        g.setTransform(at);
    }


    // a method that makes the bullet
    private void mkBullet() {
           bullet = new Bullet(new Vector2D(position), new Vector2D(velocity), 4);
           // In theory, the bullet should be placed at the ships position + what ever its radius
           bullet.position.add(radius, radius);
           bullet.velocity.addScaled(direction, (MAG_BUL));
          // plays the fire sound
           SoundManager.fire();
    }

    // method that controls what happens when there is a hit
    public void hit(){

        if(lives > 0){
            lives --;
            invincibility = true;
            turnOffInvibility();
            position = new Vector2D(400,400);
            velocity = new Vector2D();
            SoundManager.shipDead();
        }
        if(lives <=0){
            dead = true;
            SoundManager.shipDead();
            return;
        }



    }

    public int getLives(){
        return lives;
    }


    public boolean overlap(GameObject other) {


        // if invincibility is on than the collision between ship and asteroids is ignored.
        if (invincibility == true) {
            if(this instanceof Ship && other instanceof Asteroid){
                return false;
            }
        }


        // the collision between ship and the power up is ignored
        if(this instanceof Ship && other instanceof PowerUp){
            return false;
        }
        // the collision between the ship and the bullet is ignored.

        if(this instanceof Ship && other instanceof Bullet){
            return false;
        }


        /*
           For anything else normal collision applies.
        */
        if (this.position.x + this.radius + other.radius > other.position.x &&
                this.position.x < other.position.x + this.radius + other.radius &&
                this.position.y + this.radius + other.radius > other.position.y &&
                this.position.y < other.position.y + this.radius + other.radius) {
            return true;
        }
        return false;
    }

    // turn off invincibility that turns the invincibility after 2.5 seconds.
    public void turnOffInvibility() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                invincibility = false;
                timer.cancel();
            }
        }, 2500, 2500);
    }
}
