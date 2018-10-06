////////////////////////////////////////////
//         DO NOT TOUCH THIS FILE         //
////////////////////////////////////////////

package io.polyhx.lhgames.game.action;

import io.polyhx.lhgames.game.Upgrade;

/**
 * This class represents an upgrade action.
 */
public class UpgradeAction implements IAction {
    /**
     * The contained upgrade.
     */
    private final Upgrade fUpgrade;

    /**
     * Constructor.
     *
     * @param upgrade The upgrade type.
     */
    public UpgradeAction(Upgrade upgrade) {
        fUpgrade = upgrade;
    }

    @Override
    public ActionType getActionType() {
        return ActionType.UPGRADE;
    }

    @Override
    public String getJSONContent() {
        return fUpgrade.getJSON();
    }
}