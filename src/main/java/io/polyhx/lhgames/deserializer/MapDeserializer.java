////////////////////////////////////////////
//         DO NOT TOUCH THIS FILE         //
////////////////////////////////////////////

package io.polyhx.lhgames.deserializer;

import com.google.common.collect.ImmutableList;
import io.polyhx.lhgames.game.Map;
import io.polyhx.lhgames.game.point.Point;
import io.polyhx.lhgames.game.tile.ResourceTile;
import io.polyhx.lhgames.game.tile.Tile;
import org.jparsec.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used for decoding the map data coming from the game server.
 */
public class MapDeserializer {
    /**
     * This parser detects a opening bracket.
     */
    private final static Parser<Void> PARSER_BRACKET_OPEN = Scanners.isChar('[');

    /**
     * This parser detects a closing bracket.
     */
    private final static Parser<Void> PARSER_BRACKET_CLOSE = Scanners.isChar(']');

    /**
     * This parser detects a opening brace.
     */
    private final static Parser<Void> PARSER_BRACE_OPEN = Scanners.isChar('{');

    /**
     * This parser detects a closing brace.
     */
    private final static Parser<Void> PARSER_BRACE_CLOSE = Scanners.isChar('}');

    /**
     * This parser detects a comma.
     */
    private final static Parser<Void> PARSER_COMMA = Scanners.isChar(',');

    /**
     * This parser detects the pattern '(x,)*x+λ' where x is a number.
     */
    private final static Parser<List<Float>> PARSER_TILE_CONTENT =
            Scanners.DECIMAL.map(s -> Float.parseFloat(s)).sepBy(PARSER_COMMA);

    /**
     * This parser detects the pattern `{(x,)*x+λ}`.
     */
    private final static Parser<List<Float>> PARSER_TILE_ITEM =
            Parsers.sequence(PARSER_BRACE_OPEN, PARSER_TILE_CONTENT, PARSER_BRACE_CLOSE, (x1, x2, x3) -> x2);

    /**
     * This parser detects the pattern `({(x,)*x+λ},)*{(x,)*x+λ}+λ`.
     */
    private final static Parser<List<List<Float>>> PARSER_ROW_CONTENT =
            PARSER_TILE_ITEM.sepBy(PARSER_COMMA);

    /**
     * This parser detects the pattern `[({(x,)*x+λ},)*{(x,)*x+λ}+λ]`.
     */
    private final static Parser<List<List<Float>>> PARSER_ROW_ITEM =
            Parsers.sequence(PARSER_BRACKET_OPEN, PARSER_ROW_CONTENT, PARSER_BRACKET_CLOSE, (x1, x2, x3) -> x2);

    /**
     * This parser detects the pattern `([({(x,)*x+λ},)*{(x,)*x+λ}+λ])*[({(x,)*x+λ},)*{(x,)*x+λ}+λ]+λ`.
     */
    private final static Parser<List<List<List<Float>>>> PARSER_MAP_CONTENT =
            PARSER_ROW_ITEM.sepBy(PARSER_COMMA);

    /**
     * This parser detects the pattern `[([({(x,)*x+λ},)*{(x,)*x+λ}+λ])*[({(x,)*x+λ},)*{(x,)*x+λ}+λ]+λ]`.
     */
    private final static Parser<List<List<List<Float>>>> PARSER_MAP_ITEM
            = Parsers.sequence(PARSER_BRACKET_OPEN, PARSER_MAP_CONTENT, PARSER_BRACKET_CLOSE, (x1, x2, x3) -> x2);

    /**
     * This function deserialize the map using the custom format defined with the grammar in PARSER_MAP_ITEM.
     *
     * @param data     The serialized data of the map.
     * @param relative The map's relative point.
     * @return The decoded Map object.
     */
    public Map deserialize(String data, Point relative) {
        /* parse the data into a tree */
        List<List<List<Float>>> tree = PARSER_MAP_ITEM.parse(data);

        /* create the tile deserializer */
        List<ResourceTile> resources = new ArrayList<>();
        TileDeserializer deserializer = new TileDeserializer(resources);

        /* create the corresponding tiles */
        List<List<Tile>> map = new ArrayList<>();
        for (int i = 0; i < tree.size(); i++) {
            List<List<Float>> row = tree.get(i);
            List<Tile> tiles = new ArrayList<>();
            for (int j = 0; j < row.size(); j++) {
                List<Float> parameters = row.get(j);
                Point position = new Point(relative.getX() + i, relative.getY() + j);
                Tile tile = deserializer.deserialize(parameters, position);
                tiles.add(tile);
            }
            map.add(ImmutableList.copyOf(tiles));
        }

        /* create the map */
        return new Map(ImmutableList.copyOf(map), ImmutableList.copyOf(resources), relative);
    }
}
