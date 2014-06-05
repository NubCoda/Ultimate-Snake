package Properties;

/**
 * 
 * 
 */
public class Player {
	private String playerName;
	private int playerId;
	private int highscore;
	private int score = 0;
	private int bulletCount = 0;
	
	/**
	 * 
	 */
	public Player() {

	}

	/**
	 * 
	 * @param playerName
	 */
	public Player(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * 
	 * @param playerId
	 * @param playerName
	 * @param highscore
	 */
	public Player(int playerId, String playerName, int highscore) {
		this.playerId = playerId;
		this.playerName = playerName;
		this.highscore = highscore;
	}

	/**
	 * 
	 * @return
	 */
	public int getPlayerId() {
		return playerId;
	}

	/**
	 * 
	 * @param playerId
	 */
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	/**
	 * 
	 * @return
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * 
	 * @param playerName
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * 
	 * @return
	 */
	public int getHighscore() {
		return highscore;
	}

	/**
	 * 
	 * @param highscore
	 */
	public void setHighscore(int highscore) {
		this.highscore = highscore;
	}

	/**
	 * 
	 * @return
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * 
	 * @param i
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * 
	 * @return
	 */
	public int getBulletCount() {
		return bulletCount;
	}

	/**
	 * 
	 * @param count
	 */
	public void setBulletCount(int count) {
		this.bulletCount = count;
	}
}
