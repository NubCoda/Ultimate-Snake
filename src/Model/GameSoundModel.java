package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class GameSoundModel {
	private Player soundPlayer;
	private FileInputStream fileInputStream;
	private Thread thread;
	private Runnable runnable;
	private String soundFilePath;
	
	public GameSoundModel(String soundFilePath) {
		this.soundFilePath = soundFilePath;
	}

	public void stopSound() {
		soundPlayer.close();
	}

	public void playSound() {
		try {
			fileInputStream = new FileInputStream(new File(soundFilePath));
			soundPlayer = new Player(fileInputStream);
		} catch (JavaLayerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		runnable = new Runnable() {
			@Override
			public void run() {
				try {
					soundPlayer.play();
				} catch (JavaLayerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		thread = new Thread(runnable);
		thread.start();
	}

}
