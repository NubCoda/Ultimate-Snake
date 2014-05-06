package DataAccessObject;

import Properties.Player;

public interface IDataAccessObject {
	public void createPlayer(Player player);
	public Player getPlayer();
}
