package View;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import View.Interface.IDrawable;

@SuppressWarnings("serial")
public abstract class SpriteView implements IDrawable {
	protected GamePanelView gamePanelView;
	protected BufferedImage bufferedImage;
	protected double x;
	protected double y;

	public SpriteView(String path, double x, double y,
			GamePanelView gamePanelView) {
		bufferedImage = loadImage(path);
		this.x = x;
		this.y = y;
		this.gamePanelView = gamePanelView;
	}

	protected BufferedImage loadImage(String path) {
		BufferedImage bufferedImage = null;
		if(path!=null && !path.isEmpty()){
			try {
				bufferedImage = ImageIO.read(new File(path));
			} catch (IOException e) {
				e.printStackTrace();
			}
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
		if(bufferedImage != null){
			graphics.drawImage(bufferedImage, (int) x, (int) y, null);
		}
	}
	
	public BufferedImage getImage(){
		return bufferedImage;
	}
}
