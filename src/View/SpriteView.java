package View;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import View.Interface.IDrawable;

public abstract class SpriteView implements IDrawable {
	protected BufferedImage bufferedImage;
	protected double x;
	protected double y;

	public SpriteView(String path, double x, double y,
			GamePanelView gamePanelView) {
		bufferedImage = loadImage(path);
		this.x = x;
		this.y = y;
	}

	protected BufferedImage loadImage(String path) {
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bufferedImage;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ViewInterface.IDrawable#drawObjects(java.awt.Graphics)
	 */
	@Override
	public void drawObjects(Graphics graphics) {
		graphics.drawImage(bufferedImage, (int) x, (int) y, null);
	}
	
	public BufferedImage getImage(){
		return bufferedImage;
	}
}
