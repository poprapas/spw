package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Fish extends Sprite{
	public static final int X_TO_FADE = 450;
	public static final int X_TO_DIE = 500;

	BufferedImage f;
	
	
	private int step = 4;
	private boolean alive = true;
	
	public Fish(int x, int y, int width, int height) {
		super(x, y, width, height);

		try{
			f = ImageIO.read(new File("f2/photo/fish.png"));
		}
		catch(IOException e){

		}
		
	}

	@Override
	public void draw(Graphics2D g) {
		if(x < X_TO_FADE)
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
					(float)(X_TO_DIE - x)/(X_TO_DIE - X_TO_FADE)));
		}
		
		g.drawImage(f, x, y, width, height, null);
		
	}

	public void proceed(){
		x -= step;
		if(x > X_TO_DIE){
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