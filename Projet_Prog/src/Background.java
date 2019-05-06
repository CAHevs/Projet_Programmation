import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Background {

    private BufferedImage bg;
    private ImageIcon imagebg;


    public Background() throws IOException {
        bg = ImageIO.read(new File("background.jpg"));
        imagebg = new ImageIcon(bg);
    }
    public ImageIcon getBackground(){
        return imagebg;
    }
}
