package spaceship;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	// get buffered image to show screen
	private BufferedImage image;
	
	public SpriteSheet(BufferedImage bufferimage) {
		this.image = bufferimage;
	}
	
	public BufferedImage getImage(int c, int r, int w, int h) {
		BufferedImage buffered_image = image.getSubimage((c * 32) - 32, (r * 32) - 32, w, h);
		return buffered_image;
	}
	public BufferedImage getBoss(int col, int row, int width, int height) {
		
		BufferedImage buffered_image2 = image.getSubimage(0,0, width, height);	
		return buffered_image2;
		
	}
}
