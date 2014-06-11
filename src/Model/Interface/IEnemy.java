package Model.Interface;

/**
 * Dieses Interface wird f�r Actor verwendet, die als "Feind" gelten sollen. Es
 * dient haupts�chlich f�r die Pr�fung von Kollision
 */
public interface IEnemy extends IActor {
	/**
	 * Methoden-K�rper zum Pr�fen, ob der Gegner am Leben ist.
	 * 
	 * @return Wahrheitswert welcher angibt, ob der Gegner noch am Leben ist
	 */
	public boolean isAlive();
}