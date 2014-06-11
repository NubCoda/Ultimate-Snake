package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 * Diese Klasse dient zum Abspielen von Musik. Sie verwendent eine externe
 * Libary, damit MP3s abgespielt werden können. Denn zumindest Java 7 kann das
 * ohne weiteres nicht. Des Weiteren bietet diese Libary Funktion zum vor und
 * zurück spielen. Momentan wird nur eine Hintergrundmelodie abgespielt.
 */
public class GameSoundModel {
	/*
	 * Variabeln
	 */
	private Player soundPlayer;
	private Thread thread;
	private Runnable runnable;
	private String soundFilePath;

	/**
	 * Konstruktor der Klasse, setz den Pfad zur Musik
	 * 
	 * @param soundFilePath
	 *            Der Pfad zur Soundatei für die Hintergrundmusik
	 */
	public GameSoundModel(String soundFilePath) {
		this.soundFilePath = soundFilePath;
	}

	/**
	 * Stoppt die Melodie, wenn das Soundplayer Objekt != null ist
	 */
	public void stopSound() {
		if (soundPlayer != null) {
			soundPlayer.close();
		}
	}

	/**
	 * Spielt über die Klassen der Libary die Musik ab. Dies muss in einem
	 * weiteren Hintergrund-Thread passieren, da sich ansonsten das Spiel
	 * aufhängt
	 */
	public void playSound() {
		try {
			/*
			 * Über den Fileinputstream wird die Datei erzeugt beziehungsweise
			 * ausgelesen und dann abgespielt.
			 */
			soundPlayer = new Player(new FileInputStream(
					new File(soundFilePath)));
		} catch (JavaLayerException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		/*
		 * Das Runnable für den Thread.
		 */
		runnable = new Runnable() {
			@Override
			public void run() {
				try {
					/*
					 * Spiele Sound ab!
					 */
					soundPlayer.play();
				} catch (JavaLayerException e) {
					e.printStackTrace();
				}
			}
		};
		/*
		 * Erzeuge und starte Thread mit dem Runnable!
		 */
		thread = new Thread(runnable);
		thread.start();
	}
}