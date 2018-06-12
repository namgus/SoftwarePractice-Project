package spaceship;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface InterfaceA {
	
	public void tick();
	public void render(Graphics g);
	public Rectangle getBounds();
	
	public double getX();
	public double getY();

}
