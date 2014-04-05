package Model.Interface;

import java.awt.Point;
import java.util.Map;

public interface IPlayer {
	double getX();
	double getY();
	Direction getDirection();
	Map<Point, Point> getBonesPosition();
}
