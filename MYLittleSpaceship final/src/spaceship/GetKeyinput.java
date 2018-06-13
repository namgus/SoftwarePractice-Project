package spaceship;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GetKeyinput extends KeyAdapter {
	Game g;
	public GetKeyinput(Game game) {
		this.g = game;
	}
	
	public void keyPressed(KeyEvent e) {
		g.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e) {
		g.keyReleased(e);
	}
}
