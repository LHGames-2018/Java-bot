////////////////////////////////////////////
//         DO NOT TOUCH THIS FILE         //
////////////////////////////////////////////

package io.polyhx.lhgames.game.action;

import io.polyhx.lhgames.game.point.IPoint;

/**
 * This class represents a steal action.
 */
public class StealAction extends AbstractPointAction {
    /**
     * Constructor.
     *
     * @param point The direction of the player we want to steal from.
     */
    public StealAction(IPoint point) {
        super(point);
    }

    @Override
    public ActionType getActionType() {
        return ActionType.STEAL;
    }
}
