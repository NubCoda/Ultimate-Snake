package Controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.KeyStroke;

import Model.AppleModel;
import Model.Logic;
import Model.SnakeHeadModel;
import Model.SnakeTailModel;
import Model.Interface.Direction;
import Model.Interface.IActor;
import Model.Interface.IConstants;
import Model.Interface.IPlayerBone;
import View.AppleView;
import View.GamePanelView;
import View.MainView;
import View.SnakeHeadView;
import View.SnakeTailView;
import View.SpriteView;

public class MainController {
	private static MainController mainController;
	private MainView mainView;
	private GamePanelView gamePanelView;
	private SnakeHeadModel snakeHeadModel;
	private Logic logic;
	private boolean hasGameStarted = false;
	
	private MainController() {
		createWindow();
		logic = new Logic();
		logic.addObserver(gamePanelView);
		Thread t = new Thread(logic);
		t.start();
		startGame();
	}

	public static MainController getInstance() {
		if (mainController == null) {
			mainController = new MainController();
		}
		return mainController;
	}
	
	public IActor getActor(IActor actor){
		return logic.getActor(actor);
	}
	
	public void addActor(IActor actor) {
		logic.addActor(actor);
	}

	public void addViewActor(SpriteView actor) {
		gamePanelView.addActor(actor);
	}

	private void createWindow() {
		gamePanelView = new GamePanelView();
		mainView = new MainView(gamePanelView);
		gamePanelView.registerKeyboardAction(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				logic.kill();
				for(Frame frame : Frame.getFrames()){
					frame.dispose();
				}
				
//				System.exit(0);
			}
		},  KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
	}

	/**
	 * Main Funktion Gezeichnet wird nur, wenn der Benutzer das "Spiel"
	 * gestartet hat
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MainController.getInstance();
	}

	public void switchSnakeDirection(Direction direction) {
		snakeHeadModel.rotateSnake(direction);
	}

	public void startGame() {
		// TODO: das Level nur beim Start bzw. beim Levelwechsel erstellen
		if (!hasGameStarted) {
			AppleView appleView = new AppleView(IConstants.APPLE_PAHT, 20, 20,
					gamePanelView);
			SnakeHeadView snakeHeadView = new SnakeHeadView(IConstants.SNAKE_HEAD_PAHT, 120, 120, gamePanelView);
			snakeHeadModel = new SnakeHeadModel(gamePanelView, 120, 120, snakeHeadView.getImage());
			SnakeTailView snakeTailView = new SnakeTailView(IConstants.SNAKE_TAIL_PAHT, 100, 120, gamePanelView);
			SnakeTailModel snakeTailModel = new SnakeTailModel(gamePanelView, 100, 120, snakeHeadModel, snakeTailView.getImage());
			SnakeTailView snakeTailView1 = new SnakeTailView(IConstants.SNAKE_TAIL_PAHT, 80, 120, gamePanelView);
			SnakeTailModel snakeTailModel1 = new SnakeTailModel(gamePanelView, 80, 120, snakeTailModel, snakeTailView1.getImage());
			SnakeTailView snakeTailView2 = new SnakeTailView(IConstants.SNAKE_TAIL_PAHT, 60, 120, gamePanelView);
			SnakeTailModel snakeTailModel2 = new SnakeTailModel(gamePanelView, 60, 120, snakeTailModel1, snakeTailView2.getImage());
			snakeHeadModel.setLast(snakeTailModel2);
			AppleModel appleModel = new AppleModel(gamePanelView, appleView.getImage());
			logic.addActor(appleModel);
			logic.addActor(snakeHeadModel);
			logic.addActor(snakeTailModel);
			logic.addActor(snakeTailModel1);
			logic.addActor(snakeTailModel2);
			appleModel.addObserver(appleView);
			snakeHeadModel.addObserver(snakeHeadView);
			snakeTailModel.addObserver(snakeTailView);
			snakeTailModel1.addObserver(snakeTailView1);
			snakeTailModel2.addObserver(snakeTailView2);
			gamePanelView.addActor(appleView);
			gamePanelView.addActor(snakeHeadView);
			gamePanelView.addActor(snakeTailView);
			gamePanelView.addActor(snakeTailView1);
			gamePanelView.addActor(snakeTailView2);
			hasGameStarted = true;
		}
		logic.setGameRunning(true);

	}

	public void pauseGame() {
		logic.setGameRunning(false);
	}

	public void restartGame() {

	}
}
