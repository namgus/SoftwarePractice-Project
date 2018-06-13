package spaceship;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	public Rectangle playButton = new Rectangle(Game.WIDTH/2-50, 500,100,50) ;
	public Rectangle helpButton = new Rectangle(Game.WIDTH/2 + 170, 500,100,50) ;
	public Rectangle quitButton = new Rectangle(Game.WIDTH/2 +380, 500,100,50) ;
	
	
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		Font font1 = new Font("Opificio", Font.BOLD, 50);
		g.setFont(font1);
		g.setColor(Color.white);
		g.drawString("MY LITTLE SPACESHIP", Game.WIDTH/2 - 20 , 240);
		
		Font font2 = new Font("Opificio", Font.PLAIN, 30);
		g.setFont(font2);
		g.drawString("Play", playButton.x + 19,playButton.y + 30);
		g2d.draw(playButton);
		g.drawString("Help", helpButton.x + 19,helpButton.y + 30);
		g2d.draw(helpButton);
		g.drawString("Quit", quitButton.x + 19,quitButton.y + 30);
		g2d.draw(quitButton);
	}

}
