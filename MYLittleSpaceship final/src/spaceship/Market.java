package spaceship;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class Market {
   private int upgradeAnglebullet = 0;
   private int upgradeStraightbullet = 0;
   
   private int x = 210;
   private int y = 180;   
   
   
   public Rectangle playButton = new Rectangle(735, 405,100,50) ;

   private BufferedImage moveview = null;
   
   public void render(Graphics g, BufferedImage moveview) {
      Graphics2D g2d = (Graphics2D) g;

      Font fnt1 = new Font("arial", Font.BOLD,30);
      g.setFont(fnt1);
      g.setColor(Color.YELLOW);
      g.drawString("Market", Game.WIDTH-30, 50);
      Font fnt0 = new Font("arial", Font.BOLD, 40);
      g.setFont(fnt0);
      g.setColor(Color.white);
      g.drawString(String.valueOf(Game.MONEY), 10, 40);
     
      if (upgradeAnglebullet == 1) {
         g.drawImage(moveview, 210, 175,null);
      }
      else if (upgradeAnglebullet == 2) {
         g.drawImage(moveview, 210, 175,null);
         g.drawImage(moveview, 385, 175,null);
      }
      else if (upgradeAnglebullet == 3) {
         g.drawImage(moveview, 210, 175,null);
         g.drawImage(moveview, 385, 175,null);
         g.drawImage(moveview, 560, 175,null);
      }
      
      if (upgradeStraightbullet == 1) {
         g.drawImage(moveview, 210, 255,null);
      }
      else if (upgradeStraightbullet == 2) {
         g.drawImage(moveview, 210, 255,null);
         g.drawImage(moveview, 385, 255,null);
      }

      if (Player.minushealth == 8) {
         g.drawImage(moveview, 210, 330,null);
      }
      else if (Player.minushealth == 6) {
         g.drawImage(moveview, 210, 330,null);
         g.drawImage(moveview, 385, 330,null);
      }
      else if (Player.minushealth == 5) {
         g.drawImage(moveview, 210, 330,null);
         g.drawImage(moveview, 385, 330,null);
         g.drawImage(moveview, 560, 330,null);
      }
      else if (Player.minushealth == 4) {
         g.drawImage(moveview, 210, 330,null);
         g.drawImage(moveview, 385, 330,null);
         g.drawImage(moveview, 560, 330,null);
         g.drawImage(moveview, 735, 330,null);
      }
      
      if (Game.movesize == 7) {
         g.drawImage(moveview, 210, 410,null);
      }
      else if (Game.movesize == 10) {
         g.drawImage(moveview, 210, 410,null);
         g.drawImage(moveview, 385, 410,null);
      }
      else if (Game.movesize == 13) {
         g.drawImage(moveview, 210, 410,null);
         g.drawImage(moveview, 385, 410,null);
         g.drawImage(moveview, 560, 410,null);
      }
      else if (Game.movesize == 15) {
         g.drawImage(moveview, 210, 410,null);
         g.drawImage(moveview, 385, 410,null);
         g.drawImage(moveview, 560, 410,null);
         g.drawImage(moveview, 735, 410,null);
      }
   }
   
   public void setUpgradeAngleBullet(int upgradeAnglebullet) {
      this.upgradeAnglebullet = upgradeAnglebullet;
   }
   public int getUpgradeAngleBullet() {
      return upgradeAnglebullet;
   }
   public void setStraightbullet(int upgradeStraightbullet) {
      this.upgradeStraightbullet = upgradeStraightbullet;
   }
   public int getStraightbullet() {
      return upgradeStraightbullet;
   }
   public int getMarketX() {
      return x;
   }
   public int getMarketY() {
      return y;
   }
   public void setMarketX(int x) {
      this.x = x;
   }
   public void setMarketY(int y) {
      this.y = y;
   }
}