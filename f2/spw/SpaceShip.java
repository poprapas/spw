package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;

public class SpaceShip extends Sprite{

	int step = 6;
	
	public SpaceShip(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.GRAY);
		g.fillRect(x, y, width, height);
		
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
		if(x > 400 - width)
			x = 400 - width;
	}

}
