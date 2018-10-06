////////////////////////////////////////////
//         DO NOT TOUCH THIS FILE         //
////////////////////////////////////////////

package io.polyhx.lhgames.game.action;

import io.polyhx.lhgames.game.point.IPoint;

/**
 * This class represents a resource collection action.
 */
public class CollectAction extends AbstractPointAction {
    /**
     * Constructor.
     *
     * @param point The direction of the resource to collect.
     */
    public CollectAction(IPoint point) {
        super(point);
    }

    @Override
    public ActionType getActionType() {
        return ActionType.COLLECT;
    }
}
