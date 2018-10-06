package io.polyhx.lhgames.deserializer;

import io.polyhx.lhgames.game.point.Point;
import io.polyhx.lhgames.game.tile.ResourceTile;
import io.polyhx.lhgames.game.tile.Tile;
import io.polyhx.lhgames.game.tile.TileContent;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TileDeserializerTest {
    @Test
    public void deserialize() {
        List<ResourceTile> resources = new ArrayList<>();
        TileDeserializer deserializer = new TileDeserializer(resources);

        Tile tile;

        /* empty tile */
        tile = deserializer.deserialize(Arrays.asList(), new Point(0, 0));
        assertEquals(tile.getContent(), TileContent.EMPTY);
        assertEquals(tile.getX(), 0);
        assertEquals(tile.getY(), 0);
        assertEquals(resources.size(), 0);

        /* empty tile with number */
        tile = deserializer.deserialize(Arrays.asList(0.0f), new Point(2, 0));
        assertEquals(tile.getContent(), TileContent.EMPTY);
        assertEquals(tile.getX(), 2);
        assertEquals(tile.getY(), 0);
        assertEquals(resources.size(), 0);

        /* wall tile */
        tile = deserializer.deserialize(Arrays.asList(1.0f), new Point(2, 4));
        assertEquals(tile.getContent(), TileContent.WALL);
        assertEquals(tile.getX(), 2);
        assertEquals(tile.getY(), 4);
        assertEquals(resources.size(), 0);

        /* wall tile */
        tile = deserializer.deserialize(Arrays.asList(2.0f), new Point(-2, 4));
        assertEquals(tile.getContent(), TileContent.HOUSE);
        assertEquals(tile.getX(), -2);
        assertEquals(tile.getY(), 4);
        assertEquals(resources.size(), 0);

        /* lava tile */
        tile = deserializer.deserialize(Arrays.asList(3.0f), new Point(-2, -4));
        assertEquals(tile.getContent(), TileContent.LAVA);
        assertEquals(tile.getX(), -2);
        assertEquals(tile.getY(), -4);
        assertEquals(resources.size(), 0);

        /* resource tile */
        tile = deserializer.deserialize(Arrays.asList(4.0f, 5000.0f, 2.0f), new Point(0, 0));
        assertEquals(tile.getContent(), TileContent.RESOURCE);
        assertEquals(tile.getX(), 0);
        assertEquals(tile.getY(), 0);
        assertEquals(resources.size(), 1);
        assertEquals(((ResourceTile) tile).getResource(), 5000);
        assertEquals(((ResourceTile) tile).getDensity(), 2, 0.001);

        /* empty tile from a malformed resource tile */
        tile = deserializer.deserialize(Arrays.asList(4.0f, 5000.0f), new Point(0, 0));
        assertEquals(tile.getContent(), TileContent.EMPTY);
        assertEquals(tile.getX(), 0);
        assertEquals(tile.getY(), 0);
        assertEquals(resources.size(), 1);

        /* empty tile from a malformed resource tile */
        tile = deserializer.deserialize(Arrays.asList(4.0f, 5000.0f, 4.0f, 5.0f), new Point(0, 0));
        assertEquals(tile.getContent(), TileContent.EMPTY);
        assertEquals(tile.getX(), 0);
        assertEquals(tile.getY(), 0);
        assertEquals(resources.size(), 1);

        /* shop tile */
        tile = deserializer.deserialize(Arrays.asList(5.0f), new Point(0, 0));
        assertEquals(tile.getContent(), TileContent.SHOP);
        assertEquals(tile.getX(), 0);
        assertEquals(tile.getY(), 0);
        assertEquals(resources.size(), 1);

        /* shop tile */
        tile = deserializer.deserialize(Arrays.asList(5.0f), new Point(0, 0));
        assertEquals(tile.getContent(), TileContent.SHOP);
        assertEquals(tile.getX(), 0);
        assertEquals(tile.getY(), 0);
        assertEquals(resources.size(), 1);

        /* player tile */
        tile = deserializer.deserialize(Arrays.asList(6.0f), new Point(0, 0));
        assertEquals(tile.getContent(), TileContent.PLAYER);
        assertEquals(tile.getX(), 0);
        assertEquals(tile.getY(), 0);
        assertEquals(resources.size(), 1);

        /* resource tile */
        tile = deserializer.deserialize(Arrays.asList(4.0f, 1000.0f, 1.0f), new Point(0, 0));
        assertEquals(tile.getContent(), TileContent.RESOURCE);
        assertEquals(tile.getX(), 0);
        assertEquals(tile.getY(), 0);
        assertEquals(resources.size(), 2);
        assertEquals(((ResourceTile) tile).getResource(), 1000);
        assertEquals(((ResourceTile) tile).getDensity(), 1, 0.001);
    }
}