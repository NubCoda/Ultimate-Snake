package View;

import java.util.Observable;
import java.util.Observer;

public class SnakeView implements Observer {
	public SnakeView(){

	}

	@Override
	public void update(Observable observable, Object argObject) {

	}
}

/* ALT */
//private BufferedImage head;
//private BufferedImage tail;
//private int length = 20;
//private int currentDirection = IConstants.RIGHT;
//private Map<Point, Integer> drawDirections;
//private GamePanelView gamePanelView;

//public SnakeView(String pathHead, String pathTail, double x, double y,
//		GamePanelView gamePanelView) {
//	super(pathHead, x, y, gamePanelView);
//	this.gamePanelView = gamePanelView;
//	head = loadImage(pathHead);
//	tail = loadImage(pathTail);
//	drawDirections = new HashMap<Point, Integer>();
//	width = 20;
//	height = 20;
//}

//@Override
////public void drawObjects(Graphics graphics) {
//Graphics2D g2 = (Graphics2D) graphics;
//AffineTransform oldTransorfm = g2.getTransform();
//AffineTransform at = new AffineTransform();
//int rotation = 0;
//if (currentDirection == IConstants.UP) {
//	rotation = -90;
//} else if (currentDirection == IConstants.DOWN) {
//	rotation = 90;
//} else if (currentDirection == IConstants.LEFT) {
//	rotation = 180;
//}
//at.rotate(Math.toRadians(rotation), (int) x + (10),
//		(int) y + (10));
//g2.transform(at);
//g2.drawImage(head, (int) x, (int) y, null);
//g2.setTransform(oldTransorfm);
//g2.setColor(Color.red);
//g2.drawRect((int)x,(int) y,(int) 20, (int)20);
//int drawX = (int) x;
//int drawY = (int) y;
//int curDir = currentDirection;
//for (int i = 1; i <= length; i++) {
//	Point curPosition = new Point(drawX, drawY);
//	if (drawDirections.containsKey(curPosition)) {
//		curDir = drawDirections.get(curPosition);
//	}
//	switch (curDir) {
//	case IConstants.RIGHT:
//		drawX -= 20;
//		break;
//	case IConstants.LEFT:
//		drawX += 20;
//		break;
//	case IConstants.UP:
//		drawY += 20;
//		break;
//	case IConstants.DOWN:
//		drawY -= 20;
//		break;
//	}
//	g2.drawImage(tail, drawX, drawY, null);
//}
//}

//@Override
//public boolean collidedWith(SpriteView spriteView) {
//if(this.intersects(spriteView)){
//	System.out.println("kollision snake");
//	return true;
//}
//return false;
//}

//this.x = ((SnakeModel) observable).getX();
//this.y = ((SnakeModel) observable).getY();
//drawDirections = ((SnakeModel) observable).getDrawDirections();
//currentDirection = ((SnakeModel) observable).getDirection();