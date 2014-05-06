package Properties;

public class Player {
	private String playerName;
	private int playerId;
	
	public Player() {
		
	}
	
	public Player(String playerName) {
		this.playerName = playerName;
	}
	
	public Player(int playerId, String playerName) {
		this.playerId = playerId;
		this.playerName = playerName;
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
}
