package spaceship;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JOptionPane;

public class Wallet {
	
	Game game;
	Random r = new Random();
	
	Wallet(Game game){		
		this.game = game;
		
	}
	
		
	String Show_money() {
		String string_money = String.valueOf(Game.MONEY);
		return string_money;
	}
	
	public boolean buy(int price,int probability) {
		if(price > game.MONEY) {
			JOptionPane.showMessageDialog(null, "NOT ENOUGH MONEY");
			System.out.println("no doolr");
			return false;
		} else {
			if(r.nextInt(99)> 99-probability) {				
				JOptionPane.showMessageDialog(null, "UPGRADE COMPLETE");
				System.out.println("up");
				game.MONEY -= price;
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "UPGRADE FAIL");
				System.out.println("dwn");
				game.MONEY -= price;
				return false;
			}
					
		}			
		
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("arial", Font.BOLD, 40);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString(Show_money(), 10, 40);
				
	}



}
