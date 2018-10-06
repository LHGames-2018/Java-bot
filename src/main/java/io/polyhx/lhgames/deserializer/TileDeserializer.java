////////////////////////////////////////////
//         DO NOT TOUCH THIS FILE         //
////////////////////////////////////////////

package io.polyhx.lhgames.deserializer;

import io.polyhx.lhgames.game.point.Point;
import io.polyhx.lhgames.game.tile.ResourceTile;
import io.polyhx.lhgames.game.tile.Tile;
import io.polyhx.lhgames.game.tile.TileContent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is used for decoding tiles coming from the game server.
 */
public class TileDeserializer {
    /**
     * This map is used for mapping Integer value to TileContent.
     */
    private static final Map<Integer, TileContent> TILE_ID_MAP;

    static {
        TILE_ID_MAP = new HashMap<>();
        TILE_ID_MAP.put(TileContent.EMPTY.getID(), TileContent.EMPTY);
        TILE_ID_MAP.put(TileContent.WALL.getID(), TileContent.WALL);
        TILE_ID_MAP.put(TileContent.HOUSE.getID(), TileContent.HOUSE);
        TILE_ID_MAP.put(TileContent.LAVA.getID(), TileContent.LAVA);
        TILE_ID_MAP.put(TileContent.RESOURCE.getID(), TileContent.RESOURCE);
        TILE_ID_MAP.put(TileContent.SHOP.getID(), TileContent.SHOP);
        TILE_ID_MAP.put(TileContent.PLAYER.getID(), TileContent.PLAYER);
    }

    /**
     * The list of resource tiles that has been created by this deserializer.
     */
    private final List<ResourceTile> fResources;

    /**
     * Constructor.
     *
     * @param resources The resources array used for putting the created resource tiles.
     */
    public TileDeserializer(List<ResourceTile> resources) {
        fResources = resources;
    }

    /**
     * This method creates a tile from a list of value. A single value is tile ID and 3 values is a resource tile.
     *
     * @param data     The list of value we want to create a tile from.
     * @param position The tile's relative position.
     * @return The created tile.
     */
    public Tile deserialize(List<Float> data, Point position) {
        /* empty tile */
        if (data.size() == 0) {
            return new Tile(position, TileContent.EMPTY);
        }

        /* tile other than a resource */
        if (data.get(0) != TileContent.RESOURCE.getID()) {
            /* they all have one parameter */
            if (data.size() != 1) {
                System.out.println("Non resource tile doesn't have one parameter: " + data);
                return new Tile(position, TileContent.EMPTY);
            }

            /* get the tile content */
            TileContent content = TILE_ID_MAP.get(data.get(0).intValue());
            if (content == null) {
                System.out.println("Fail to get tile content: " + data);
                return new Tile(position, TileContent.EMPTY);
            }

            /* create the tile */
            return new Tile(position, content);
        }

        /* a resource has 3 parameters */
        if (data.size() != 3) {
            System.out.println("Resource tile doesn't have three parameters: " + data);
            return new Tile(position, TileContent.EMPTY);
        }

        /* create the resource tile and add it to the resource list */
        ResourceTile tile = new ResourceTile(position, data.get(1).intValue(), data.get(2));
        fResources.add(tile);
        return tile;
    }
}
