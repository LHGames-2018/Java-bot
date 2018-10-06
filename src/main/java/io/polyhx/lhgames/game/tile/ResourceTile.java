package io.polyhx.lhgames.game.tile;

import io.polyhx.lhgames.game.point.Point;

/**
 * This class represents a resource tile in the map.
 */
public class ResourceTile extends Tile {
    /**
     * The remaining resources in this tile.
     */
    private final int fResource;

    /**
     * The density of this resource tile.
     */
    private final float fDensity;

    /**
     * Constructor.
     *
     * @param position The position of the tile in global coordinate.
     * @param resource The remaining resource of this tile.
     * @param density  The density of resource in this tile.
     */
    public ResourceTile(Point position, int resource, float density) {
        super(position, TileContent.RESOURCE);
        fResource = resource;
        fDensity = density;
    }

    /**
     * This method returns the remaining resources in this tile.
     *
     * @return The remaining resources in this tile.
     */
    public int getResource() {
        return fResource;
    }

    /**
     * This method returns the density of resource in this tile.
     *
     * @return The density of resource in this tile.
     */
    public float getDensity() {
        return fDensity;
    }
}
