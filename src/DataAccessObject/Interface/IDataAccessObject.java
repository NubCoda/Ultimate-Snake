package DataAccessObject.Interface;

import Properties.Player;

public interface IDataAccessObject {
	public void createConnection();
	public void createPlayer(Player player);
	public Player getPlayer();
}
