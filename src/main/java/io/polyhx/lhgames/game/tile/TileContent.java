package io.polyhx.lhgames.game.tile;

/**
 * This enumeration defines the possible type of tile.
 */
public enum TileContent {
    EMPTY(0, ' '),
    WALL(1, 'X'),
    HOUSE(2, 'H'),
    LAVA(3, 'L'),
    RESOURCE(4, 'R'),
    SHOP(5, 'S'),
    PLAYER(6, 'P');

    /**
     * This ID of this tile type.
     */
    private final int fID;

    /**
     * The symbol used for printing this tile type.
     */
    private final char fSymbol;

    /**
     * Constructor.
     *
     * @param id     The ID of this tile type.
     * @param symbol The symbol of this tile type.
     */
    TileContent(int id, char symbol) {
        fID = id;
        fSymbol = symbol;
    }

    /**
     * This method returns the ID of this tile type.
     *
     * @return The ID of this tile type.
     */
    public int getID() {
        return fID;
    }

    /**
     * This method returns the map symbol of this tile type.
     *
     * @return The symbol of this tile type.
     */
    public char getSymbol() {
        return fSymbol;
    }
}
