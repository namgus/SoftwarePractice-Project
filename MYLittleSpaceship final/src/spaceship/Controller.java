package spaceship;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class Controller {
	
	private LinkedList<InterfaceA> ea = new LinkedList<InterfaceA>();
	private LinkedList<InterfaceB> eb = new LinkedList<InterfaceB>();
	
	Wallet w;
	Texture tex;
	InterfaceA enta;
	InterfaceB entb;
	Random r = new Random();
	private Game game;
	
	
	public Controller(Texture tex,Game game) {		
		this.tex = tex;		
		this.game = game;
	}
	
	public void createEnemy(int enemy_count) {
		for(int i = 0; i<enemy_count; i++) addEntity(new Enemy(r.nextInt(640),-10,tex,this,game,w));
	}
	public void createBoss() {
		addEntity(new Boss(150,-400,tex,this,game,w));
	}
	
	public void tick() {	
		//A class
		for(int i = 0; i< ea.size();i++) {
			enta = ea.get(i);
			
			enta.tick();
		}
		
		//B class
		for(int i = 0; i< eb.size();i++) {
			entb = eb.get(i);
			
			entb.tick();
		}
	}
	
	public void render(Graphics g) {
		
		for(int i = 0; i< ea.size();i++) {
			enta = ea.get(i);
			
			enta.render(g);
		}

		for(int i=0; i< eb.size();i++) {
			entb = eb.get(i);
			
			entb.render(g);
		}
	}
	
	public void addEntity(InterfaceA block) {
		ea.add(block);
	}
	public void removeEntity(InterfaceA block) {
		ea.remove(block);
	}
	public void addEntity(InterfaceB block) {
		eb.add(block);
	}
	public void removeEntity(InterfaceB block) {
		eb.remove(block);
	}
	public LinkedList<InterfaceA> getEntityA(){
		return ea;
	}
	public LinkedList<InterfaceB> getEntityB(){
		return eb;
	}

}
