package Model.Interface;

/**
 * Dieses Interface wird für Actor verwendet, die als "Feind" gelten sollen. Es
 * dient hauptsächlich für die Prüfung von Kollision
 */
public interface IEnemy extends IActor {
	/**
	 * Methoden-Körper zum Prüfen, ob der Gegner am Leben ist.
	 * 
	 * @return Wahrheitswert welcher angibt, ob der Gegner noch am Leben ist
	 */
	public boolean isAlive();
}