package View;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;

import Model.Interface.Direction;
import Model.Interface.IActor;
import Model.Interface.IConstants;
import Model.Interface.IElement;

@SuppressWarnings("serial")
public class OpponentView extends SpriteView implements Observer {
	
	private int rotation;
	private Direction direction;
	
	public OpponentView(String path, double x, double y,
			GamePanelView gamePanelView) {
		super(path, x, y, gamePanelView);
	}

	@Override
	public void update(Observable observable, Object argObject) {
		IActor opponent = ((IActor) observable);
		this.x = opponent.getBounding().getX();
		this.y = opponent.getBounding().getY();
	}

}
