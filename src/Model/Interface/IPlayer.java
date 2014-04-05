package Model.Interface;

import java.awt.geom.Point2D;
import java.util.Map;

public interface IPlayer {
	double getX();
	double getY();
	Direction getDirection();
	Map<Point2D.Double, Point2D.Double> getBonesPosition();
}
