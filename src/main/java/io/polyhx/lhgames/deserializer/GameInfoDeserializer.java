////////////////////////////////////////////
//         DO NOT TOUCH THIS FILE         //
////////////////////////////////////////////

package io.polyhx.lhgames.deserializer;

import com.google.gson.*;
import io.polyhx.lhgames.game.GameInfo;
import io.polyhx.lhgames.game.Map;
import io.polyhx.lhgames.game.Player;
import io.polyhx.lhgames.game.point.Point;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used for decoding the game info data coming from the game server.
 */
public class GameInfoDeserializer implements JsonDeserializer<GameInfo> {
    @Override
    public GameInfo deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();

        /* deserialize the player */
        PlayerDeserializer playerDeserializer = new PlayerDeserializer();
        Player player = playerDeserializer.deserialize(obj.get("Player"), Player.class, context);

        /* deserialize the other players */
        List<Player> others = new ArrayList<>();
        for(JsonElement elem : obj.getAsJsonArray("OtherPlayers")) {
            others.add(playerDeserializer.deserialize(elem, Player.class, context));
        }

        /* deserialize the map */
        Point relative = new Point(obj.get("xMin").getAsInt(), obj.get("yMin").getAsInt());
        Map map = new MapDeserializer().deserialize(obj.get("CustomSerializedMap").getAsString(), relative);

        return new GameInfo(player, others, map, obj.get("WallsAreBreakable").getAsBoolean());
    }
}
