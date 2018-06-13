package spaceship;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionVID = 1L;
	public static final int WIDTH = 480;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public final String GAMENAME ="우주선키우기";

	private boolean run = false;
	private Thread t;
	
	private BufferedImage buffer_image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); 
	private BufferedImage spriteSheet = null;
	private BufferedImage background = null;
	private BufferedImage upgradeview = null;
	private BufferedImage boss = null;
	
	private BufferedImage moveview = null;
	
	private boolean is_shooting = false;
	public static boolean stage_clear = false;
	public static boolean boss_stage = false;
	
	private int enemy_count = 4;
	private int enemy_killed = 0;
	
	public LinkedList<InterfaceA> ea;
	public LinkedList<InterfaceB> eb;
	
	public static int HEALTH = 100 * 2;
	public static int MONEY = 0;
	public static int movesize = 5;
	public static int LEVEL = 1 ;
	public static int Boss_HP = 10;
	public static int HIT = 0;
	
	public static enum STATE{
		MENU,
		GAME,
		MARKET
	};
	
	public static STATE state = STATE.MENU;
	
	public int getEnemy_count() {
		return enemy_count;
	}

	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}

	public int getEnemy_killed() {
		return enemy_killed;
	}

	public void setEnemy_killed(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}
	
	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}
	public BufferedImage getBoss() {
		return boss;
	}

	private Player p;
	private Controller c;
	private Texture tex;
	private Menu menu;
	private Wallet w;
	
	public static Market market;
	
	Random r =new Random();
	
	public void init() {
		requestFocus();
		
		LoadImage load = new LoadImage();  
		try {
			spriteSheet = load.ImageLoading("/sprite_sheet.png");
			background = load.ImageLoading("/background.png");
			upgradeview = load.ImageLoading("/upgrade.png");
			moveview = load.ImageLoading("/movedimg.png");
			boss = load.ImageLoading("/Boss.png");
		}catch(IOException e) {
			e.printStackTrace();
		}
			
		tex = new Texture(this);		
		c = new Controller(tex, this);	
		p = new Player(200, 200, tex, this, c);
		menu = new Menu();
		market = new Market();
		w = new Wallet(this);
		this.addKeyListener(new GetKeyinput(this));	
		this.addMouseListener(new MouseInput(this,w));
		
		c.createEnemy(enemy_count);
		
		ea = c.getEntityA();
		eb = c.getEntityB();
		
		
	}
	
	private synchronized void start() {
		if(run)
			return; // finish this method
		
		// start the game
		run = true;
		t = new Thread(this);
		t.start();
		
	}
	
	private synchronized void stop() {
		if(!run)
			return; // finish this method
		
		// stop the game
		run = false;
		
		try {
			t.join();
		}catch (InterruptedException e) {
			e.printStackTrace();			
		}
		
		System.exit(1);
	}
	
	
	public void run() {
		init();
		long initialT = System.nanoTime();
		final double amountOfTicks = 60.0;
		double nanosec = 1000000000 / amountOfTicks;
		double flag = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		// game loop
		while(run) {
			long now = System.nanoTime();
			flag += (now - initialT) / nanosec;
			initialT = now;
			if (flag >= 1) {
				tick();
				updates++;
				flag--;
			}
			render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println(updates + "Ticks, Fps "+ frames);
				// reset
				updates = 0;
				frames = 0;
			}
		}	
		stop();
	}
	private void tick() {
		if(state == STATE.GAME) {
			p.tick();
			c.tick();
		}
		
		if(enemy_killed >= enemy_count && ! boss_stage) {
			c.createBoss();	
			boss_stage = true;
			
		}
		
		if(enemy_killed >= enemy_count && boss_stage && stage_clear) {
			enemy_count += 2;
			enemy_killed = 0;			
			LEVEL++;
			c.createEnemy(enemy_count);
			boss_stage = false;
			stage_clear = false;
		}
		
		if(HEALTH <= 0) {
			JOptionPane.showMessageDialog(null, "Your Little SPACE Ship is Destroyed");
			//c.removeEntity();
			HEALTH = 2 * 100;
			boss_stage = false;
			LEVEL = 1;
			Boss_HP = 10;
			HIT = 0;
			enemy_count = 4;
			enemy_killed = 0;
			state = STATE.MARKET;
			c.createEnemy(enemy_count);
		}
	}
	
	private void render() {
		// start buffers
		BufferStrategy bufferstrategy = this.getBufferStrategy();
		if(bufferstrategy == null) {
			createBufferStrategy(3);
			return;
		}
		// draw
		Graphics g = bufferstrategy.getDrawGraphics();
		g.drawImage(buffer_image, 0, 0, getWidth(), getHeight(), this);
		
		g.drawImage(background, 0,0,null);
		
		if(state == STATE.GAME) {
			p.render(g);
			c.render(g);
			
			Font fnt0 = new Font("arial", Font.BOLD, 20);
			g.setFont(fnt0);
			g.setColor(Color.white);
			g.drawString("LEVEL : "+ LEVEL , 10, 120);
			
			g.setColor(Color.gray);
			g.fillRect(5,50,200,30);
			
			g.setColor(Color.green);
			g.fillRect(5,50,HEALTH,30);
			
			g.setColor(Color.white);
			g.drawRect(5,50,200,30);
			
			w.render(g);
						
			if(boss_stage) {
				g.drawString("BOSS", 600, 40);
				
				g.setColor(Color.gray);
				g.fillRect(600,50,200,30);
				
				g.setColor(Color.red);
				g.fillRect(600,50,200-(200*HIT)/Boss_HP,30);
				
				g.setColor(Color.white);
				g.drawRect(600,50,200,30);
			}
			
		} else if (state == STATE.MENU) {
			menu.render(g);
		}
		else if (state == STATE.MARKET) {
			g.drawImage(upgradeview, 60,140,null);
			
			g.drawImage(moveview, market.getMarketX(), market.getMarketY(), null);

			market.render(g,moveview);
		}
		
		g.dispose();
		bufferstrategy.show();
		
	}
	

	public static void main(String args[]) {
		Game g = new Game();
		
		int widthsize = WIDTH * SCALE;
		int heightsize = HEIGHT * SCALE;
		g.setPreferredSize(new Dimension(widthsize, heightsize));
		g.setMaximumSize(new Dimension(widthsize, heightsize));
		g.setMinimumSize(new Dimension(widthsize, heightsize));
		
		// create frame
		JFrame frame = new JFrame(g.GAMENAME);
		frame.add(g);
		frame.pack(); // pack all components and sizes them
		// window close operation
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		g.start();
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(state == STATE.GAME) {
			if(key == KeyEvent.VK_RIGHT) {
				p.setVelX(movesize);
			} else if(key == KeyEvent.VK_LEFT) {
				p.setVelX(-movesize);
			} else if(key == KeyEvent.VK_DOWN) {
				p.setVelY(movesize);
			} else if(key == KeyEvent.VK_UP) {
				p.setVelY(-movesize);
			} 
			if(key == KeyEvent.VK_SPACE && !is_shooting) {
				int numa = market.getUpgradeAngleBullet();
				int nums = market.getStraightbullet();
				if (numa == 0)
					c.addEntity(new Bullet(p.getX(),p.getY(), 90, tex,this));
				else if (numa == 1) {
					c.addEntity(new Bullet(p.getX(),p.getY(), 60, tex, this));
					c.addEntity(new Bullet(p.getX(),p.getY(), 120, tex, this));
				}
				else if (numa == 2) {
					c.addEntity(new Bullet(p.getX(),p.getY(), 130, tex, this));
					c.addEntity(new Bullet(p.getX(),p.getY(), 90, tex, this));
					c.addEntity(new Bullet(p.getX(),p.getY(), 50, tex, this));
				}
				else if (numa == 3) {
					c.addEntity(new Bullet(p.getX(),p.getY(), 140, tex, this));
					c.addEntity(new Bullet(p.getX(),p.getY(), 106, tex, this));
					c.addEntity(new Bullet(p.getX(),p.getY(), 73, tex, this));
					c.addEntity(new Bullet(p.getX(),p.getY(), 40, tex, this));
				}
				
				if(nums == 1) {
					c.addEntity(new Bullet(p.getX()+20,p.getY(), 90, tex, this));
					c.addEntity(new Bullet(p.getX()-20,p.getY(), 90, tex, this));
				}
				else if(nums == 2) {
					c.addEntity(new Bullet(p.getX()-40,p.getY(), 90, tex, this));
					c.addEntity(new Bullet(p.getX(),p.getY(), 90, tex, this));
					c.addEntity(new Bullet(p.getX()+40,p.getY(), 90, tex, this));
	
				}

				is_shooting = true ;
				
			}
		
		}
	}
	
	public void keyReleased(KeyEvent e) {		
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_RIGHT) {
			p.setVelX(0);
		} else if(key == KeyEvent.VK_LEFT) {
			p.setVelX(0);
		} else if(key == KeyEvent.VK_DOWN) {
			p.setVelY(0);
		} else if(key == KeyEvent.VK_UP) {
			p.setVelY(0);
		} else if(key == KeyEvent.VK_SPACE) {
			is_shooting = false ;	
		}
	}
	

}
