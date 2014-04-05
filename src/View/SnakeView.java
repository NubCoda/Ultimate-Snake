package View;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import Model.Interface.Direction;
import Model.Interface.IConstants;
import Model.Interface.IPlayer;

public class SnakeView implements Observer {
	private SnakeHeadView snakeHeadView;
	private Vector<SnakeTailView> tails;
	private GamePanelView gamePanelView;

	public SnakeView(double x, double y, GamePanelView gamePanelView, Vector<Point2D.Double> bonesPoints, Direction direction){
		this.gamePanelView = gamePanelView;
		this.snakeHeadView = new SnakeHeadView(IConstants.SNAKE_HEAD_PAHT, x, y, gamePanelView, direction);
		this.tails = new Vector<SnakeTailView>();
		for (Point2D.Double point : bonesPoints) {
			tails.add(new SnakeTailView(IConstants.SNAKE_TAIL_PAHT, point.x, point.y, gamePanelView));
		}
	}

	public Vector<SpriteView> getActors(){
		Vector<SpriteView> actors = new Vector<SpriteView>();
		actors.add(snakeHeadView);
		actors.addAll(tails);
		return actors;
	}

	@Override
	public void update(Observable observable, Object argObject) {
		IPlayer snake = ((IPlayer) observable);
		snakeHeadView.x = snake.getX();
		snakeHeadView.y = snake.getY();
		snakeHeadView.setDirection(snake.getDirection());
		Vector<Point2D.Double> bonesPos = snake.getBonesPosition();
		int size = this.tails.size();
		for (int i = 0; i < bonesPos.size(); i++) {
			if(i<size){
				tails.get(i).x = bonesPos.get(i).x;
				tails.get(i).y = bonesPos.get(i).y;
			} else {
				this.tails.add(new SnakeTailView(IConstants.SNAKE_TAIL_PAHT, bonesPos.get(i).x, bonesPos.get(i).y, gamePanelView));
			}

//			if(tails.containsKey(point)){
//				this.tails.get(point).x = bonesPos.getX();
//				this.tails.get(point).y = bonesPos.getY();
//			} else {
//				this.tails.put(point, new SnakeTailView(IConstants.SNAKE_TAIL_PAHT, point.x, point.y, gamePanelView));
//			}
		}
	}
}