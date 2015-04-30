package f2.spw;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;
import java.applet.*;

public class Music extends JFrame{

	Clip clip;
	public Music(){

		try{
			URL url = this.getClass().getClassLoader().getResource("f2/music/all_star.wav");
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);

			clip = AudioSystem.getClip();
					clip.open(audioIn);
			clip.start();
		}
		catch (UnsupportedAudioFileException e){
			System.out.println("notFound");
		}
		catch (IOException e){
			System.out.println("notFound");
		}
		catch (LineUnavailableException e){
			System.out.println("notFound");
		}
	}
}