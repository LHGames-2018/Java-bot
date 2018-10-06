package io.polyhx.lhgames.game;

import io.polyhx.lhgames.game.point.Point;
import io.polyhx.lhgames.game.tile.Tile;
import io.polyhx.lhgames.game.tile.TileContent;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MapTest {
    /**
     * The test data for the equivalent map:
     * <p>
     * [1] [ ] [4]
     * [3] [ ] [ ]
     * [2] [4] [ ]
     */
    private static final String TEST_DATA = "[[{1},{3},{2}],[{},{},{4,5000,1}],[{4,1500,1.5},{},{}]]";

    /**
     * The map generated from the data.
     */
    private final Map fMap;

    public MapTest() {
        List<Tile> column0 = new ArrayList<>();
        column0.add(new Tile(new Point(20, 15), TileContent.EMPTY));
        column0.add(new Tile(new Point(20, 16), TileContent.LAVA));
        column0.add(new Tile(new Point(20, 17), TileContent.HOUSE));

        List<Tile> column1 = new ArrayList<>();
        column1.add(new Tile(new Point(21, 15), TileContent.PLAYER));
        column1.add(new Tile(new Point(21, 16), TileContent.EMPTY));
        column1.add(new Tile(new Point(21, 17), TileContent.LAVA));

        List<Tile> column2 = new ArrayList<>();
        column2.add(new Tile(new Point(22, 15), TileContent.HOUSE));
        column2.add(new Tile(new Point(22, 16), TileContent.EMPTY));
        column2.add(new Tile(new Point(22, 17), TileContent.SHOP));

        List<List<Tile>> tiles = new ArrayList<>();
        tiles.add(column0);
        tiles.add(column1);
        tiles.add(column2);

        fMap = new Map(tiles, new ArrayList<>(), new Point(20, 15));
    }

    @Test
    public void getTile() {
        Tile tile;

        /* normal case */
        assertEquals(fMap.getTile(20, 15).getX(), 20);
        assertEquals(fMap.getTile(20, 15).getY(), 15);
        assertEquals(fMap.getTile(20, 15).getContent(), TileContent.EMPTY);

        /* normal case */
        assertEquals(fMap.getTile(20, 16).getX(), 20);
        assertEquals(fMap.getTile(20, 16).getY(), 16);
        assertEquals(fMap.getTile(20, 16).getContent(), TileContent.LAVA);

        /* normal case */
        assertEquals(fMap.getTile(20, 17).getX(), 20);
        assertEquals(fMap.getTile(20, 17).getY(), 17);
        assertEquals(fMap.getTile(20, 17).getContent(), TileContent.HOUSE);

        /* normal case */
        assertEquals(fMap.getTile(21, 15).getX(), 21);
        assertEquals(fMap.getTile(21, 15).getY(), 15);
        assertEquals(fMap.getTile(21, 15).getContent(), TileContent.PLAYER);

        /* normal case */
        assertEquals(fMap.getTile(21, 16).getX(), 21);
        assertEquals(fMap.getTile(21, 16).getY(), 16);
        assertEquals(fMap.getTile(21, 16).getContent(), TileContent.EMPTY);

        /* normal case */
        assertEquals(fMap.getTile(21, 17).getX(), 21);
        assertEquals(fMap.getTile(21, 17).getY(), 17);
        assertEquals(fMap.getTile(21, 17).getContent(), TileContent.LAVA);

        /* normal case */
        assertEquals(fMap.getTile(22, 15).getX(), 22);
        assertEquals(fMap.getTile(22, 15).getY(), 15);
        assertEquals(fMap.getTile(22, 15).getContent(), TileContent.HOUSE);

        /* normal case */
        assertEquals(fMap.getTile(22, 16).getX(), 22);
        assertEquals(fMap.getTile(22, 16).getY(), 16);
        assertEquals(fMap.getTile(22, 16).getContent(), TileContent.EMPTY);

        /* normal case */
        assertEquals(fMap.getTile(22, 17).getX(), 22);
        assertEquals(fMap.getTile(22, 17).getY(), 17);
        assertEquals(fMap.getTile(22, 17).getContent(), TileContent.SHOP);

        /* x out of bound */
        assertEquals(fMap.getTile(19, 15), null);
        assertEquals(fMap.getTile(23, 15), null);

        /* y out of bound */
        assertEquals(fMap.getTile(20, 14), null);
        assertEquals(fMap.getTile(20, 18), null);
    }
}