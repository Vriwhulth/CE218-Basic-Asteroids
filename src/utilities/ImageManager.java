package utilities;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 * Created by ap16718.
 */



public class ImageManager {

    // this may need modifying
    public final static String path = "images/";
    public final static String ext = ".png";

    public static Map<String, Image> images = new HashMap<String, Image>();




    // load the image method
    public static Image loadImage(String fname) throws IOException {
        BufferedImage img;
        img = ImageIO.read(new File(path + fname + ext));
        images.put(fname, img);
        return img;
    }



    }


