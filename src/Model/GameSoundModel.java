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
	private boolean fileClosed = false;
	
	public GameSoundModel(String soundFilePath) {
		this.soundFilePath = soundFilePath;
		openSoundFile();
	}

	public void stopSound() {
		soundPlayer.close();
		fileClosed = true;
	}

	public void playSound() {
		if(fileClosed){
			fileClosed = false;
			openSoundFile();
		}
		try {
			soundPlayer = new Player(fileInputStream);
		} catch (JavaLayerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
	
	private void openSoundFile(){
		try {
			fileInputStream = new FileInputStream(new File(soundFilePath));
			soundPlayer = new Player(fileInputStream);
		} catch (JavaLayerException | FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
