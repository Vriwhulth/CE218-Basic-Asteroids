package game1;

import utilities.ImageManager;

import javax.tools.Tool;
import java.awt.*;
import java.io.IOException;

/**
 * Created by ap16718
 */


// constrants method
public class Constants {
    public static final int FRAME_HEIGHT = 720;
    public static final int FRAME_WIDTH = 720;
    public static final Dimension FRAME_SIZE = new Dimension(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
    public static final int DELAY = 20;
    public static final double DT = DELAY / 1000.0; // in seconds

    public static Image ASTEROID1, MILKYWAY1;
    static {
        try {
            ASTEROID1 = ImageManager.loadImage("asteroid1");
            MILKYWAY1 = ImageManager.loadImage("milkyway2");
        } catch (IOException e) { e.printStackTrace(); }
    }
}
