////////////////////////////////////////////
//         DO NOT TOUCH THIS FILE         //
////////////////////////////////////////////

package io.polyhx.lhgames.game;

import io.polyhx.lhgames.game.point.IPoint;
import io.polyhx.lhgames.game.point.Point;
import io.polyhx.lhgames.game.tile.ResourceTile;
import io.polyhx.lhgames.game.tile.Tile;

import java.util.List;

/**
 * This class represents the last map received by the game server.
 */
public class Map {
    /**
     * The 2D array of tiles in the map.
     */
    private final List<List<Tile>> fTiles;

    /**
     * This list of resources in the map.
     */
    private final List<ResourceTile> fResources;

    /**
     * The map's relative position.
     */
    private final Point fRelativeTo;

    /**
     * Constructor.
     *
     * @param tiles     The 2D array of tiles.
     * @param resources The list of resources in the map.
     * @param relative  The map's relative position.
     */
    public Map(List<List<Tile>> tiles, List<ResourceTile> resources, Point relative) {
        fTiles = tiles;
        fResources = resources;
        fRelativeTo = relative;
    }

    /**
     * This method returns the tile at a point in the global coordinate system.
     *
     * @param x The X coordinate of the tile we want.
     * @param y The Y coordinate of the tile we want.
     * @return The tile if it is in the map, else {@code null}.
     */
    public Tile getTile(int x, int y) {
        /* get coordinate relative to the 2D array */
        x -= fRelativeTo.getX();
        y -= fRelativeTo.getY();

        /* make sure the x coordinate is inside the map */
        if (x < 0 || x >= fTiles.size()) return null;

        /* get the column */
        List<Tile> column = fTiles.get(x);

        /* make sure the y coordinate is inside the map */
        if (y < 0 || y >= column.size()) return null;

        /* get the actual tile */
        return column.get(y);
    }

    /**
     * This method returns the tile at a point in the global coordinate system.
     *
     * @param point The point of the tile we want.
     * @return The tile if it is in the map, else {@code null}.
     */
    public Tile getTile(IPoint point) {
        return getTile(point.getX(), point.getY());
    }

    /**
     * This method returns the tile above a point in the global coordinate system.
     *
     * @param point The point from which we want the above tile.
     * @return The tile above the point if it is in the map, else {@code null}.
     */
    public Tile getTileAboveOf(IPoint point) {
        return getTile(point.getX(), point.getY() + 1);
    }

    /**
     * This method returns the tile below a point in the global coordinate system.
     *
     * @param point The point from which we want the below tile.
     * @return The tile below the point if it is in the map, else {@code null}.
     */
    public Tile getTileBelowOf(IPoint point) {
        return getTile(point.getX(), point.getY() - 1);
    }

    /**
     * This method returns the tile at right of a point in the global coordinate system.
     *
     * @param point The point from which we want the tile at the right.
     * @return The tile at the right of the point if it is in the map, else {@code null}.
     */
    public Tile getTileRightOf(IPoint point) {
        return getTile(point.getX() + 1, point.getY());
    }

    /**
     * This method returns the tile at left of a point in the global coordinate system.
     *
     * @param point The point from which we want the tile at the left.
     * @return The tile at the left of the point if it is in the map, else {@code null}.
     */
    public Tile getTileLeftOf(IPoint point) {
        return getTile(point.getX() - 1, point.getY());
    }

    /**
     * This method returns the 2D array of tiles.
     *
     * @return The 2D array of tiles in the map.
     */
    public List<List<Tile>> getTiles() {
        return fTiles;
    }

    /**
     * This method returns the list of resources in the map.
     *
     * @return The list of resources in the map.
     */
    public List<ResourceTile> getResources() {
        return fResources;
    }

    /**
     * This method returns the point in which the map is relative to.
     *
     * @return The point in which the map is relative to.
     */
    public Point getRelativePoint() {
        return fRelativeTo;
    }

    /**
     * This method prints the map to the standard output.
     *
     * Note: The X and Y coordinates are flipped.
     */
    public void print() {
        for (List<Tile> tiles : fTiles) {
            for (Tile tile : tiles) {
                System.out.print(tile.getContent().getSymbol() + " ");
            }
            System.out.println(" ");
        }
    }
}
