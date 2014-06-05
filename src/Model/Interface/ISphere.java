package Model.Interface;

/**
 * 
 * 
 */
public interface ISphere extends IActor {
	/**
	 * 
	 * @return
	 */
	Direction getDirection();

	public boolean isGone();

	void setGone(boolean b);
}
