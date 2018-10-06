////////////////////////////////////////////
//         DO NOT TOUCH THIS FILE         //
////////////////////////////////////////////

package io.polyhx.lhgames.game.action;

/**
 * This class represents an heal action.
 */
public class HealAction implements IAction {
    @Override
    public ActionType getActionType() {
        return ActionType.HEAL;
    }

    @Override
    public String getJSONContent() {
        return "";
    }
}
