////////////////////////////////////////////
//         DO NOT TOUCH THIS FILE         //
////////////////////////////////////////////

package io.polyhx.lhgames.game;

import java.util.List;

/**
 * This class represents the game information received by the game server.
 */
public class GameInfo {
    /**
     * The information concerning the player played by the bot.
     */
    private final Player fPlayer;

    /**
     * The information concerning the other visible players on the map.
     */
    private final List<Player> fOtherPlayers;

    /**
     * The information concerning the map.
     */
    private final Map fMap;

    /**
     * Whether the walls are breakable.
     */
    private final boolean fAreWallsBreakable;

    /**
     * Constructor.
     *
     * @param player        The player played by the bot.
     * @param others        The other visible players.
     * @param map           The visible map.
     * @param wallBreakable Whether the walls are breakable.
     */
    public GameInfo(Player player, List<Player> others, Map map, boolean wallBreakable) {
        fPlayer = player;
        fOtherPlayers = others;
        fMap = map;
        fAreWallsBreakable = wallBreakable;
    }

    /**
     * This method returns the information of the player played by the bot.
     *
     * @return The player of the bot.
     */
    public Player getPlayer() {
        return fPlayer;
    }

    /**
     * This method returns the information concerning the other visible players.
     *
     * @return The other visible players.
     */
    public List<Player> getOtherPlayers() {
        return fOtherPlayers;
    }

    /**
     * This method returns the current map.
     *
     * @return The current map.
     */
    public Map getMap() {
        return fMap;
    }

    /**
     * This method returns whether the walls are breakable or not.
     *
     * @return Whether the walls are breakable or not.
     */
    public boolean areWallsBreakable() {
        return fAreWallsBreakable;
    }
}
