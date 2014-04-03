package View;

@SuppressWarnings("serial")
public class SnakeHeadView extends SpriteView{
	public SnakeHeadView(String path, double x, double y,
			GamePanelView gamePanelView) {
		super(path, x, y, gamePanelView);
	}

	@Override
	public boolean collidedWith(SpriteView spriteView) {
		if(this.intersects(spriteView)){
			System.out.println("kollision snakehead");
			return true;
		}
		return false;
	}
}
