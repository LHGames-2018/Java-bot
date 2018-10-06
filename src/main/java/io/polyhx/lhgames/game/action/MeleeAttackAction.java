////////////////////////////////////////////
//         DO NOT TOUCH THIS FILE         //
////////////////////////////////////////////

package io.polyhx.lhgames.game.action;

import io.polyhx.lhgames.game.point.IPoint;

/**
 * This class represents a melee attack action.
 */
public class MeleeAttackAction extends AbstractPointAction {
    /**
     * Constructor.
     *
     * @param point The direction of the attack.
     */
    public MeleeAttackAction(IPoint point) {
        super(point);
    }

    @Override
    public ActionType getActionType() {
        return ActionType.MELEE_ATTACK;
    }
}
