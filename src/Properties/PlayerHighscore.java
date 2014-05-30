package Properties;

public class PlayerHighscore {
	private Player player;
	private int highscore;
	private int highscore_id;
	private int currentScore = 0;
	
	public PlayerHighscore(Player player, int highscore, int higscore_id) {
		this.player = player;
		this.highscore = highscore;
		this.highscore_id = higscore_id;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * @return the highscore
	 */
	public int getHighscore() {
		return highscore;
	}

	/**
	 * @param highscore the highscore to set
	 */
	public void setHighscore(int highscore) {
		this.highscore = highscore;
		System.out.println("Highscore: " + highscore);
	}

	/**
	 * @return the highscore_id
	 */
	public int getHighscore_id() {
		return highscore_id;
	}

	/**
	 * @param highscore_id the highscore_id to set
	 */
	public void setHighscore_id(int highscore_id) {
		this.highscore_id = highscore_id;
	}

	public int getCurrentScore() {
		return currentScore;
	}

	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}
	
	
	
}
