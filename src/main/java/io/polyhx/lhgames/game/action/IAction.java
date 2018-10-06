////////////////////////////////////////////
//         DO NOT TOUCH THIS FILE         //
////////////////////////////////////////////

package io.polyhx.lhgames.game.action;

/**
 * This interface represents an action performed by the player.
 */
public interface IAction {
    /**
     * This method returns the type of the action.
     *
     * @return The type of the action.
     */
    ActionType getActionType();

    /**
     * This method returns the JSON content of the action.
     *
     * @return The JSON content of the action.
     */
    String getJSONContent();
}
