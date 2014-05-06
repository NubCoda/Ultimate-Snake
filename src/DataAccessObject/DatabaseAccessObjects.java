package DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;

import properties.BenutzerLogin;
import properties.EmailKontakt;
import Properties.Player;

public class DatabaseAccessObjects implements IDataAccessObject {

	private Connection conn;
	private PreparedStatement prStatement;

	private String database = "MailDB.db3";
	private String table = "Mailadresse";
	private String url;
	private String sql;
	
	
	@Override
	public void createPlayer(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Player getPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

}
