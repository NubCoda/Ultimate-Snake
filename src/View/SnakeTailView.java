package View;

@SuppressWarnings("serial")
public class SnakeTailView extends SpriteView{
	public SnakeTailView(String path, double x, double y,
			GamePanelView gamePanelView) {
		super(path, x, y, gamePanelView);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean collidedWith(SpriteView spriteView) {
		if(this.intersects(spriteView)){
//			System.out.println("kollision snaketail");
			return true;
		}
		return false;
	}

}
