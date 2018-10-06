////////////////////////////////////////////
//         DO NOT TOUCH THIS FILE         //
////////////////////////////////////////////

package io.polyhx.lhgames.game.action;

import io.polyhx.lhgames.game.Item;

/**
 * This class represents purchase action.
 */
public class PurchaseAction implements IAction {
    /**
     * The item to purchase.
     */
    private final Item fItem;

    /**
     * Constructor.
     *
     * @param item The item to purchase.
     */
    public PurchaseAction(Item item) {
        fItem = item;
    }

    @Override
    public ActionType getActionType() {
        return ActionType.PURCHASE;
    }

    @Override
    public String getJSONContent() {
        return fItem.getJSON();
    }
}