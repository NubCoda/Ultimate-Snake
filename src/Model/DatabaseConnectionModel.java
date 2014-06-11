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
 * Das Datenbankmodel f�r die Abfragen, Updates und Inserts �ber JDBC mit einer
 * SQLite DB
 */
public class DatabaseConnectionModel {
	/*
	 * Die Variablen Einige Pfade kommen aus der IConstant Klasse
	 */
	private Connection connection;
	private PreparedStatement preparedStatement;
	private String table = IConstants.TABLE_PLAYER;
	private String url = IConstants.DATABASE_PATH;
	private String sql;
	private Vector<Player> playerVector = null;

	/**
	 * Diese Funktion baut mit Hilfe des JDBC-Treibers die Datenbankverbindung
	 * auf
	 */
	public void createConnection() {
		/*
		 * Baue die Verbindung �ber die JDBC Klasse auf Setze die Datenbank auf
		 * read and write F�ngt ab, wenn es eine SQL oder Klassen Exception gibt
		 */
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
	 * Methode zum Erzeugen des Spielers. Gibt false zur�ck, wenn der Spieler
	 * nicht erzeugt wurde
	 * 
	 * @param playerName
	 *            Der Name des Spielers, der erzeugt werden soll
	 * @return Wahrheitswert welcher angibt, ob Spieler erzeugt wurde
	 */
	public boolean createPlayer(String playerName) {
		boolean notCreated = true;
		/*
		 * Als erstes Fragen wir ab, ob es diesen Spieler bereits auf der
		 * Datenbank gibt. Wenn es denn bereits gibt, wird der Benutzer dar�ber
		 * informiert und erneut aufgefordert, einen Namen anzugeben
		 */
		try {
			Player player = getSinglePlayer(playerName);
			if (player != null) {
				notCreated = false;
			}
			/*
			 * Erzeuge Datenbankverbindung
			 */
			createConnection();
			/*
			 * Wenn noch nicht erstellt erstelle Spieler!
			 */
			if (notCreated) {
				sql = "INSERT INTO " + table + " (player_name) values (?)";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, playerName);
				preparedStatement.executeUpdate();

			}
			/*
			 * Schlie�e die Datenbank verbindung nach Abschluss der Logik!
			 */
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*
		 * Gib den booleschen Wert zur�ck
		 */
		return notCreated;
	}

	/**
	 * Methode, die einen bestimmten Spieler zur�ck gibt, wenn vorhanden
	 * 
	 * @param playerName
	 *            Der Name des Spieler, der selektiert werden soll
	 * @return Das Spieler-Objekt f�r den Spielernamen
	 */
	public Player getSinglePlayer(String playerName) {
		/*
		 * Erzeuge Datenbankverbindung
		 */
		createConnection();
		Player player = null;
		sql = "SELECT * FROM " + table + " WHERE player_name = ?";
		/*
		 * Baue Statement auf
		 */
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, playerName);
			ResultSet resultSet = preparedStatement.executeQuery();
			/*
			 * Nur wenn Resultset einen Datensatz besitzt, wird ein
			 * Player-Objekt erzeugt
			 */
			if (resultSet.isBeforeFirst()) {
				player = new Player(resultSet.getInt("player_id"),
						resultSet.getString("player_name"),
						resultSet.getInt("highscore"));
			}
			/*
			 * Schlie�e Datenbankverbindung
			 */
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*
		 * Gebe den Spieler zur�ck.
		 */
		return player;
	}

	/**
	 * Eine Methode, die uns einen Vector mit allen Spielern zur�ckgibt.
	 * 
	 * @return Vector mit allen Spielern
	 */
	public Vector<Player> getPlayers() {
		/*
		 * Erzeuge Datenbankverbindung
		 */
		createConnection();
		playerVector = new Vector<Player>();
		sql = "SELECT * FROM " + table;
		/*
		 * Baue SQL Statement
		 */
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			/*
			 * Solange Resulset Datens�tze hat, erzeuge neuen Spieler und f�ge
			 * Sie dem Vector hinzu
			 */
			while (resultSet.next()) {
				playerVector.add(new Player(resultSet.getInt("player_id"),
						resultSet.getString("player_name"), 0));
			}
			/*
			 * Schlie�e Datenbankverbindung
			 */
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return playerVector;
	}

	/**
	 * Diese Methode gibt uns die 10 besten Spieler zur�ck
	 * 
	 * @return Ein Vector mit Spieler-Objekten.
	 */
	public Vector<Player> getTopPlayers() {
		/*
		 * Baue Datenbankverbindung auf und erzeuge den leeren Vector.
		 */
		createConnection();
		Vector<Player> vectorTopPlayer = new Vector<Player>();
		sql = "SELECT * FROM " + table
				+ " ORDER BY highscore DESC, player_name ASC LIMIT 10";
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			/*
			 * Solange das Resultset Datens�tze zur�ckgibt, schreiben wir diese
			 * in den Vector.
			 */
			while (resultSet.next()) {
				vectorTopPlayer.add(new Player(resultSet.getInt("player_id"),
						resultSet.getString("player_name"), resultSet
								.getInt("highscore")));
			}
			/*
			 * Schlie�e Datenbankverbindung
			 */
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vectorTopPlayer;
	}

	/**
	 * Diese Funktion aktualisiert den Highscore des Spielers.
	 * 
	 * @param player
	 *            Der Spielername, f�r den der Highscore aktualisiert werden
	 *            soll.
	 */
	public void updatePlayerScore(Player player) {
		/*
		 * Baue Datenbankverbindung auf
		 */
		createConnection();
		sql = "UPDATE " + table + " SET highscore = ? WHERE player_id = ?";
		try {
			/*
			 * Setze die Werte �ber setInt f�r die "?"
			 */
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, player.getHighscore());
			preparedStatement.setInt(2, player.getPlayerId());
			/*
			 * F�hre update aus und schlie�e Verbindung zur Datenbank
			 */
			preparedStatement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}