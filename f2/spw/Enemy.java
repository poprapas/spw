package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy extends Sprite{
	public static final int Y_TO_FADE = 400;
	public static final int Y_TO_DIE = 660;
	
	private int step = (int)(Math.random()*30);
	private boolean alive = true;
	
	public Enemy(int x, int y, int width, int height) {
		super(x, y, 5, height);
		
	}

	@Override
	public void draw(Graphics2D g) {
		if(y < Y_TO_FADE)
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
					(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
		}
		if(Math.random() < 0.3)
			g.setColor(Color.YELLOW);

		else if(Math.random() >= 0.3 && Math.random() < 0.6)
			g.setColor(Color.GREEN);

		else if(Math.random() >= 0.6 && Math.random() < 1)
			g.setColor(Color.CYAN);
		
		g.fillRect(x, y, width, height);
		
	}

	public void proceed(){
		y += step;
		if(y > Y_TO_DIE){
			alive = false;
		}
	}
	
	public boolean isAlive(){
		return alive;
	}
}