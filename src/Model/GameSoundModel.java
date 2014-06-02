package Model;

import javax.sound.sampled.*;
import javax.swing.SwingWorker;

import java.io.*;

public class GameSoundModel extends SwingWorker<Void, Void> {

	public GameSoundModel() {

	}

	@Override
	protected Void doInBackground() {
		try {
			AudioInputStream audioIn = AudioSystem
					.getAudioInputStream(new File(
							"resources/sound/background_game.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
			System.out.println("start");
			Thread.sleep(8200000);
			clip.close();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
}
