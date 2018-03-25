package game1;

import utilities.JEasyFrame;

import utilities.Vector2D;

import java.util.*;
/**
 * Created by ap16718.
 */
public class Game {
    // Used to decide how many asteroids are drawn
    public int asteroidToSpawnCount = 0;
    // Object list used to store all the game objects
    public List<GameObject> objects;
    // List to used to store asteroids.
    public List<Asteroid> astList;
    Keys ctrl;
    Ship ship;
    AI enemy;
    int score = 0;
    int scoreforExtraLife = 1000;
    int scorefoInv = 500;
    PowerUp bp;



    public Game() {
        objects = new ArrayList<>();
        astList = new ArrayList<>();
        ctrl = new Keys();
        // places the ship on the map
        ship = new Ship(new Vector2D(220, 320), new Vector2D(), 10, ctrl);

        // places the enemy... not fully fixed the enemy ship shoots too many bullets
        enemy = new AI(new Vector2D(200,200), new Vector2D(), 10, new RotateNShoot());
        objects.add(ship);
        ship.invincibility = true;
        ship.turnOffInvibility();

        //Enemy ship shoots too many bullets to dodge
        //objects.add(enemy);

    }
    // main method
    public static void main(String[] args) throws Exception {
        Game game = new Game();
        View view = new View(game);
        new JEasyFrame(view, "CE218 Assignment: Asteroid Game").addKeyListener(game.ctrl);
        while (true) {
            game.update();
            view.repaint();
            Thread.sleep(Constants.DELAY);
        }
    }
    // Adds score
    public void addScore(int s){

        score = score + s;
    }
    // Gets the score
    public int getScore(){
        return score;
    }
    // Upgrade method
    public void update() {
        checkGameState();
        // Check for collisions
        for (GameObject a : objects) {
            a.update();
           // System.out.println(a);
            for (GameObject aa : objects) {
                if(a == aa){
                    continue;
                }
                a.collisionHandling(aa);
            }
        }

        List<GameObject> Alive = new ArrayList<>();
        Alive.addAll(objects);
        if (ship.bullet != null){
                Alive.add(ship.bullet);
        }
        if (enemy.bullet != null) {
                Alive.add(enemy.bullet);
        }
        if(ship.lives <=6) {
            bp = spawnLifeUP();


            if (bp != null) {
                Alive.add(bp);
            }

        }
        if(ship.lives <= 10) {
            bp = spawnInvincibility();

        }

            if(bp != null){
                Alive.add(bp);
            }


        ship.bullet = null;
        // Continue updating the game
        for (GameObject a : objects) {
            a.update();

            // If a is a bullet
            if(a instanceof Bullet){
                // Check if the field to update the score is true
                if(((Bullet) a).giveScore == true){
                    addScore(100);
                    // System.out.println("Score added!");
                }
            }
            if(a instanceof PowerUp) {
               // System.out.println("what'sup");
            }
            if(a.change){
                a.change = false;
                if(a.radius>10) {
                    Alive.add(Asteroid.makeAsteroid(a));
                    Alive.add(Asteroid.makeAsteroid(a));
                    Alive.add(Asteroid.makeAsteroid(a));
                }
            }

            if (a.dead) {
                Alive.remove(a);
                enemy.count=0;
            }

        }
        // Add the alive objects to the object list
        // use synchronized blocks to keep thread safety
        synchronized (Game.class) {
            objects.clear();
            objects.addAll(Alive);

        }
        synchronized (Game.class){
            astList.clear();
            for(GameObject a:Alive){
                if(a instanceof Asteroid){
                    astList.add((Asteroid) a);
                }
            }
        }
    }

    // checks the game state
    private void checkGameState(){
        // If the asteroid list is empty
        if (astList.size() == 0){
            asteroidToSpawnCount ++;
            startNextLevel();
        }
    }


    // spawns the extra life power up
    private PowerUp spawnLifeUP(){
        LifeUpPowerUp lpu = new LifeUpPowerUp(new Vector2D(Math.random() * Constants.FRAME_WIDTH,0),new Vector2D(0,80),30,40,40,Sprite.LIFEUP,ship);

        if (score >= scoreforExtraLife)
        {
            scoreforExtraLife += 10000;
            return lpu;
        }
        return null;
    }


    // spawns the invincibility power up
    private PowerUp spawnInvincibility() {
        InvincibilityPowerUp ipu = new InvincibilityPowerUp(new Vector2D(Math.random() * Constants.FRAME_WIDTH,0),new Vector2D(0,80),30,40,40,Sprite.INV,ship);

        if(score >= scorefoInv) {
            scorefoInv += 15000;
            return ipu;
        }
        return null;
    }

    // starts the next level and spawns the asteroids.
    private void startNextLevel(){
        for(int i =0;i<asteroidToSpawnCount;i++){
            objects.add(Asteroid.makeRandomAsteroid());
        }
    }
}
