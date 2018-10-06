////////////////////////////////////////////
//         DO NOT TOUCH THIS FILE         //
////////////////////////////////////////////

package io.polyhx.lhgames.game;

/**
 * This enumeration defines the possible upgrades.
 */
public enum Upgrade {
    CARRYING_CAPACITY("CarryingCapacity", 0),
    MAXIMUM_HEALTH("MaximumHealth", 3),
    COLLECTING_SPEED("CollectingSpeed", 4),
    ATTACK("AttackPower", 1),
    DEFENCE("Defence", 2);

    /**
     * The JSON value of the upgrade.
     */
    private final String fJSON;

    /**
     * The ID of the upgrade.
     */
    private final int fID;

    /**
     * Constructor.
     *
     * @param json The JSON value of the upgrade.
     * @param id   The ID of the upgrade.
     */
    Upgrade(String json, int id) {
        fJSON = json;
        fID = id;
    }

    /**
     * This method returns the JSON value of the upgrade.
     *
     * @return The JSON value of the upgrade.
     */
    public String getJSON() {
        return fJSON;
    }

    /**
     * This method returns the JSON ID of the upgrade.
     *
     * @return The ID of the upgrade.
     */
    public int getID() {
        return fID;
    }
}