package Properties;

/**
 * Klasse, welche die Infos �ber einen Spieler beinhaltet
 */
public class Player {
	private String playerName;
	private int playerId;
	private int highscore;
	private int score = 0;
	private int bulletCount = 0;

	/**
	 * Konstruktor des Players
	 * 
	 * @param playerId
	 *            Die ID des Spielers
	 * @param playerName
	 *            Der Name des Spielers
	 * @param highscore
	 *            Die Highscore des Spielers
	 */
	public Player(int playerId, String playerName, int highscore) {
		this.playerId = playerId;
		this.playerName = playerName;
		this.highscore = highscore;
	}

	/**
	 * Getter f�r die ID des Spielers
	 * 
	 * @return Die ID des Spielers
	 */
	public int getPlayerId() {
		return playerId;
	}

	/**
	 * Setter f�r die ID des Spielers
	 * 
	 * @param playerId
	 *            Die ID des Spielers
	 */
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	/**
	 * Getter f�r den Spielernamen
	 * 
	 * @return Der Spielername
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * Setter f�r den Spielernamen
	 * 
	 * @param playerName
	 *            Name des Spielers
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * Getter f�r die Highscore des Spielers
	 * 
	 * @return Die Highscore des Spielers
	 */
	public int getHighscore() {
		return highscore;
	}

	/**
	 * Setter f�r die Highscore des Spielers
	 * 
	 * @param highscore
	 *            Die Highscore des Spielers
	 */
	public void setHighscore(int highscore) {
		this.highscore = highscore;
	}

	/**
	 * Getter f�r die aktuelle Score
	 * 
	 * @return Die Score des Spielers
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * Setter f�r die aktuelle Score
	 * 
	 * @param score
	 *            Die Score welche gesetzt werden soll
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Getter f�r die Anzahl der Sch�sse
	 * 
	 * @return Anzahl der Sch�sse
	 */
	public int getBulletCount() {
		return bulletCount;
	}

	/**
	 * Setter f�r die Anzahl der Sch�sse
	 * 
	 * @param count
	 *            Anzahl der Sch�sse
	 */
	public void setBulletCount(int count) {
		this.bulletCount = count;
	}
}
