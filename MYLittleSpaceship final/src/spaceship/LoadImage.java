package spaceship;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LoadImage {
	// load image from the source
	private BufferedImage loadedimage;
	
	public BufferedImage ImageLoading(String sourcepath) throws IOException {
		loadedimage = ImageIO.read(getClass().getResource(sourcepath));
		return loadedimage;
	}
}
