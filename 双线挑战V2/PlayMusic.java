package com.cxd.linecargame210127.V2;

import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JApplet;
 
public class PlayMusic {
	public AudioClip music = loadSound("image_lineballgame/backgroudmusic_lineballgame.wav");
	
	public static AudioClip loadSound(String filename) {
		URL url = null;
		try {
			url = new URL("file:" + filename);
		} 
		catch (MalformedURLException e) {;}
		return JApplet.newAudioClip(url);
	}
	public void play() {
		music.play();
		music.loop();
	}
}

