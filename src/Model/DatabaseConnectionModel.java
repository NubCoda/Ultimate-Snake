package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import Model.Interface.IConstants;
import Properties.Player;

/**
 * 
 * 
 */
public class DatabaseConnectionModel {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private String table = IConstants.TABLE_PLAYER;
	private String url = IConstants.DATABASE_PATH;
	private String sql;
	private Vector<Player> playerVector = null;

	public void createConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(url);
			connection.setReadOnly(false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param playerName
	 */
	public boolean createPlayer(String playerName) {
		boolean notCreated = true;
		try {
			Player player = getSinglePlayer(playerName);
			if (player != null) {
				notCreated = false;
			}
			createConnection();
			
			if (notCreated) {
				sql = "INSERT INTO " + table + " (player_name) values (?)";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, playerName);
				preparedStatement.executeUpdate();

			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notCreated;
	}

	/**
	 * 
	 * @param playerName
	 * @return
	 */
	public Player getSinglePlayer(String playerName) {
		createConnection();
		Player player = null;
		sql = "SELECT * FROM " + table + " WHERE player_name = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, playerName);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.isBeforeFirst()) {
				player = new Player(resultSet.getInt("player_id"),
						resultSet.getString("player_name"),
						resultSet.getInt("highscore"));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return player;
	}

	/**
	 * 
	 * @return
	 */
	public Vector<Player> getPlayers() {
		createConnection();
		Player player = new Player();
		playerVector = new Vector<Player>();
		sql = "SELECT * FROM " + table;
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				player = new Player(resultSet.getInt("player_id"),
						resultSet.getString("player_name"), 0);
				playerVector.add(player);
				player = null;
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return playerVector;
	}

	/**
	 * 
	 * @return
	 */
	public Vector<Player> getTopPlayers() {
		createConnection();
		Vector<Player> vectorTopPlayer = new Vector<Player>();
		Player player;
		sql = "SELECT * FROM " + table
				+ " ORDER BY highscore DESC, player_name ASC LIMIT 10";
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				player = new Player(resultSet.getInt("player_id"),
						resultSet.getString("player_name"),
						resultSet.getInt("highscore"));
				vectorTopPlayer.add(player);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vectorTopPlayer;
	}

	/**
	 * 
	 * @param player
	 */
	public void updatePlayerScore(Player player) {
		createConnection();
		sql = "UPDATE " + table + " SET highscore = ? WHERE player_id = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, player.getHighscore());
			preparedStatement.setInt(2, player.getPlayerId());
			preparedStatement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}