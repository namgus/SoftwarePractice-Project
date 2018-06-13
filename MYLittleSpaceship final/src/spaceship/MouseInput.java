package spaceship;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;

import spaceship.Game.STATE;

public class MouseInput extends MouseAdapter implements MouseMotionListener {

   int x = 0;
   int y = 0;
   
   Point pClicked = new Point(0, 0);
   Point pMoved = new Point(0, 0);
   Game game;

   private Wallet w;
   private Graphics g;

   
   public MouseInput(Game game,Wallet w) {
         
      this.game = game;
      this.w = w;
      game.addMouseMotionListener(this);
      
   }

   public int getX() {
      return x;
   }

   public int getY() {
      return y;
   }

   @Override
   public void mouseMoved(MouseEvent e) {
      pMoved = e.getPoint();
      x = e.getX();
      y = e.getY();
      
      if(Game.state == Game.STATE.MARKET) {
         if (x >= 210 && x <= 310) {
            if (y >= 180 && y <= 230) {            
               Game.market.setMarketX(210);
               Game.market.setMarketY(175);
            }
            if (y >= 255 && y <= 305) {            
               Game.market.setMarketX(210);
               Game.market.setMarketY(255);
            }
            if (y >= 330 && y <= 380) {            
               Game.market.setMarketX(210);
               Game.market.setMarketY(330);
            }
            if (y >= 410 && y <= 460) {            
               Game.market.setMarketX(210);
               Game.market.setMarketY(410);
            }
         }
         if (x >= 385 && x <= 485) {
            if (y >= 180 && y <= 230) {
               Game.market.setMarketX(385);
               Game.market.setMarketY(175);
            }
            if (y >= 255 && y <= 305) {            
               Game.market.setMarketX(385);
               Game.market.setMarketY(255);
            }
            if (y >= 330 && y <= 380) {            
               Game.market.setMarketX(385);
               Game.market.setMarketY(330);
            }
            if (y >= 410 && y <= 460) {            
               Game.market.setMarketX(385);
               Game.market.setMarketY(410);
            }
         }
         if (x >= 560 && x <= 660) {
            if (y >= 180 && y <= 230) {
               Game.market.setMarketX(560);
               Game.market.setMarketY(175);
            }

            if (y >= 330 && y <= 380) {            
               Game.market.setMarketX(560);
               Game.market.setMarketY(330);
            }
            if (y >= 410 && y <= 460) {            
               Game.market.setMarketX(560);
               Game.market.setMarketY(410);
            }
         }
         if (x >= 735 && x <= 835) {

            if (y >= 330 && y <= 380) {            
               Game.market.setMarketX(735);
               Game.market.setMarketY(330);
            }
            if (y >= 410 && y <= 460) {            
               Game.market.setMarketX(735);
               Game.market.setMarketY(410);
            }
         }
      }
       
   }
   
   @Override
   public void mouseClicked(MouseEvent arg0) {
      // TODO Auto-generated method stub

   }

   @Override
   public void mouseEntered(MouseEvent arg0) {
      // TODO Auto-generated method stub

   }

   @Override
   public void mouseExited(MouseEvent arg0) {
      // TODO Auto-generated method stub

   }

   
   @Override
   public void mousePressed(MouseEvent e) {
      
      int mx = e.getX();
      int my = e.getY();
      if (Game.state == Game.STATE.MENU) {
         if (mx >= Game.WIDTH / 2 -50 && mx <= Game.WIDTH / 2 + 50) {
            if (my >= 500 && my <= 550) {
               // pressed play button
               Game.state = Game.STATE.GAME;               
            }
         }
         if (mx >= Game.WIDTH / 2 + 170 && mx <= Game.WIDTH / 2 + 270) {
        	    if (my >= 500 && my <= 550) {
                    // pressed help button
                  JOptionPane.showMessageDialog(null, "up key - up\ndown key - down\nleft key - left\nright key - right\nspace - shoot!\n\n나만의 작고 귀여운 우주선을\r\n" + 
                        " 외계인들을 사냥하여 강하게 만들자", null, JOptionPane.QUESTION_MESSAGE);
                  
                 }
          }
         if (mx >= Game.WIDTH / 2 + 380 && mx <= Game.WIDTH / 2 + 480) {
            if (my >= 500 && my <= 550) {
               // pressed quit button
               System.exit(1);
            }
         }
         
      }

      
      if(Game.state == Game.STATE.MARKET) {
         if (mx >= Game.WIDTH / 2 - 180 && mx <= Game.WIDTH / 2 - 80) {
            if (my >= 500 && my <= 550) {
               //set
               Game.state = Game.STATE.GAME;
            }
         }
         if (mx >= 210 && mx <= 310) {
            if (my >= 180 && my <= 230 && Game.market.getUpgradeAngleBullet() == 0) {      
               if (w.buy(100, 70)) {
                  Game.market.setUpgradeAngleBullet(1);
               }
            }
            if (my >= 255 && my <= 305 && Game.market.getStraightbullet() == 0) {
               if (w.buy(100, 70)) {
                  Game.market.setStraightbullet(1);
               }
            }
            if (my >= 330 && my <= 380 && Player.getMinushealth() == 10) {
               if (w.buy(100, 70)) {
                  Player.setMinushealth(8);
               }
            }
            if (my >= 410 && my <= 460 && Game.movesize == 5) {   
               if (w.buy(100, 70)) {
                  Game.movesize = 7;
               }
            }
         }
         if (mx >= 385 && mx <= 485) {
            if (my >= 180 && my <= 230 && Game.market.getUpgradeAngleBullet() == 1) {
               if (w.buy(100, 50)) {
                  Game.market.setUpgradeAngleBullet(2);
               }
            }
            if (my >= 255 && my <= 305 && Game.market.getStraightbullet() == 1) {
               if (w.buy(100, 50)) {
                  Game.market.setStraightbullet(2);
               }
            }
            if (my >= 330 && my <= 380 && Player.getMinushealth() == 8) {   
               if (w.buy(100, 50)) {
                  Player.setMinushealth(6);
               }
            }
            if (my >= 410 && my <= 460 && Game.movesize == 7) {   
               if (w.buy(100, 50)) {
                  Game.movesize = 10;
               }
            }
         }
         
         if (mx >= 560 && mx <= 660) {
            if (my >= 180 && my <= 230 && Game.market.getUpgradeAngleBullet() == 2) {
               if (w.buy(100, 30)) {
                  Game.market.setUpgradeAngleBullet(3);
               }
            }
            if (my >= 330 && my <= 380 && Player.getMinushealth() == 6) {   
               if (w.buy(100, 30)) {
                  Player.setMinushealth(5);
               }
            }
            if (my >= 410 && my <= 460 && Game.movesize == 10) {
               if (w.buy(100, 30)) {
                  Game.movesize = 13;
               }
            }
         }
         if (mx >= 735 && mx <= 835) {
            if (my >= 330 && my <= 380 && Player.getMinushealth() == 5) {
               if (w.buy(100, 10)) {
                  Player.setMinushealth(4);
               }
            }
            if (my >= 410 && my <= 460 && Game.movesize == 13) {
               if (w.buy(100, 10)) {
                  Game.movesize = 15;
               }
            }
         }
      }
      
   }

   @Override
   public void mouseReleased(MouseEvent arg0) {
      // TODO Auto-generated method stub
      
   }

}