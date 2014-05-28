package Properties;

/**
 * 
 * 
 */
public class Player {
	private String playerName;
	private int playerId;

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
	public Player(int playerId, String playerName) {
		this.playerId = playerId;
		this.playerName = playerName;
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

}
