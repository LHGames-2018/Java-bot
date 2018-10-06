package io.polyhx.lhgames.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.polyhx.lhgames.game.Item;
import io.polyhx.lhgames.game.point.Point;
import io.polyhx.lhgames.game.Upgrade;
import io.polyhx.lhgames.game.action.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class ActionSerializerTest {
    @Test
    public void serialize() {
        ActionSerializer serializer = new ActionSerializer();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(StealAction.class, serializer)
                .registerTypeAdapter(MeleeAttackAction.class, serializer)
                .registerTypeAdapter(CollectAction.class, serializer)
                .registerTypeAdapter(MoveAction.class, serializer)
                .registerTypeAdapter(UpgradeAction.class, serializer)
                .registerTypeAdapter(PurchaseAction.class, serializer)
                .registerTypeAdapter(HealAction.class, serializer)
                .create();

        String json;

        /* steal action */
        json = gson.toJson(new StealAction(new Point(1, 0)));
        assertEquals(json, "{\"ActionName\":\"StealAction\",\"Content\":\"{X:1,Y:0}\"}");

        /* melee attack action */
        json = gson.toJson(new MeleeAttackAction(new Point(0, -1)));
        assertEquals(json, "{\"ActionName\":\"MeleeAttackAction\",\"Content\":\"{X:0,Y:-1}\"}");

        /* collect action*/
        json = gson.toJson(new CollectAction(new Point(-2, 4)));
        assertEquals(json, "{\"ActionName\":\"CollectAction\",\"Content\":\"{X:0,Y:0}\"}");

        /* collect action*/
        json = gson.toJson(new CollectAction(new Point(-2, 0)));
        assertEquals(json, "{\"ActionName\":\"CollectAction\",\"Content\":\"{X:-1,Y:0}\"}");

        /* move action */
        json = gson.toJson(new MoveAction(new Point(2, 3)));
        assertEquals(json, "{\"ActionName\":\"MoveAction\",\"Content\":\"{X:0,Y:0}\"}");

        /* move action */
        json = gson.toJson(new MoveAction(new Point(0, 3)));
        assertEquals(json, "{\"ActionName\":\"MoveAction\",\"Content\":\"{X:0,Y:1}\"}");

        /* upgrade action */
        json = gson.toJson(new UpgradeAction(Upgrade.ATTACK));
        assertEquals(json, "{\"ActionName\":\"UpgradeAction\",\"Content\":\"AttackPower\"}");

        /* purchase action */
        json = gson.toJson(new PurchaseAction(Item.SHIELD));
        assertEquals(json, "{\"ActionName\":\"PurchaseAction\",\"Content\":\"Shield\"}");

        /* purchase action */
        json = gson.toJson(new HealAction());
        assertEquals(json, "{\"ActionName\":\"HealAction\",\"Content\":\"\"}");
    }
}