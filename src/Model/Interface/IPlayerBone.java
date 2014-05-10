package Model.Interface;

import java.awt.geom.Point2D;

public interface IPlayerBone {
	Direction getDirection();
	double getX();
	double getY();
	Point2D.Double getMovement();
}
