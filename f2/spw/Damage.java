package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Damage extends Sprite{
	public static final int Y_TO_FADE = 500;
	public static final int Y_TO_DIE = 660;
	
	BufferedImage b;

	private int step = 5;
	private boolean alive = true;
	
	public Damage(int x, int y, int width, int height) {
		super(x, y, 60, 70);
		
		try{
			b = ImageIO.read(new File("f2/photo/bomb.png"));
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

		g.drawImage(b, x, y, width, height, null);

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