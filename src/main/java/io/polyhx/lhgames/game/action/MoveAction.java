////////////////////////////////////////////
//         DO NOT TOUCH THIS FILE         //
////////////////////////////////////////////

package io.polyhx.lhgames.game.action;

import io.polyhx.lhgames.game.point.IPoint;

/**
 * This class represents a move action.
 */
public class MoveAction extends AbstractPointAction {
    /**
     * Constructor.
     *
     * @param point The direction to move.
     */
    public MoveAction(IPoint point) {
        super(point);
    }

    @Override
    public ActionType getActionType() {
        return ActionType.MOVE;
    }
}
