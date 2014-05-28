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
	public void createPlayer(String playerName) {
		createConnection();
		sql = "INSERT INTO " + player_table + " (player_name) values (?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, playerName);
			preparedStatement.executeUpdate();
			sql = "SELECT * FROM sqlite_sequence WHERE name = '" + player_table
					+ "'";
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
}