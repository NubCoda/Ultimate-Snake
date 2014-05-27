package Controller;

import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.KeyStroke;

import Model.AppleModel;
import Model.Logic;
import Model.MenuModel;
import Model.OpponentModel;
import Model.SnakeHeadModel;
import Model.SnakeTailModel;
import Model.Interface.Direction;
import Model.Interface.IActor;
import Model.Interface.IConstants;
import Model.Interface.IMenu;
import View.AppleView;
import View.GamePanelView;
import View.MainView;
import View.MenuView;
import View.OpponentView;
import View.SnakeHeadView;
import View.SnakeTailView;
import View.SpriteView;

/**
 *
 *
 */
public class MainController {
	private static MainController MAIN_CONTROLLER_INSTANCE;
	private GamePanelView gamePanelView;
	private Logic logic;
	private SnakeHeadModel snakeHeadModel;
	private IMenu oldMenu = null;

	/**
	 * 
	 */
	private MainController() {
		createWindow();
		logic = new Logic();
		logic.addObserver(gamePanelView);
		Thread t = new Thread(logic);
		t.start();

		// TODO: dies ist das Level Menu
		MenuView title = new MenuView(50, 10, gamePanelView, "SNAKE", 48.0f);
		MenuView spielStarten = new MenuView(50, 30, gamePanelView,
				"Spiel starten", 48.0f);
		MenuModel spMenuModel = new MenuModel(gamePanelView,
				spielStarten.getX(), spielStarten.getY(),
				spielStarten.getHeight(), spielStarten.getWidth(),
				"Spiel starten");
		MenuView optionen = new MenuView(50, 50, gamePanelView, "Optionen",
				48.0f);
		MenuModel optionenMenuModel = new MenuModel(gamePanelView,
				optionen.getX(), optionen.getY(), optionen.getHeight(),
				optionen.getWidth(), "Optionen");
		MenuView beenden = new MenuView(50, 70, gamePanelView, "Beenden", 48.0f);
		MenuModel beendenMenuModel = new MenuModel(gamePanelView,
				beenden.getX(), beenden.getY(), beenden.getHeight(),
				beenden.getWidth(), "Beenden");
		MenuView spieler = new MenuView(95, 98, gamePanelView, "Spieler: ",
				48.0f);
		MenuModel spielerMenuModel = new MenuModel(gamePanelView,
				spieler.getX(), spieler.getY(), spieler.getHeight(),
				spieler.getWidth(), "Spieler: ");
		MenuView highScore = new MenuView(7, 98, gamePanelView, "Highscore: ",
				48.0f);
		MenuModel highScoreMenuModel = new MenuModel(gamePanelView,
				highScore.getX(), highScore.getY(), highScore.getHeight(),
				highScore.getWidth(), "Highscore: ");
		logic.addActor(spMenuModel);
		logic.addActor(optionenMenuModel);
		logic.addActor(beendenMenuModel);
		logic.addActor(spielerMenuModel);
		logic.addActor(highScoreMenuModel);
		spMenuModel.addObserver(spielStarten);
		optionenMenuModel.addObserver(optionen);
		beendenMenuModel.addObserver(beenden);
		spielerMenuModel.addObserver(spieler);
		highScoreMenuModel.addObserver(highScore);
		gamePanelView.addActor(title);
		gamePanelView.addActor(spielStarten);
		gamePanelView.addActor(optionen);
		gamePanelView.addActor(beenden);
		gamePanelView.addActor(spieler);
		gamePanelView.addActor(highScore);
	}

	/**
	 * 
	 * @return
	 */
	public static MainController getInstance() {
		if (MAIN_CONTROLLER_INSTANCE == null) {
			MAIN_CONTROLLER_INSTANCE = new MainController();
		}
		return MAIN_CONTROLLER_INSTANCE;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MainController.getInstance();
	}

	/**
	 * 
	 * @param actor
	 * @return
	 */
	public IActor getActor(IActor actor) {
		return logic.getActor(actor);
	}

	/**
	 * 
	 */
	private void createWindow() {
		gamePanelView = new GamePanelView(1280, 720);
		new MainView(gamePanelView);

		// TODO - passend auslagern
		// - Fuer pause und neustarten passende KeyEvents festlegen
		gamePanelView.registerKeyboardAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				logic.kill();
				for (Frame frame : Frame.getFrames()) {
					frame.dispose();
				}
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
	}

	/**
	 * 
	 * @param direction
	 */
	public void switchSnakeDirection(Direction direction) {
		snakeHeadModel.rotateSnake(direction);
	}

	/**
	 * 
	 * @param point
	 */
	public void mouseMove(Point point) {
		IActor actor = logic.checkMouse(point);
		if (actor != null) {
			((MenuModel) actor).focus();
			if (oldMenu != null && !oldMenu.equals(actor)) {
				oldMenu.defocus();
			}
		} else if (oldMenu != null) {
			oldMenu.defocus();
		}
		oldMenu = ((MenuModel) actor);
	}

	/**
	 * 
	 * @param point
	 */
	public void mouseClick(Point point) {
		IActor actor = logic.checkMouse(point);
		if (actor != null) {
			String menuText = ((MenuModel) actor).getText();
			if (menuText.equals("Spiel starten")) {
				gamePanelView.clearActors();
				logic.clearActors();
				startGame();
			} else if (menuText.equals("Optionen")) {

			} else if (menuText.equals("Beenden")) {
				logic.kill();
				for (Frame frame : Frame.getFrames()) {
					frame.dispose();
				}
			}
		}
	}

	/**
	 * 
	 */
	public void startGame() {
		// TODO: dies koennte das Level 1 sein
		AppleView appleView = new AppleView(IConstants.APPLE_PAHT, 0, 0,
				gamePanelView);
		SnakeHeadView snakeHeadView = new SnakeHeadView(
				IConstants.SNAKE_HEAD_PAHT, 120, 120, gamePanelView);
		snakeHeadModel = new SnakeHeadModel(gamePanelView, 120, 120,
				snakeHeadView.getImage(), logic);
		SnakeTailView snakeTailView = new SnakeTailView(
				IConstants.SNAKE_TAIL_PAHT, 100, 120, gamePanelView);
		SnakeTailModel snakeTailModel = new SnakeTailModel(gamePanelView, 100,
				120, snakeHeadModel, snakeTailView.getImage());
		SnakeTailView snakeTailView1 = new SnakeTailView(
				IConstants.SNAKE_TAIL_PAHT, 80, 120, gamePanelView);
		SnakeTailModel snakeTailModel1 = new SnakeTailModel(gamePanelView, 80,
				120, snakeTailModel, snakeTailView1.getImage());
		SnakeTailView snakeTailView2 = new SnakeTailView(
				IConstants.SNAKE_TAIL_PAHT, 60, 120, gamePanelView);
		SnakeTailModel snakeTailModel2 = new SnakeTailModel(gamePanelView, 60,
				120, snakeTailModel1, snakeTailView2.getImage());
		snakeHeadModel.setLast(snakeTailModel2);
		AppleModel appleModel = new AppleModel(gamePanelView,
				appleView.getImage());
		OpponentView opponentView1 = new OpponentView(IConstants.OPPONENT_PATH, 0, 60, gamePanelView);
		OpponentModel opponentModel1 = new OpponentModel(gamePanelView, 0, 60, opponentView1.getImage(), logic);
		opponentModel1.addObserver(opponentView1);
			
		appleModel.addObserver(appleView);
		snakeHeadModel.addObserver(snakeHeadView);
		snakeTailModel.addObserver(snakeTailView);
		snakeTailModel1.addObserver(snakeTailView1);
		snakeTailModel2.addObserver(snakeTailView2);
		logic.addActor(appleModel);
		logic.addActor(snakeHeadModel);
		logic.addActor(snakeTailModel);
		logic.addActor(snakeTailModel1);
		logic.addActor(snakeTailModel2);
		logic.addActor(opponentModel1);
		gamePanelView.addActor(appleView);
		gamePanelView.addActor(snakeHeadView);
		gamePanelView.addActor(snakeTailView);
		gamePanelView.addActor(snakeTailView1);
		gamePanelView.addActor(snakeTailView2);
		gamePanelView.addActor(opponentView1);
		logic.setGameRunning(true);

	}

	/**
	 * 
	 */
	public void pauseGame() {
		logic.setGameRunning(false);
	}

	/**
	 * 
	 */
	public void restartGame() {

	}
}