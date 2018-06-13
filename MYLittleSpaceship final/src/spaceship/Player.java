package spaceship;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject implements InterfaceA {
	
	private double velX = 0;
	private double velY = 0;
	
	private Texture tex;
	
	static int minushealth = 10;
	
	Game game;
	Controller controller;
	
	public Player(double x, double y, Texture tex,Game game,Controller controller) {
		super(x, y);		
		this.tex = tex;
		this.game = game;
		this.controller = controller;
	}
	
	public void tick() {
		// move player
		x+=velX;
		y+=velY;	
		
		// collision bound
		if(x<=0)x=0;
		if(x>=960-32) x=960-32;
		if(y<=0)y=0;
		if(y>=720-32)y=720-32;
		
		for(int i=0; i<game.eb.size();i++)
		{
			InterfaceB tempEnt = game.eb.get(i);
			
			if(Physics.Collision(this, tempEnt) && !Game.boss_stage)
			{
				controller.removeEntity(tempEnt);
				Game.HEALTH -= minushealth * 10;
				game.setEnemy_killed(game.getEnemy_killed() + 1);
				if(Game.HEALTH <= 0) {
					System.out.println("heleee");
					for( i=0; i<game.eb.size();i++)
					{
					tempEnt = game.eb.get(i);
					controller.removeEntity(tempEnt);
					}
				}
			}
			
			if(Physics.Collision(this, tempEnt) && Game.boss_stage)
			{
				Game.HEALTH = 0;
				controller.removeEntity(tempEnt);
				
			}
			
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(tex.player,(int)x,(int)y,null);
	}
	
	public double getX() {
		return x;		
	}
	public double getY() {
		return y;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void setVelX(double velX) {
		this.velX = velX;
	}
	public void setVelY(double velY) {
		this.velY = velY;
	}
	public static double getMinushealth() {
		return minushealth;		
	}
	public static void setMinushealth(int health) {
		minushealth = health;
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,32,32);
	}
	
}
