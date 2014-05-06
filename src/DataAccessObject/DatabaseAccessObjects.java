package DataAccessObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataAccessObject.Interface.IDataAccessObject;
import Model.Interface.IConstants;
import Properties.Player;

public class DatabaseAccessObjects implements IDataAccessObject {

	private Connection connection;
	private PreparedStatement preparedStatement;

	private String database = IConstants.DATABASE_NAME;
	private String table = IConstants.TABLE_PLAYER_NAME;
	private String url = IConstants.DATABASE_PATH;
	private String sql;
	@Override
	public void createConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			// initialise connection
			url = "jdbc:sqlite:" + database;
			// url
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
	public void createPlayer(Player player) {
		sql = "SELECT * FROM player";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);  		
			ResultSet resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Player getPlayer() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
