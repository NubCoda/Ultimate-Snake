package Model.Interface;

import java.awt.Point;
import java.util.Vector;

public interface IPlayer {
	double getX();
	double getY();
	Direction getDirection();
	Vector<Point> getBonesPosition();
}
