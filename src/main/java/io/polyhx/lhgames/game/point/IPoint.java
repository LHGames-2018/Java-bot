package io.polyhx.lhgames.game.point;

/**
 * This interface provides a 2D point from the implemented object.
 */
public interface IPoint {
    /**
     * This method returns the X coordinate.
     *
     * @return The X coordinate.
     */
    int getX();

    /**
     * This method returns the Y coordinate.
     *
     * @return The Y coordinate.
     */
    int getY();

    /**
     * This method returns the pythagorean distance to another point.
     *
     * @param point The other point we want the distance from.
     * @return The pythagorean distance to the other point.
     */
    default double getDistanceTo(IPoint point) {
        double dx = getX() - point.getX();
        double dy = getY() - point.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * This method returns the JSON data of this point.
     * @return
     */
    default String toJSON() {
        return String.format("{X:%d,Y:%d}", getX(), getY());
    }
}
