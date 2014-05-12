package Properties;

public class Player {
	private String playerName;
	private int playerId;
	private int highscore;
	
	public Player() {
		
	}
	
	public Player(String playerName) {
		this.playerName = playerName;
	}
	
	public Player(int playerId, String playerName, int highscore) {
		this.playerId = playerId;
		this.playerName = playerName;
		this.highscore = highscore;
	}
	
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getHighscore() {
		return highscore;
	}

	public void setHighscore(int highscore) {
		this.highscore = highscore;
	}	
}
