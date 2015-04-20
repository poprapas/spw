package f2.spw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GamePanel extends JPanel {
	
	private BufferedImage bi;	
	Graphics2D big;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	BufferedImage bg;

	public GamePanel() {
		bi = new BufferedImage(500, 660, BufferedImage.TYPE_INT_ARGB);

		try{
			bg = ImageIO.read(new File("f2/photo/bg.gif"));
		}
		catch(IOException e){

		}

		big = (Graphics2D) bi.getGraphics();
	}

	public void updateGameUI(GameReporter reporter){
		big.clearRect(0, 0, 500, 650);
		if(reporter.getScore() < 5000)
		    big.setColor(Color.WHITE);	

		else if(reporter.getScore() >= 5000 && reporter.getScore() < 10000)
		    big.setColor(Color.MAGENTA); 

		else		   	
 			big.setColor(Color.ORANGE);

 		big.drawImage(bg, 0, 0, 500, 660, null);

		big.drawString(String.format("%08d", reporter.getScore()), 200, 20);

		if(reporter.getExtraLive() >= 0){
			big.drawString(String.format("%d", reporter.getExtraLive()), 20, 20);
		}
		else if(reporter.getExtraLive() < 0){
			big.drawString(String.format("%s", "GAME OVER"), 20, 20);
		}

		for(Sprite s : sprites){
			s.draw(big);
		}
		
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}

}
