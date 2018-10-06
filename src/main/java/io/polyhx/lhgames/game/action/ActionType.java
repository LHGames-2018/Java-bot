////////////////////////////////////////////
//         DO NOT TOUCH THIS FILE         //
////////////////////////////////////////////

package io.polyhx.lhgames.game.action;

/**
 * This enumeration represents all possible actions.
 */
public enum ActionType {
    STEAL("StealAction"),
    MELEE_ATTACK("MeleeAttackAction"),
    COLLECT("CollectAction"),
    MOVE("MoveAction"),
    UPGRADE("UpgradeAction"),
    PURCHASE("PurchaseAction"),
    HEAL("HealAction");

    /**
     * The JSON value of this action.
     */
    private final String fJSON;

    /**
     * Constructor.
     *
     * @param json The JSON value of the action.
     */
    ActionType(String json) {
        fJSON = json;
    }

    /**
     * This method returns the JSON value of the action.
     *
     * @return The JSON value of the action.
     */
    public String getJSON() {
        return fJSON;
    }
}
