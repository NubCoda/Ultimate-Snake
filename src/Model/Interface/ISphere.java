package Model.Interface;

/**
 * Dieses Interface dient für den Schuss. Es wird hauptsächlich zur
 * Unterscheidung bei Kollision benötigt.
 */
public interface ISphere extends IActor {
	/**
	 * Gibt die Richtung zurück
	 * 
	 * @return Die Richtung in Welche die Kugel sich bewegt.
	 */
	Direction getDirection();

	/**
	 * Gibt zurück, ob der Schuss noch im Spiel vorhanden ist oder nicht
	 * 
	 * @return Wahrheitswert, zur Bestimmung des zustandes
	 */
	public boolean isGone();

	/**
	 * Setzt einen Wahrheitswert welcher angibt, ob der Schuss im Spiel
	 * vorhanden ist oder nicht
	 * 
	 * @param b
	 *            Der Wahrheitswert, zur Bestimmung des zustandes
	 */
	void setGone(boolean b);
}