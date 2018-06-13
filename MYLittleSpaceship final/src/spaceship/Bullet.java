package spaceship;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject implements InterfaceA {
	private Texture tex;
	private Game g;
	double angle;

	public Bullet(double x, double y, double angle, Texture tex, Game game) {
		super(x,y);
		this.angle = angle;
		this.tex = tex;		
		this.g = game;
	}
	
	public void tick() {
		x -= Math.cos(Math.toRadians(angle))*10;
		y -= Math.sin(Math.toRadians(angle))*10;
	}
	
	public void render(Graphics g) {		
		g.drawImage(tex.missile, (int) x,(int) y, null);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,32,32);
	}
	
	public double getY() {
		return y;
	}
	
	public double getX() {
		return x;
	}
}
