package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Damage> damages = new ArrayList<Damage>();
	private ArrayList<Gift> gifts = new ArrayList<Gift>();	
	private SpaceShip v;	
	
	private Timer timer;
	
	private long score = 0;
	private double difficulty = 0.15;
	
	private int extraLive = 0;

	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);
		
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
			}
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*500), 20, 5,(int)(Math.random()*50));
		gp.sprites.add(e);
		enemies.add(e);
	}
	
	private void generateDamage(){
		Damage d = new Damage((int)(Math.random()*500), 15, 3,(int)(Math.random()*40));
		gp.sprites.add(d);
		damages.add(d);
	}

	private void generateGift(){
		Gift g = new Gift((int)(Math.random()*500), 10, 2,(int)(Math.random()*30));
		gp.sprites.add(g);
		gifts.add(g);
	}

	private void process(){
		if(Math.random() < difficulty){
			generateEnemy();
		}

		if(Math.random() < 0.05){
			generateDamage();
		}

		if(Math.random() < 0.025){
			generateGift();
		}

		if(score >= 3000 && score < 4000){
			extraLive += 1;
			score = 4000;
		}
		else if(score >= 10000 && score < 11000){
			extraLive += 1;
			score = 11000;
		}
		
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				score += 499;
				v.width += 0.75;
				v.height += 0.75;
			}
		}
		
		Iterator<Damage> d_iter = damages.iterator();
		while(d_iter.hasNext()){
			Damage d = d_iter.next();
			d.proceed();
			
			if(!d.isAlive()){
				d_iter.remove();
				gp.sprites.remove(d);
			}
		}

        Iterator<Gift> g_iter = gifts.iterator();
		while(g_iter.hasNext()){
			Gift g = g_iter.next();
			g.proceed();
			
			if(!g.isAlive()){
				g_iter.remove();
				gp.sprites.remove(g);
			}
		}

		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				if(extraLive >= 0){
					extraLive -= 1;
				}
				else if(extraLive < 0){
					die();
				}
				return;
			}
		}

		Rectangle2D.Double dr;
		for(Damage d : damages){
			dr = d.getRectangle();
			if(dr.intersects(vr)){
				score -= 199;
				d.disappear();
				return;
			}
		}

		Rectangle2D.Double gr;
		for(Gift g : gifts){
			gr = g.getRectangle();
			if(gr.intersects(vr)){
				score += 99;
				g.disappear();
				return;
			}
		}
	}
	
	public void die(){
		timer.stop();
	}
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v.move_Width(-1);
			break;
		case KeyEvent.VK_RIGHT:
			v.move_Width(1);
			break;
		case KeyEvent.VK_D:
			difficulty += 0.2;
			break;
		case KeyEvent.VK_UP:
			v.move_Height(-1);
			break;
		case KeyEvent.VK_DOWN:
			v.move_Height(1);
			break;
		case KeyEvent.VK_S:
			timer.stop();
			break;
		case KeyEvent.VK_A:
			timer.start();
			break;
		}
	}

	public long getScore(){
		return score;
	}

	public int getExtraLive(){
		return extraLive;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
