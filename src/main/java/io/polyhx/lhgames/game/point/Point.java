////////////////////////////////////////////
//         DO NOT TOUCH THIS FILE         //
////////////////////////////////////////////

package io.polyhx.lhgames.game.point;

/**
 * This class provides a default implementation for the IPoint interface.
 */
public class Point implements IPoint {
    /**
     * The X coordinate of the point.
     */
    private final int fX;

    /**
     * The Y coordinate of the point.
     */
    private final int fY;

    /**
     * Default constructor.
     */
    public Point() {
        fX = 0;
        fY = 0;
    }

    /**
     * Constructor by values.
     *
     * @param x The X coordinate of the point.
     * @param y The Y coordinate of the point.
     */
    public Point(int x, int y) {
        fX = x;
        fY = y;
    }

    /**
     * Copy constructor.
     *
     * @param point The point we want to copy.
     */
    public Point(IPoint point) {
        fX = point.getX();
        fY = point.getY();
    }

    /**
     * This point defines a direction going up.
     */
    public static final Point UP = new Point(0, -1);

    /**
     * This point defines a direction going down.
     */
    public static final Point DOWN = new Point(0, 1);

    /**
     * This point defines a direction going left.
     */
    public static final Point LEFT = new Point(-1, 0);

    /**
     * This point defines a direction going right.
     */
    public static final Point RIGHT = new Point(1, 0);

    /**
     * This method returns the X coordinate of the point.
     *
     * @return The X coordinate of the point.
     */
    public int getX() {
        return fX;
    }

    /**
     * This method returns the Y coordinate of the point.
     *
     * @return The Y coordinate of the point.
     */
    public int getY() {
        return fY;
    }
}
