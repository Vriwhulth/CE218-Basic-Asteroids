package game1;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created by ap16718.
 */
public class View extends JComponent {

    public static final Color BG_COLOR = Color.black;

    private Game game;
    Image im = Constants.MILKYWAY1;
    AffineTransform bgTransf;
    public View(Game game) {

        this.game = game;
        double imWidth = im.getWidth(null);
        double imHeight = im.getHeight(null);
        double stretchx = (imWidth > Constants.FRAME_WIDTH? 1 :
                Constants.FRAME_WIDTH/imWidth);
        double stretchy = (imHeight > Constants.FRAME_HEIGHT? 1 :
                Constants.FRAME_HEIGHT/imHeight);
        bgTransf = new AffineTransform();
        bgTransf.scale(stretchx, stretchy);
    }
    // paintComponent method
    public void paintComponent(Graphics g0) {
        // create a new graphics2D object by casting g0
        Graphics2D g = (Graphics2D) g0;

        g.setColor(BG_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(im,bgTransf,null);

        synchronized (Game.class){
        for (GameObject a : game.objects) {

            a.draw(g);
        }}

        g.setFont(new Font("Century Gothic", Font.BOLD, 16));
        g.setColor(Color.red);
        g.drawString("Score: " + Integer.toString(game.getScore()),40,40);
        g.drawString("Lives: " + Integer.toString(game.ship.getLives()),40,70);
        if(game.ship.getLives() <= 0){

            g.setFont(new Font("Century Gothic", Font.BOLD, 30));
            g.setColor(Color.red);
            g.drawString("You Are Dead!",260,200);
            g.setColor(Color.white);
            g.drawString("Your Score was!: " +game.getScore(),260,240);
            g.drawString("Well Done! ",260,300);
            g.setFont(new Font("Century Gothic", Font.BOLD, 45));
            g.drawString("Thanks For Playing! ",200,360);

        }
    }

    // method that gets the preffered size.
    public Dimension getPreferredSize() {
        return Constants.FRAME_SIZE;
    }
}
