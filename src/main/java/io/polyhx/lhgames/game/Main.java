////////////////////////////////////////////
//         DO NOT TOUCH THIS FILE         //
////////////////////////////////////////////

package io.polyhx.lhgames.game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.polyhx.lhgames.deserializer.GameInfoDeserializer;
import io.polyhx.lhgames.game.action.*;
import io.polyhx.lhgames.game.bot.Bot;
import io.polyhx.lhgames.serializer.ActionSerializer;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        ActionSerializer actionSerializerObj = new ActionSerializer();
        Gson actionSerializer = new GsonBuilder()
                .registerTypeAdapter(StealAction.class, actionSerializerObj)
                .registerTypeAdapter(MeleeAttackAction.class, actionSerializerObj)
                .registerTypeAdapter(CollectAction.class, actionSerializerObj)
                .registerTypeAdapter(MoveAction.class, actionSerializerObj)
                .registerTypeAdapter(UpgradeAction.class, actionSerializerObj)
                .registerTypeAdapter(PurchaseAction.class, actionSerializerObj)
                .registerTypeAdapter(HealAction.class, actionSerializerObj)
                .create();

        final Gson gameDeserializer = new GsonBuilder()
                .registerTypeAdapter(GameInfo.class, new GameInfoDeserializer())
                .create();

        final Bot bot = new Bot();

        port(3000);
        post("/", (req, res) -> {
            /* remove the `data=` from the body of the request */
            String encoded = req.body().substring(5);

            /* decode the URL-encoded json */
            String json = java.net.URLDecoder.decode(encoded, "UTF-8");

            /* parse the data */
            GameInfo game = gameDeserializer.fromJson(json, GameInfo.class);

            /* get an action from the bot */
            IAction action;
            try {
                action = bot.getAction(game.getMap(), game.getPlayer(), game.getOtherPlayers(), game);
            } catch (Exception e){
                e.printStackTrace();
                return null;
            }

            /* return the serialized JSON */
            return actionSerializer.toJson(action);
        });
        init();
    }
}
