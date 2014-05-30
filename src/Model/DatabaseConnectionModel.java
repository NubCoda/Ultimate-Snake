package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import Model.Interface.IConstants;
import Model.Interface.IDataBaseConnection;
import Properties.Player;
import Properties.PlayerHighscore;

/**
 * 
 * 
 */
public class DatabaseConnectionModel implements IDataBaseConnection {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private String player_table = IConstants.TABLE_PLAYER;
	private String highscore_table = IConstants.TABLE_HIGHSCORE;
	private String url = IConstants.DATABASE_PATH;
	private String sql;

	@Override
	public void createConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			// initialise connection
			connection = DriverManager.getConnection(url);
			connection.setReadOnly(false);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean createPlayer(String playerName) {
		createConnection();
		boolean notCreated = true;

		sql = "SELECT * FROM " + player_table + " WHERE player_name = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, playerName);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				notCreated = false;
				connection.close();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (notCreated) {
			sql = "INSERT INTO " + player_table + " (player_name) values (?)";
			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, playerName);
				preparedStatement.executeUpdate();
				sql = "SELECT * FROM sqlite_sequence WHERE name = '"
						+ player_table + "'";
				preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet != null) {
					sql = "INSERT INTO " + highscore_table
							+ " (player_id, highscore) values (?, ?)";
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt(1, resultSet.getInt("seq"));
					preparedStatement.setString(2, playerName);
					preparedStatement.executeUpdate();
				}
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return notCreated;
	}

	/**
	 * 
	 * @param playerName
	 * @return
	 */
	public PlayerHighscore getSinglePlayer(String playerName) {
		createConnection();
		PlayerHighscore playerHighscore = null;
		sql = "SELECT player.*, highscore.highscore, highscore.highscore_id FROM "
				+ player_table
				+ " player"
				+ " JOIN "
				+ highscore_table
				+ " highscore ON"
				+ " player.player_id = highscore.player_id"
				+ " WHERE player_name = '" + playerName + "'";
		// System.out.println(sql);
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
				playerHighscore = new PlayerHighscore(new Player(
						resultSet.getInt("player_id"),
						resultSet.getString("player_name")),
						resultSet.getInt("highscore"),
						resultSet.getInt("highscore_id"));
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return playerHighscore;
	}

	@Override
	public Vector<PlayerHighscore> getPlayers() {
		createConnection();
		PlayerHighscore playerHighscore = null;
		Vector<PlayerHighscore> playerVector = new Vector<PlayerHighscore>();
		sql = "SELECT player.player_name, highscore.* FROM " + player_table
				+ " player JOIN " + highscore_table + " highscore ON"
				+ " highscore.player_id = player.player_id";
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				playerHighscore = new PlayerHighscore(new Player(
						resultSet.getInt("player_id"),
						resultSet.getString("player_name")),
						resultSet.getInt("highscore"),
						resultSet.getInt("highscore_id"));
				playerVector.add(playerHighscore);
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return playerVector;
	}

	@Override
	public void updatePlayerScore(PlayerHighscore playerHighscore) {
		createConnection();
		sql = "UPDATE " + highscore_table
				+ " SET highscore = ? WHERE player_id = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, playerHighscore.getCurrentScore());
			preparedStatement.setInt(2, playerHighscore.getPlayer()
					.getPlayerId());
			int update = preparedStatement.executeUpdate();
			System.out.println(update);
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Vector<PlayerHighscore> getTopTenPlayers() {
		createConnection();
		Vector<PlayerHighscore> vectorTopPlayer = new Vector<PlayerHighscore>();
		PlayerHighscore playerHighscore;
		sql = "SELECT player.player_name, highscore.* FROM "
				+ player_table
				+ " player JOIN "
				+ highscore_table
				+ " highscore ON player.player_id = highscore.player_id ORDER BY highscore DESC, player_name ASC LIMIT 10";
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				playerHighscore = new PlayerHighscore(new Player(
						resultSet.getInt("player_id"),
						resultSet.getString("player_name")),
						resultSet.getInt("highscore"),
						resultSet.getInt("highscore_id"));
				vectorTopPlayer.add(playerHighscore);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vectorTopPlayer;
	}
}