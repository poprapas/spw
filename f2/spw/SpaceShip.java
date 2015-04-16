package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpaceShip extends Sprite{

	int step = 6;
  	BufferedImage k;
	
	public SpaceShip(int x, int y, int width, int height) {
		super(x, y, width, height);
		
		try{
			k = ImageIO.read(new File("f2/photo/kir.png"));
		}
		catch(IOException e){

		}

	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(k, x, y, width, height, null);
		
	}

	public void move_Height(int direction){
		y += (step * direction);
		if(y < 0)
			y = 0;
		if(y > 750 - height)
			y = 750 - height;
	}

	public void move_Width(int direction){
		x += (step * direction);
		if(x < 0)
			x = 0;
		if(x > 500 - width)
			x = 500 - width;
	}

}
