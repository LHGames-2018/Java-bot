package io.polyhx.lhgames.deserializer;

import io.polyhx.lhgames.game.Map;
import io.polyhx.lhgames.game.point.Point;
import io.polyhx.lhgames.game.tile.TileContent;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapDeserializerTest {
    /**
     * The test data for the equivalent map:
     * <p>
     * [1] [ ] [4]
     * [3] [ ] [ ]
     * [2] [4] [ ]
     */
    private static final String TEST_DATA = "[[{1},{3},{2}],[{},{},{4,5000,1}],[{4,1500,1.5},{},{}]]";

    @Test
    public void deserialize() {
        MapDeserializer deserializer = new MapDeserializer();
        Map map = deserializer.deserialize(TEST_DATA, new Point(0, 0));

        assertEquals(map.getTile(0, 0).getX(), 0);
        assertEquals(map.getTile(0, 0).getY(), 0);
        assertEquals(map.getTile(0, 0).getContent(), TileContent.WALL);

        assertEquals(map.getTile(1, 0).getX(), 1);
        assertEquals(map.getTile(1, 0).getY(), 0);
        assertEquals(map.getTile(1, 0).getContent(), TileContent.EMPTY);

        assertEquals(map.getTile(2, 0).getX(), 2);
        assertEquals(map.getTile(2, 0).getY(), 0);
        assertEquals(map.getTile(2, 0).getContent(), TileContent.RESOURCE);

        assertEquals(map.getTile(0, 1).getX(), 0);
        assertEquals(map.getTile(0, 1).getY(), 1);
        assertEquals(map.getTile(0, 1).getContent(), TileContent.LAVA);

        assertEquals(map.getTile(1, 1).getX(), 1);
        assertEquals(map.getTile(1, 1).getY(), 1);
        assertEquals(map.getTile(1, 1).getContent(), TileContent.EMPTY);

        assertEquals(map.getTile(2, 1).getX(), 2);
        assertEquals(map.getTile(2, 1).getY(), 1);
        assertEquals(map.getTile(2, 1).getContent(), TileContent.EMPTY);

        assertEquals(map.getTile(0, 2).getX(), 0);
        assertEquals(map.getTile(0, 2).getY(), 2);
        assertEquals(map.getTile(0, 2).getContent(), TileContent.HOUSE);

        assertEquals(map.getTile(1, 2).getX(), 1);
        assertEquals(map.getTile(1, 2).getY(), 2);
        assertEquals(map.getTile(1, 2).getContent(), TileContent.RESOURCE);

        assertEquals(map.getTile(2, 2).getX(), 2);
        assertEquals(map.getTile(2, 2).getY(), 2);
        assertEquals(map.getTile(2, 2).getContent(), TileContent.EMPTY);

        assertEquals(map.getResources().size(), 2);
        assertEquals(map.getResources().get(0).getContent(), TileContent.RESOURCE);
        assertEquals(map.getResources().get(0).getResource(), 5000);
        assertEquals(map.getResources().get(0).getDensity(), 1.0, 0.001);
        assertEquals(map.getResources().get(1).getContent(), TileContent.RESOURCE);
        assertEquals(map.getResources().get(1).getResource(), 1500);
        assertEquals(map.getResources().get(1).getDensity(), 1.5, 0.001);

        assertEquals(map.getRelativePoint().getX(), 0);
        assertEquals(map.getRelativePoint().getY(), 0);
    }
}