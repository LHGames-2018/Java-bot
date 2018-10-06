////////////////////////////////////////////
//         DO NOT TOUCH THIS FILE         //
////////////////////////////////////////////

package io.polyhx.lhgames.game.action;

import io.polyhx.lhgames.game.point.IPoint;
import io.polyhx.lhgames.game.point.Point;

/**
 * This abstract class represents any action that uses a point as a content.
 */
public abstract class AbstractPointAction implements IAction {
    /**
     * This target point of the action.
     */
    private final IPoint fPoint;

    /**
     * Constructor.
     * <p>
     * Note: The point must be in a single direction, that is (0,4) -> (0,1) or (-3,0) -> (-1,0). This is invalid: (3,4).
     *
     * @param point The target point of the action.
     */
    public AbstractPointAction(IPoint point) {
        /* get the direction for each coordinate */
        int x = (point.getX() != 0) ? point.getX() / Math.abs(point.getX()) : 0;
        int y = (point.getY() != 0) ? point.getY() / Math.abs(point.getY()) : 0;

        /* the point can only be in one direction */
        fPoint = (Math.abs(x) + Math.abs(y) == 2) ? new Point() : new Point(x, y);
    }

    @Override
    public String getJSONContent() {
        return fPoint.toJSON();
    }
}
