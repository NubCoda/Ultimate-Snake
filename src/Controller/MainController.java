package Controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.KeyStroke;

import Model.AppleModel;
import Model.Logic;
import Model.OpponentModel;
import Model.SnakeHeadModel;
import Model.SnakeTailModel;
import Model.Interface.Direction;
import Model.Interface.IActor;
import Model.Interface.IConstants;
import View.AppleView;
import View.GamePanelView;
import View.MainView;
import View.MenuView;
import View.OpponentView;
import View.SnakeHeadView;
import View.SnakeTailView;

/**
 *
 *
 */
public class MainController {
	private static MainController MAIN_CONTROLLER_INSTANCE;
	private GamePanelView gamePanelView;
	private Logic logic;
	private SnakeHeadModel snakeHeadModel;

	/**
	 * 
	 */
	private MainController() {
		createWindow();
		logic = new Logic();
		logic.addObserver(gamePanelView);
		Thread t = new Thread(logic);
		t.start();

		MenuView title = new MenuView(50, 50, gamePanelView, "SNAKE", 48.0f);
		gamePanelView.addActor(title);
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
		gamePanelView = new GamePanelView(800, 600);
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
				IConstants.SNAKE_TAIL_PATH, 100, 120, gamePanelView);
		SnakeTailModel snakeTailModel = new SnakeTailModel(gamePanelView, 100,
				120, snakeHeadModel, snakeTailView.getImage());
		SnakeTailView snakeTailView1 = new SnakeTailView(
				IConstants.SNAKE_TAIL_PATH, 80, 120, gamePanelView);
		SnakeTailModel snakeTailModel1 = new SnakeTailModel(gamePanelView, 80,
				120, snakeTailModel, snakeTailView1.getImage());
		SnakeTailView snakeTailView2 = new SnakeTailView(
				IConstants.SNAKE_TAIL_PATH, 60, 120, gamePanelView);
		SnakeTailModel snakeTailModel2 = new SnakeTailModel(gamePanelView, 60,
				120, snakeTailModel1, snakeTailView2.getImage());
		snakeHeadModel.setLast(snakeTailModel2);
		AppleModel appleModel = new AppleModel(gamePanelView,
				appleView.getImage());
		OpponentView opponentView1 = new OpponentView(IConstants.OPPONENT_PATH, 0, 60, gamePanelView);
		OpponentModel opponentModel1 = new OpponentModel(gamePanelView, opponentView1.getImage(), logic);
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