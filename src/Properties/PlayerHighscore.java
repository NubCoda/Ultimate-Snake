package Properties;

public class PlayerHighscore {
	private Player player;
	private int highscore;
	private int highscoreId;
	private int currentScore = 0;

	public PlayerHighscore(Player player, int highscore, int higscore_id) {
		this.player = player;
		this.highscore = highscore;
		this.highscoreId = higscore_id;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player
	 *            the player to set
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
	 * @param highscore
	 *            the highscore to set
	 */
	public void setHighscore(int highscore) {
		this.highscore = highscore;
		System.out.println("Highscore: " + highscore);
	}

	/**
	 * @return the highscore_id
	 */
	public int getHighscoreId() {
		return highscoreId;
	}

	/**
	 * @param highscore_id
	 *            the highscore_id to set
	 */
	public void setHighscoreId(int highscore_id) {
		this.highscoreId = highscore_id;
	}

	public int getCurrentScore() {
		return currentScore;
	}

	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}

}
