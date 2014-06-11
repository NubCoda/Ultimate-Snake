package Controller;

import java.io.IOException;
import java.util.Vector;

import javax.swing.JOptionPane;

import Model.AppleModel;
import Model.BarrierModel;
import Model.BulletModel;
import Model.DatabaseConnectionModel;
import Model.GameSoundModel;
import Model.Logic;
import Model.OpponentModel;
import Model.SnakeHeadModel;
import Model.SnakeTailModel;
import Model.StatusbarModel;
import Model.Interface.Difficulty;
import Model.Interface.Direction;
import Model.Interface.IActor;
import Model.Interface.IConstants;
import Model.Interface.IPlayerBone;
import Properties.Player;
import View.AppleView;
import View.BarrierView;
import View.BulletView;
import View.GamePanelView;
import View.KeyListenerView;
import View.MainView;
import View.MenuView;
import View.OpponentView;
import View.SnakeHeadView;
import View.SnakeTailView;
import View.SpriteView;
import View.StatusbarView;

/**
 * MainController Singelton zum Steuern des Spieles.
 */
public class MainController {
	/*
	 * Variablen.
	 */
	private static MainController MAIN_CONTROLLER_INSTANCE;
	private GamePanelView gamePanelView;
	private Logic logic;
	private SnakeHeadModel snakeHeadModel;
	private StatusbarView statusbar;
	private StatusbarModel statusbarModel;
	private Player player;
	private DatabaseConnectionModel databaseConnectionModel;
	private boolean isGameStarted;
	private GameSoundModel gameSoundBackground;
	private KeyListenerView keyListenerView;

	/**
	 * Private Konstruktor. Wird aufgerufen, solange die Instanz des
	 * MainController == null ist. Des Weiteren verhindert dieser Konstruktor,
	 * dass eigenständige Objekte (MainController) erzeugt werden können
	 */
	private MainController() {
		initializeGame();
	}

	/**
	 * Initialisiert das Spiel
	 */
	private void initializeGame() {
		databaseConnectionModel = new DatabaseConnectionModel();
		/*
		 * Fülle die Properties des Players, damit gespielt werden kann Dabei
		 * wird der letzte Spieler aus der INI-Datei ausgelesen und verwendet
		 */
		player = databaseConnectionModel.getSinglePlayer(OptionsController
				.getInstance().getOption("player"));
		/*
		 * Ist der Player null, weil es zum Beispiel noch keinen gibt, erzeuge
		 * ihn!
		 */
		if (player == null) {
			boolean created = false;
			String playerName = null;
			/*
			 * Solange created != true
			 */
			while (!created) {
				/*
				 * Erzeuge Input-Dialog und passe den Wert weiter in den String
				 * "playerName"
				 */
				playerName = JOptionPane
						.showInputDialog("Spielernamen angeben!");
				/*
				 * Prüfe, ob playerName null oder leer ist Wenn nicht wird
				 * versucht den Spieler zu erzeugen Das klappt auch nur, wenn
				 * der Spielername noch nicht verwendet wurde!
				 */
				if (playerName != null && !playerName.isEmpty()) {
					created = databaseConnectionModel.createPlayer(playerName);
				}
			}
			/*
			 * Hole nun den erzeugten Spieler und setze die Properties. Außerdem
			 * wird der Spieler nun in die INI-Datei geschrieben!
			 */
			player = databaseConnectionModel.getSinglePlayer(playerName);
			OptionsController.getInstance().setOption("player",
					player.getPlayerName());
			/*
			 * Sichere die Optionen in der INI-Datei.
			 */
			try {
				OptionsController.getInstance().storeOptions();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		/*
		 * Erzeuge neues GameSoundModel-Objekt für die Hintergrundmusik und
		 * spiele sie ab Erzeuge die GUI Erzeuge das Logik-Objekt und starte den
		 * Thread
		 */
		gameSoundBackground = new GameSoundModel(IConstants.GAME_SOUND_PATH);
		gameSoundBackground.playSound();
		createWindow();
		logic = new Logic();
		logic.addObserver(gamePanelView);
		Thread t = new Thread(logic);
		t.start();
	}

	/**
	 * Methode zum Zurückgeben der Instanz vom MainController
	 * 
	 * @return Die Instanz der Klasse
	 */
	public static MainController getInstance() {
		/*
		 * Erzeuge neue Instanz, wenn der MainController == null ist Weiteres
		 * Erzeugen ist nicht möglich!
		 */
		if (MAIN_CONTROLLER_INSTANCE == null) {
			MAIN_CONTROLLER_INSTANCE = new MainController();
		}
		return MAIN_CONTROLLER_INSTANCE;
	}

	/**
	 * Main-Funktion Erzeugt das Objekt, während im Konstruktor der Klasse die
	 * eigentliche Logik ausgeführt wird
	 * 
	 * @param args
	 *            Array mit Argumenten für das Programm (wird nicht benötigt)
	 */
	public static void main(String[] args) {
		MainController.getInstance();
	}

	/**
	 * Methode zum Finden eines Actors
	 * 
	 * @param actor
	 *            Der Actor nach dem gesucht werden soll
	 * @return Der Acotr welcher sich im Logik-Objekt befindet
	 */
	public IActor getActor(IActor actor) {
		return logic.getActor(actor);
	}

	/**
	 * Erzeuge die GUI bestehend aus dem GamePanel, der Statusbar und dem
	 * eigentlichen Frame.
	 */
	private void createWindow() {
		keyListenerView = new KeyListenerView();
		gamePanelView = new GamePanelView(800, 600);
		statusbarModel = new StatusbarModel(player);
		statusbar = new StatusbarView();
		statusbarModel.addObserver(statusbar);
		new MainView(gamePanelView, statusbar);
		statusbarModel.updateStatus();
		/*
		 * Zeige den Titel des Spiels an!
		 */
		MenuView title = new MenuView(50, 50, gamePanelView, "ULTIMATE-SNAKE");
		/*
		 * Alle MenuViews sind Actor Sie werden dem GamePanel hinzugefügt, damit
		 * sie auch wieder entfernt werden können, wenn es notwendig ist
		 */
		gamePanelView.addActor(title);
	}

	/**
	 * Funktion zum Ändern der Richtung der Schlange
	 * 
	 * @param direction
	 *            Die neue Richtung in welche die Schalnge laufen soll
	 */
	public void switchSnakeDirection(Direction direction) {
		snakeHeadModel.rotateSnake(direction);
	}

	/**
	 * Methode, die den Spieler erzeugen soll Gibt false zurück, wenn der
	 * Spieler nicht erzeugt wurde!
	 * 
	 * @param playerName
	 *            Der Name des neuen Spielers
	 * @return Wahrheitswert, welcher angibt, ob das Erstellen des Spielers
	 *         erfolgreich war
	 * @throws IOException
	 *             Wenn beim Speichern des Spielernamens in die INI-Datei ein
	 *             Fehler aufgetreten ist, dann wird dieser Fehler geworfen
	 */
	public boolean createPlayer(String playerName) throws IOException {
		boolean created = databaseConnectionModel.createPlayer(playerName);
		if (created) {
			/*
			 * Setze alle neuen Werte des erzeugten Spieler in ein neues Objekt!
			 * "newPlayer"
			 */
			Player newPlayer = databaseConnectionModel
					.getSinglePlayer(playerName);
			player.setPlayerName(newPlayer.getPlayerName());
			player.setPlayerId(newPlayer.getPlayerId());
			player.setScore(newPlayer.getScore());
			player.setHighscore(newPlayer.getHighscore());
			statusbarModel.updateStatus();
			/*
			 * Sicherere die Apassungen im INI-File
			 */
			OptionsController.getInstance().setOption("player",
					player.getPlayerName());
			OptionsController.getInstance().storeOptions();
		}
		return created;
	}

	/**
	 * Starte das Spiel!
	 */
	public void startGame() {
		/*
		 * Nur wenn die boolesche Variable false ergibt, werden alle Objekete
		 * usw. neu erzeugt Ansonsten wird das Spiel einfach fortgesetzt, wenn
		 * es zum Beispiel pausiert wurde
		 */
		if (!isGameStarted) {
			isGameStarted = true;
			/*
			 * Lösche alle Actors vom GamePanel und aus der Logik
			 */
			gamePanelView.clearActors();
			logic.clearActors();
			/*
			 * Setze Schüsse sowie die aktuellen Punkte zurück auf 0!
			 */
			player.setBulletCount(0);
			player.setScore(0);

			/*
			 * maximale weite und höhe.
			 */
			int maxX = gamePanelView.getWidth();
			int maxY = gamePanelView.getHeight();

			/*
			 * Erzeuge den Apfel! Zuerst das Sprite, dann das Model Regrestriere
			 * das Sprite als Observer im Model! Füge der Logik und den
			 * GamePanel die Actor hinzu
			 */
			AppleView appleView = new AppleView(IConstants.APPLE_PATH, 0, 0,
					gamePanelView);
			AppleModel appleModel = new AppleModel(maxX, maxY, appleView
					.getImage().getWidth(), appleView.getImage().getHeight());
			appleModel.addObserver(appleView);
			logic.addActor(appleModel);
			gamePanelView.addActor(appleView);

			/*
			 * Erzeuge den Kopf der Schlange! Zuerst das Sprite, dann das Model
			 * Regrestriere das Sprite als Observer im Model! Füge der Logik und
			 * den GamePanel die Actor hinzu
			 */
			SnakeHeadView snakeHeadView = new SnakeHeadView(
					IConstants.SNAKE_HEAD_PATH, 120, 120, gamePanelView);
			snakeHeadModel = new SnakeHeadModel(maxX, maxY, 120, 120,
					snakeHeadView.getImage().getWidth(), snakeHeadView
							.getImage().getHeight());
			snakeHeadModel.addObserver(snakeHeadView);
			logic.addActor(snakeHeadModel);
			gamePanelView.addActor(snakeHeadView);

			/*
			 * Erzeuge die Bodyparts der Schlange! Das wird in einer Schleife
			 * gemacht, da wir zu beginn immer den Kopf sowie drei Körperteil
			 * benötigen! Zuerst das Sprite, dann das Model Regrestriere das
			 * Sprite als Observer im Model! Füge der Logik und den GamePanel
			 * die Actor hinzu
			 */
			IPlayerBone vorgaenger = snakeHeadModel;
			/*
			 * Iteriere, bis alle Bodyparts erzeugt sind Setze dabei jeweils das
			 * zu letzt erzeugte Körperteil als Vorgänger
			 */
			for (int count = 1; count <= 3; count++) {
				SnakeTailView snakeTailView = new SnakeTailView(
						IConstants.SNAKE_TAIL_PATH, 120 - vorgaenger
								.getBounding().getWidth(), 120, gamePanelView);
				SnakeTailModel snakeTailModel = new SnakeTailModel(100, 120,
						vorgaenger, snakeTailView.getImage().getWidth(),
						snakeTailView.getImage().getHeight());
				snakeTailModel.addObserver(snakeTailView);
				logic.addActor(snakeTailModel);
				gamePanelView.addActor(snakeTailView);
				vorgaenger = snakeTailModel;
			}
			/*
			 * setze den letzten Vorgänger
			 */
			snakeHeadModel.setLast(vorgaenger);
		}
		/*
		 * Füge den GamePanel den Keylistener hinzu. Jedoch nur dann, wenn das
		 * Spiel gestartet ist, um Nullpointer Exceptions zu vermeiden
		 * Aktualisiere die Statusbar und starte das Spiel!
		 */
		gamePanelView.addKeyListener(keyListenerView);
		statusbarModel.updateStatus();
		logic.setGameRunning(true);
	}

	/**
	 * Funktion zum Pausieren des Spiels
	 */
	public void pauseGame() {
		/*
		 * Entferne Keylistener um erneut Exception zu vermeiden bei
		 * Tastenschlägen, die nur während des Spielens benötigt werden Stoppe
		 * die Hintergrundmelodie und pausiere das Spiel
		 */
		gamePanelView.removeKeyListener(keyListenerView);
		logic.setGameRunning(false);
	}

	/**
	 * Diese Funktion startet das Spiel neu!
	 */
	public void restartGame() {
		/*
		 * Wenn aktueller Score größer als Highscore wird dem entsprechend ein
		 * der Highscore in die Datenbank geschrieben!
		 */
		if (player.getScore() > player.getHighscore()) {
			player.setHighscore(player.getScore());
			databaseConnectionModel.updatePlayerScore(player);
		}
		/*
		 * Stoppe die Hintergrundmeldie Stoppe das Spiel Entferne alle
		 * bestehendes Actors im GamePanel und in der Logik Setze die Variable
		 * isGameStarted auf false, damit die Objekte neu erzeugt werden Starte
		 * das Spiel!
		 */
		logic.setGameRunning(false);
		logic.clearActors();
		gamePanelView.clearActors();
		isGameStarted = false;
		startGame();
	}

	/**
	 * Diese Funktion wird ausgeführt, wenn der Spieler stirbt!
	 */
	public void gameOver() {
		/*
		 * Stoppe das Spiel und warte einen Augenblick, bevor der aktuelle Score
		 * gezeichnet wird So hat der Spieler noch kurz Zeit zu sehen, warum er
		 * gestorben ist.
		 */
		logic.setGameRunning(false);
		try {
			Thread.sleep(650);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/*
		 * Entferne Keylistener Stoppe Hintergrundmelodie Entferne alle Actos
		 */
		gamePanelView.removeKeyListener(keyListenerView);
		isGameStarted = false;
		logic.clearActors();
		gamePanelView.clearActors();
		/*
		 * Erzeuge je nach Fall die Titel, die angezeigt werden Wenn der Spieler
		 * ein neuen Highscore erreicht hat, wird ihm das auch mitgeteilt!
		 */
		MenuView gameOverTitle = new MenuView(50, 40, gamePanelView,
				"Game Over");
		gamePanelView.addActor(gameOverTitle);
		/*
		 * Wenn aktueller Score größer als Highscore wird dem entsprechend ein
		 * Titel erzeugt Außerdem wird der Highscore in die Datenbank
		 * geschrieben!
		 */
		if (player.getScore() > player.getHighscore()) {
			MenuView highScoreTitle = new MenuView(50, 50, gamePanelView,
					"Neue Highscore!");
			gamePanelView.addActor(highScoreTitle);
			player.setHighscore(player.getScore());
			databaseConnectionModel.updatePlayerScore(player);
			/*
			 * Wenn der aktuelle Score jedoch kleiner ist als der Highscore,
			 * passiert das nicht und es werden nur die erreichten Punkte
			 * dargestellt!
			 */
		} else {
			MenuView currentScoreTitle = new MenuView(50, 50, gamePanelView,
					"Erreichte Punkte:");
			gamePanelView.addActor(currentScoreTitle);
		}
		MenuView highScoreTitleScore = new MenuView(50, 60, gamePanelView,
				String.valueOf(player.getScore()));
		gamePanelView.addActor(highScoreTitleScore);
	}

	/**
	 * Funktion zum Erhöhen der Punktezahlt
	 */
	public void raiseScore() {
		/*
		 * Setze die Punktezahl hoch Setze die Schüsse hoch Verlängere die
		 * Schlange Aktualisiere die Statusbar und erzeuge gegebenenfalls neue
		 * Gegner und Baumstämme!
		 */
		snakeHeadModel.increaseLength();
		player.setBulletCount(player.getBulletCount() + 2);
		player.setScore(player.getScore() + 2);
		statusbarModel.updateStatus();
		spawnEnemies();
	}

	/**
	 * Funktion zum Prüfen, ob ein neuer Gegner und Baumstamm erzeugt werden
	 * soll
	 */
	private void spawnEnemies() {
		boolean spawnNewEnemy = false;
		int modulo = 0;
		/*
		 * Je nach Schwierigkeitsgrad, wird der Modulowert erhöht Dieser wird
		 * für die Berechnung benötigt, um "Random" Gegner und Baumstämme zu
		 * erzeugen
		 */
		long time = System.currentTimeMillis();
		/*
		 * Gibt den Schwierigkeitsgrad zurück und setzt dem entsprechend einen
		 * Modulowert
		 */
		switch (Difficulty.fromString(OptionsController.getInstance()
				.getOption("difficulty"))) {
		case SIMPLE:
			modulo = 8;
			break;
		case MEDIUM:
			modulo = 4;
			break;
		case DIFFICULT:
			modulo = 2;
			break;
		}
		/*
		 * Nur wenn die Zeit % modulo == 0 ist erzeugen wird neue Gegner Kann
		 * häufiger passieren, muss es aber nicht Jedoch passiert es häufiger,
		 * wenn der Schwierigkeitsgrad höher ist und umgekehrt!
		 */
		if (time % modulo == 0) {
			spawnNewEnemy = true;
		}
		/*
		 * Nur wenn spawnEnemy erzeuge neue Gegner und Baumstämme
		 */
		if (spawnNewEnemy) {
			/*
			 * Erzeuge neue Gegner und Baumstamm Objekte und füge sie der Logik
			 * und dem GamePanel hinzu
			 */
			OpponentView opponentView = new OpponentView(
					IConstants.OPPONENT_PATH, 0, 60, gamePanelView);
			OpponentModel opponentModel = new OpponentModel(
					gamePanelView.getWidth(), gamePanelView.getHeight(),
					opponentView.getImage().getWidth(), opponentView.getImage()
							.getHeight());
			opponentModel.addObserver(opponentView);
			logic.addActor(opponentModel);
			gamePanelView.addActor(opponentView);
			BarrierView barrierView = new BarrierView(IConstants.BARRIER_PATH,
					0, 60, gamePanelView);
			BarrierModel barrierModel = new BarrierModel(
					gamePanelView.getWidth(), gamePanelView.getHeight(),
					barrierView.getImage().getWidth(), barrierView.getImage()
							.getHeight());
			barrierModel.addObserver(barrierView);
			logic.addActor(barrierModel);
			gamePanelView.addActor(barrierView);
		}
	}

	/**
	 * Stellt die Top-Ten Spieler auf dem GamePanel dar.
	 */
	public void displayRanking() {
		/*
		 * Hole die Werte aus der Datenbank
		 */
		Vector<Player> topPlayerVector = databaseConnectionModel
				.getTopPlayers();
		int counter = 1;
		/*
		 * Lösche alle Actors, die noch vorhanden sein mögen
		 */
		logic.clearActors();
		gamePanelView.clearActors();
		/*
		 * Iteriere mit der For-Each Schleife durch den Vector und erzeuge die
		 * Titel bestehend aus Spielername und Highscore!
		 */
		for (Player topPlayer : topPlayerVector) {
			/*
			 * Mit Hilfe des Zählers wird die Position des Titels immer weiter
			 * verschoben.
			 */
			MenuView topPlayerView = new MenuView(50, 9.5 * counter,
					gamePanelView, topPlayer.getHighscore() + " - "
							+ topPlayer.getPlayerName());
			gamePanelView.addActor(topPlayerView);
			counter++;
		}
	}

	/**
	 * Methode zum Erhalten des aktuell ausgewählten Spielernames
	 * 
	 * @return Der Spielername aus dem Player-Objekt.
	 */
	public String getPlayerName() {
		return player.getPlayerName();
	}

	/**
	 * Diese Methode gibt einen Vector mit allen Spielern zurück. Dabei wird
	 * eine Datenbankabfrage ausgeführt. Im Prinzip nur SELECT * FROM TABLE.
	 * 
	 * @return Vector mit Objekten von allen Spielern
	 */
	public Vector<Player> getPlayers() {
		return databaseConnectionModel.getPlayers();
	}

	/**
	 * Ändert den aktuellen Spieler
	 * 
	 * @param playerName
	 *            Der Name des Spielers auf welchen gewechselt werden soll
	 */
	public void changePlayer(String playerName) {
		/*
		 * Erzeuge neues Objekt vom Typ Player durch die Datenbankabfrage Dabei
		 * wird der Spielername verwendet
		 */
		Player newPlayer = databaseConnectionModel.getSinglePlayer(playerName);
		/*
		 * Setze die Daten von dem "newPlayer" Objekt in das bestehende "Player"
		 * Objekt
		 */
		player.setPlayerName(newPlayer.getPlayerName());
		player.setPlayerId(newPlayer.getPlayerId());
		player.setScore(newPlayer.getScore());
		player.setBulletCount(newPlayer.getScore());
		player.setHighscore(newPlayer.getHighscore());
	}

	/**
	 * Wenn Optionen des Spiels geändert werden, wird diese Funktion ausgeführt
	 * 
	 * @throws IOException
	 *             Da diese Funktion andere Funktionen ausführt, die in die
	 *             INI-Datei schreiben
	 */
	public void optionsChanged() throws IOException {
		/*
		 * Aktualisiere die Statusbar und schreibe die Werte
		 */
		statusbarModel.updateStatus();
		OptionsController.getInstance().setOption("player",
				player.getPlayerName());
		OptionsController.getInstance().storeOptions();
	}

	/**
	 * Funktion zum Schießen auf Gegner oder Baumstämme
	 */
	public void shoot() {
		/*
		 * Wenn der Zähler für die Schüsse > 0 ist
		 */
		if (player.getBulletCount() > 0) {
			/*
			 * Aktuelle Schüsse = Schüsse -1 Aktualisiere die Statusbar Erzeuge
			 * das Bullet-Sprite Erzeuge das Bullet-Model Füge den Observer dem
			 * Model hinzu und die Actor dem GamePanel und der Logik.
			 */
			player.setBulletCount(player.getBulletCount() - 1);
			statusbarModel.updateStatus();
			BulletView bulletView = new BulletView(IConstants.BULLET_PATH,
					snakeHeadModel.getBounding().getCenterX(), snakeHeadModel
							.getBounding().getCenterY(), gamePanelView);
			BulletModel bulletModel = new BulletModel(snakeHeadModel
					.getBounding().getCenterX(), snakeHeadModel.getBounding()
					.getCenterY(), bulletView.getImage().getWidth(), bulletView
					.getImage().getHeight(), snakeHeadModel.getDirection());
			bulletModel.addObserver(bulletView);
			logic.addActor(bulletModel);
			gamePanelView.addActor(bulletView);
		}
	}

	/**
	 * Funktion zum Entfernen eines Spriteviews
	 * 
	 * @param spriteView
	 *            Das Spreiteview-Objekt, welches aus dem Spiel entfernt werden
	 *            soll
	 */
	public void removeSpriteView(SpriteView spriteView) {
		gamePanelView.removeActor(spriteView);
	}

	/**
	 * Funktion zum Entfernen eines Actors
	 * 
	 * @param actor
	 *            Das IActor-Objekt, welches aus dem Spiel entfernt werden soll
	 */
	public void removeActor(IActor actor) {
		logic.removeActor(actor);
	}

	/**
	 * Diese Methode gibt einen Actor zurück, der sich an einer bestimmten
	 * Position befindet
	 *
	 * @param x
	 *            Die x-Position des Actors
	 * @param y
	 *            Die y-Position des Actors
	 * @param width
	 *            Die Breite des Actors
	 * @param height
	 *            Die Höhe des Actors
	 * @return Der Actor an der bestimmten Stelle, wenn keiner gefunden wurde,
	 *         dann wird null zurückgegeben
	 */
	public IActor getActorAt(double x, double y, double width, double height) {
		return logic.getAtPosition(x, y, width, height);
	}

	/**
	 * Erzeugt ein neues Schlangenteil
	 * 
	 * @param x
	 *            Die x-Position des neuen Schlangenteils
	 * @param y
	 *            Die y-Position des neuen Schlangenteils
	 * @param vorgaenger
	 *            Das vorangehende Körperteil der Schalnge
	 * @return Das Model-Objekt des neu erzeugten Körperteils der Schlange
	 */
	public IPlayerBone createSnakeTail(int x, int y, IPlayerBone vorgaenger) {
		SnakeTailView newTailView = new SnakeTailView(
				IConstants.SNAKE_TAIL_PATH, x, y, gamePanelView);
		SnakeTailModel newTailModel;
		newTailModel = new SnakeTailModel(x, y,
				(IPlayerBone) getActor(vorgaenger), newTailView.getImage()
						.getWidth(), newTailView.getImage().getHeight());
		newTailModel.addObserver(newTailView);
		gamePanelView.addActor(newTailView);
		logic.addActor(newTailModel);
		return newTailModel;
	}

	/**
	 * Gibt den Wert der Variable zurück
	 * 
	 * @return Wahrheitswert welcher angibt, ob das Spiel gestartet wurde oder
	 *         nicht.
	 */
	public boolean getIsGameStarted() {
		return this.isGameStarted;
	}

}