package Model;

import java.util.Observable;
import java.util.concurrent.CopyOnWriteArrayList;

import Model.Interface.IActor;

/**
 * Die Logik Klasse, die im Hintergrund diverse Aktionen durchführt. Im Prinzip
 * der gesamte Ablauf zur Laufzeit des Spiels (wenn gestartet)
 */
public class Logic extends Observable implements Runnable {
	/*
	 * Die Variablen CopyOnWriteArrayList wurde verwendet, da es mit einem
	 * einfachen Vector zu Problemen führte, wenn dieser gerade durchlaufen
	 * wurde und zum Beispiel ein Apfel eingesammlt wurde.
	 */
	private CopyOnWriteArrayList<IActor> actors;
	private boolean isGameRunning;
	private long last = 0;
	private double delta = 0;
	private boolean isKilled = false;

	/**
	 * Konstruktor der Klasse.
	 */
	public Logic() {
		this.actors = new CopyOnWriteArrayList<IActor>();
	}

	/**
	 * Funktion zum Hinzufügen der Actors
	 * 
	 * @param actor
	 *            Der Actor, der dem Spiel hinzugefügt werden soll.
	 */
	public void addActor(IActor actor) {
		actors.add(actor);
	}

	/**
	 * Lösche alle Actors aus dem Spiel
	 */
	public void clearActors() {
		actors.clear();
	}

	/**
	 * Setzt den Wert für die Variable. Ist er false, wird das Spiel pausiert /
	 * angehalten.
	 * 
	 * @param isGameRunning
	 *            Wahrheitswert welcher angibt, ob das Spiel läuft oder nicht
	 */
	public void setGameRunning(boolean isGameRunning) {
		this.isGameRunning = isGameRunning;
	}

	/**
	 * Diese Methode gibt einen bestimmten Actor zurück
	 * 
	 * @param actor
	 *            Welcher Actor soll zurückgegeben werden
	 * @return Das Actor-Objekt, welches geuscht wurde
	 */
	public IActor getActor(IActor actor) {
		return actors.get(actors.indexOf(actor));
	}

	@Override
	public void run() {
		/*
		 * Diese Klasse implementiert das Runnable Hier passiert die Logik für
		 * die Laufzeit des Spieles Das Spiel wird hier gestartet und führt für
		 * alle Objekte vom Typ Actor die Logik aus
		 */
		last = System.nanoTime();
		/*
		 * Solange das Spiel nicht beendet ist
		 */
		while (!isKilled) {
			try {
				/*
				 * Nur wenn die Variable true ist
				 */
				if (isGameRunning) {
					/*
					 * Berechne die Frames, damit die Geschwindigkeit auf
					 * schwachen sowie starken Rechnern gleich schnell ist
					 */
					long currentTime = System.nanoTime();
					delta = (currentTime - last) / 1000000.00;
					last = currentTime;
					/*
					 * Iteriere mit for-Each durch alle Actos und führe die
					 * actuate Funktion aus
					 */
					for (IActor actor : actors) {
						actor.actuate(delta);
					}
					/*
					 * Checke für alle Spriteviews die Kollision durch
					 * verschachtelte For-Schleifen!
					 */
					for (int i = 0; i < actors.size(); i++) {
						for (int j = i + 1; j < actors.size(); j++) {
							IActor s1 = actors.get(i);
							IActor s2 = actors.get(j);
							s1.checkCollision(s2);
						}
					}

				}
				/*
				 * Benachrichtige die Observer und schlafe für 10 MS!
				 */
				setChanged();
				notifyObservers();
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Entferne Actor (Models) aus der Logik Klasse
	 * 
	 * @param actor
	 *            Actor, welcher entfernt werden soll
	 */
	public void removeActor(IActor actor) {
		this.actors.remove(actor);
	}

	/**
	 * Gibt einen Actor an einer bestimmten Position zurück
	 * 
	 * @param x
	 *            Die x-Position des Actors
	 * @param y
	 *            Die y-Position des Actors
	 * @param width
	 *            Die Breite des Actors
	 * @param height
	 *            Die Höhe des Actors
	 * @return Der Actor an der bestimmten Stelle, wenn keiner gefunden wurde,
	 *         dann wird null zurückgegeben
	 */
	public IActor getAtPosition(double x, double y, double width, double height) {
		IActor actor = null;
		/*
		 * Iteriere durch die Liste, bis der Actor gefunden wurde.
		 */
		for (int i = 0; i < actors.size(); i++) {
			/*
			 * Sobald Actor gefunden wurde, breche ab
			 */
			if (actors.get(i).getBounding().contains(x, y, width, height)) {
				actor = actors.get(i);
				break;
			}
		}
		return actor;
	}
}