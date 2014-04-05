package Model.Interface;

import java.awt.geom.Point2D;
import java.util.Vector;

public interface IPlayer {
	double getX();
	double getY();
	Direction getDirection();
	Vector<Point2D.Double> getBonesPosition();
}
