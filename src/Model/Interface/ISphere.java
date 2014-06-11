package Model.Interface;

/**
 * Dieses Interface dient f�r den Schuss. Es wird haupts�chlich zur
 * Unterscheidung bei Kollision ben�tigt.
 */
public interface ISphere extends IActor {
	/**
	 * Gibt die Richtung zur�ck
	 * 
	 * @return Die Richtung in Welche die Kugel sich bewegt.
	 */
	Direction getDirection();

	/**
	 * Gibt zur�ck, ob der Schuss noch im Spiel vorhanden ist oder nicht
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