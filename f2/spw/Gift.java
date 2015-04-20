package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Gift extends Sprite{
	public static final int Y_TO_FADE = 500;
	public static final int Y_TO_DIE = 660;

	BufferedImage p;
	
	
	private int step = 4;
	private boolean alive = true;
	
	public Gift(int x, int y, int width, int height) {
		super(x, y, 70, 100);

		try{
			p = ImageIO.read(new File("f2/photo/gift.png"));
		}
		catch(IOException e){

		}
		
	}

	@Override
	public void draw(Graphics2D g) {
		if(y < Y_TO_FADE)
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
					(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
		}
		
		g.drawImage(p, x, y, width, height, null);
		
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
	
	public void disappear(){
		alive = false;
	}
}