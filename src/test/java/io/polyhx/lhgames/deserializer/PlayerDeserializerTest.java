package io.polyhx.lhgames.deserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.polyhx.lhgames.game.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerDeserializerTest {
    private final static String TEST_JSON = "{" +
            "\"Health\":2," +
            "\"MaxHealth\":2," +
            "\"CarriedResources\":0," +
            "\"CarryingCapacity\":1002," +
            "\"CollectingSpeed\":2.0," +
            "\"TotalResources\":3000," +
            "\"AttackPower\":4," +
            "\"Defence\":5," +
            "\"Position\":{\"x\":22,\"y\":23}," +
            "\"HouseLocation\":{\"x\":40,\"y\":54}," +
            "\"Score\":0," +
            "\"Name\":\"Player 1\"," +
            "\"UpgradeLevels\":[0,1,2,3,4]," +
            "\"CarriedItems\":[0,2,2,3,1,4]" +
            "}";

    @Test
    public void deserialize() {
        Gson gson = new GsonBuilder().registerTypeAdapter(Player.class, new PlayerDeserializer()).create();
        Player player = gson.fromJson(TEST_JSON, Player.class);

        assertEquals(player.getPosition().getX(), 22);
        assertEquals(player.getPosition().getY(), 23);
        assertEquals(player.getHousePosition().getX(), 40);
        assertEquals(player.getHousePosition().getY(), 54);
        assertEquals(player.getCurrentHealth(), 2);
        assertEquals(player.getMaximumHealth(), 2);
        assertEquals(player.getCarriedResource(), 0);
        assertEquals(player.getResourceCapacity(), 1002);
        assertEquals(player.getTotalResource(), 3000);
        assertEquals(player.getAttack(), 4);
        assertEquals(player.getDefence(), 5);
        assertEquals(player.getScore(), 0);
        assertEquals(player.getCollectingSpeed(), 2.0, 0.01);
        assertEquals(player.getName(), "Player 1");
        assertEquals(player.getCapacityLevel(), 0);
        assertEquals(player.getAttackLevel(), 1);
        assertEquals(player.getDefenceLevel(), 2);
        assertEquals(player.getHealthLevel(), 3);
        assertEquals(player.getCollectingSpeedLevel(), 4);
    }
}
