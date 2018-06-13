package spaceship;

import java.awt.image.BufferedImage;

public class Texture {
	
	public BufferedImage player, missile, enemy, Boss;
	
	private SpriteSheet ss;
	private SpriteSheet bb;

	
	public Texture(Game game) {
		 ss = new SpriteSheet(game.getSpriteSheet());
		 bb = new SpriteSheet(game.getBoss());
		getTextures();
	}
	
	
	private void getTextures() {
		player = ss.getImage(1, 1, 32, 32);
		missile = ss.getImage(3, 1, 32, 32);
		enemy = ss.getImage(2, 1, 32, 32);
		Boss = bb.getBoss(0, 0, 600, 400);
	}

}
