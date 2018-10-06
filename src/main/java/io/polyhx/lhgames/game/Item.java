////////////////////////////////////////////
//         DO NOT TOUCH THIS FILE         //
////////////////////////////////////////////

package io.polyhx.lhgames.game;

import java.util.HashMap;

/**
 * This enumeration represents the purchasable items.
 */
public enum Item {
    SWORD("Sword", 0),
    SHIELD("Shield", 1),
    BACKPACK("Backpack", 2),
    PICKAXE("Pickaxe", 3),
    HEALTH_POTION("HealthPotion", 4);

    /**
     * The JSON value of the item.
     */
    private final String fJSON;

    /**
     * The ID of the item.
     */
    private final int fID;

    /**
     * Constructor.
     *
     * @param json The JSON value of the item.
     * @param id   The ID of the item.
     */
    Item(String json, int id) {
        fJSON = json;
        fID = id;
    }

    /**
     * This method returns the JSON value of the item.
     *
     * @return The JSON value of the item.
     */
    public String getJSON() {
        return fJSON;
    }

    /**
     * This method returns the JSON ID of the item.
     *
     * @return The ID of the item.
     */
    public int getID() {
        return fID;
    }

    /**
     * This map is used for mapping between the ID and the item.
     */
    private static final HashMap<Integer, Item> ITEM_ID_MAP;

    static {
        ITEM_ID_MAP = new HashMap<>();
        ITEM_ID_MAP.put(Item.SWORD.getID(), Item.SWORD);
        ITEM_ID_MAP.put(Item.SHIELD.getID(), Item.SHIELD);
        ITEM_ID_MAP.put(Item.BACKPACK.getID(), Item.BACKPACK);
        ITEM_ID_MAP.put(Item.PICKAXE.getID(), Item.PICKAXE);
        ITEM_ID_MAP.put(Item.HEALTH_POTION.getID(), Item.HEALTH_POTION);
    }

    /**
     * This static method returns the item enumeration from the ID.
     *
     * @param id The ID of the enumeration.
     * @return The corresponding enumeration of the ID.
     */
    public static Item fromID(int id) {
        return ITEM_ID_MAP.get(id);
    }
}
