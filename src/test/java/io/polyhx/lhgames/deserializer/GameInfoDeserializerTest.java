package io.polyhx.lhgames.deserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.polyhx.lhgames.game.GameInfo;
import io.polyhx.lhgames.game.Item;
import io.polyhx.lhgames.game.tile.TileContent;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameInfoDeserializerTest {
    /**
     * The test data for with the equivalent map:
     * <p>
     * [1] [ ] [ ]
     * [3] [ ] [ ]
     * [2] [4] [ ]
     */
    private final static String TEST_JSON = "{" +
        "\"Player\":{" +
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
            "\"UpgradeLevels\":[0,3,4,2,1]," +
            "\"CarriedItems\":[0,2,2,3,1,4]" +
        "}," +
        "\"CustomSerializedMap\":\"[[{1},{3},{2}],[{},{},{4,5000,1}],[{},{},{}]]\"," +
        "\"xMin\":20," +
        "\"yMin\":40," +
        "\"WallsAreBreakable\":false," +
        "\"OtherPlayers\":[" +
            "{" +
                "\"Health\":1," +
                "\"MaxHealth\":2," +
                "\"CarriedResources\":3," +
                "\"CarryingCapacity\":4," +
                "\"CollectingSpeed\":5.0," +
                "\"TotalResources\":6," +
                "\"AttackPower\":7," +
                "\"Defence\":8," +
                "\"Position\":{\"x\":9,\"y\":10}," +
                "\"HouseLocation\":{\"x\":11,\"y\":12}," +
                "\"Score\":13," +
                "\"Name\":\"Player 14\"," +
                "\"UpgradeLevels\":[15,17,18,20,21]," +
                "\"CarriedItems\":[0,2,3,1,4]" +
            "},{" +
                "\"Health\":22," +
                "\"MaxHealth\":23," +
                "\"CarriedResources\":24," +
                "\"CarryingCapacity\":25," +
                "\"CollectingSpeed\":26.0," +
                "\"TotalResources\":27," +
                "\"AttackPower\":28," +
                "\"Defence\":29," +
                "\"Position\":{\"x\":30,\"y\":31}," +
                "\"HouseLocation\":{\"x\":32,\"y\":33}," +
                "\"Score\":34," +
                "\"Name\":\"Player 35\"," +
                "\"UpgradeLevels\":[36,37,38,39,40]," +
                "\"CarriedItems\":[]" +
            "}" +
        "]" +
    "}";

    @Test
    public void deserialize() {
        Gson gson = new GsonBuilder().registerTypeAdapter(GameInfo.class, new GameInfoDeserializer()).create();
        GameInfo game = gson.fromJson(TEST_JSON, GameInfo.class);

        /* assert player information */
        assertEquals(game.getPlayer().getPosition().getX(), 22);
        assertEquals(game.getPlayer().getPosition().getY(), 23);
        assertEquals(game.getPlayer().getHousePosition().getX(), 40);
        assertEquals(game.getPlayer().getHousePosition().getY(), 54);
        assertEquals(game.getPlayer().getCurrentHealth(), 2);
        assertEquals(game.getPlayer().getMaximumHealth(), 2);
        assertEquals(game.getPlayer().getCarriedResource(), 0);
        assertEquals(game.getPlayer().getResourceCapacity(), 1002);
        assertEquals(game.getPlayer().getTotalResource(), 3000);
        assertEquals(game.getPlayer().getAttack(), 4);
        assertEquals(game.getPlayer().getDefence(), 5);
        assertEquals(game.getPlayer().getScore(), 0);
        assertEquals(game.getPlayer().getCollectingSpeed(), 2.0, 0.01);
        assertEquals(game.getPlayer().getName(), "Player 1");
        assertEquals(game.getPlayer().getCapacityLevel(), 0);
        assertEquals(game.getPlayer().getAttackLevel(), 3);
        assertEquals(game.getPlayer().getDefenceLevel(), 4);
        assertEquals(game.getPlayer().getHealthLevel(), 2);
        assertEquals(game.getPlayer().getCollectingSpeedLevel(), 1);
        assertEquals(game.getPlayer().getItems().size(), 6);
        assertEquals(game.getPlayer().getItems().get(0), Item.SWORD);
        assertEquals(game.getPlayer().getItems().get(1), Item.BACKPACK);
        assertEquals(game.getPlayer().getItems().get(2), Item.BACKPACK);
        assertEquals(game.getPlayer().getItems().get(3), Item.PICKAXE);
        assertEquals(game.getPlayer().getItems().get(4), Item.SHIELD);
        assertEquals(game.getPlayer().getItems().get(5), Item.HEALTH_POTION);

        /* assert map content */
        assertEquals(game.getMap().getTile(20, 40).getX(), 20);
        assertEquals(game.getMap().getTile(20, 40).getY(), 40);
        assertEquals(game.getMap().getTile(20, 40).getContent(), TileContent.WALL);

        assertEquals(game.getMap().getTile(21, 40).getX(), 21);
        assertEquals(game.getMap().getTile(21, 40).getY(), 40);
        assertEquals(game.getMap().getTile(21, 40).getContent(), TileContent.EMPTY);

        assertEquals(game.getMap().getTile(22, 40).getX(), 22);
        assertEquals(game.getMap().getTile(22, 40).getY(), 40);
        assertEquals(game.getMap().getTile(22, 40).getContent(), TileContent.EMPTY);

        assertEquals(game.getMap().getTile(20, 41).getX(), 20);
        assertEquals(game.getMap().getTile(20, 41).getY(), 41);
        assertEquals(game.getMap().getTile(20, 41).getContent(), TileContent.LAVA);

        assertEquals(game.getMap().getTile(21, 41).getX(), 21);
        assertEquals(game.getMap().getTile(21, 41).getY(), 41);
        assertEquals(game.getMap().getTile(21, 41).getContent(), TileContent.EMPTY);

        assertEquals(game.getMap().getTile(22, 41).getX(), 22);
        assertEquals(game.getMap().getTile(22, 41).getY(), 41);
        assertEquals(game.getMap().getTile(22, 41).getContent(), TileContent.EMPTY);

        assertEquals(game.getMap().getTile(20, 42).getX(), 20);
        assertEquals(game.getMap().getTile(20, 42).getY(), 42);
        assertEquals(game.getMap().getTile(20, 42).getContent(), TileContent.HOUSE);

        assertEquals(game.getMap().getTile(21, 42).getX(), 21);
        assertEquals(game.getMap().getTile(21, 42).getY(), 42);
        assertEquals(game.getMap().getTile(21, 42).getContent(), TileContent.RESOURCE);

        assertEquals(game.getMap().getTile(22, 42).getX(), 22);
        assertEquals(game.getMap().getTile(22, 42).getY(), 42);
        assertEquals(game.getMap().getTile(22, 42).getContent(), TileContent.EMPTY);

        assertEquals(game.getMap().getResources().size(), 1);
        assertEquals(game.getMap().getResources().get(0).getContent(), TileContent.RESOURCE);
        assertEquals(game.getMap().getResources().get(0).getResource(), 5000);
        assertEquals(game.getMap().getResources().get(0).getDensity(), 1, 0.001);

        assertEquals(game.getMap().getRelativePoint().getX(), 20);
        assertEquals(game.getMap().getRelativePoint().getY(), 40);

        assertEquals(game.areWallsBreakable(), false);

        /* assert other player */
        assertEquals(game.getOtherPlayers().size(), 2);
        assertEquals(game.getOtherPlayers().get(0).getCurrentHealth(), 1);
        assertEquals(game.getOtherPlayers().get(0).getMaximumHealth(), 2);
        assertEquals(game.getOtherPlayers().get(0).getCarriedResource(), 3);
        assertEquals(game.getOtherPlayers().get(0).getResourceCapacity(), 4);
        assertEquals(game.getOtherPlayers().get(0).getCollectingSpeed(), 5.0, 0.01);
        assertEquals(game.getOtherPlayers().get(0).getTotalResource(), 6);
        assertEquals(game.getOtherPlayers().get(0).getAttack(), 7);
        assertEquals(game.getOtherPlayers().get(0).getDefence(), 8);
        assertEquals(game.getOtherPlayers().get(0).getPosition().getX(), 9);
        assertEquals(game.getOtherPlayers().get(0).getPosition().getY(), 10);
        assertEquals(game.getOtherPlayers().get(0).getHousePosition().getX(), 11);
        assertEquals(game.getOtherPlayers().get(0).getHousePosition().getY(), 12);
        assertEquals(game.getOtherPlayers().get(0).getScore(), 13);
        assertEquals(game.getOtherPlayers().get(0).getName(), "Player 14");
        assertEquals(game.getOtherPlayers().get(0).getCapacityLevel(), 15);
        assertEquals(game.getOtherPlayers().get(0).getAttackLevel(), 17);
        assertEquals(game.getOtherPlayers().get(0).getDefenceLevel(), 18);
        assertEquals(game.getOtherPlayers().get(0).getHealthLevel(), 20);
        assertEquals(game.getOtherPlayers().get(0).getCollectingSpeedLevel(), 21);
        assertEquals(game.getOtherPlayers().get(0).getItems().size(), 5);
        assertEquals(game.getOtherPlayers().get(0).getItems().get(0), Item.SWORD);
        assertEquals(game.getOtherPlayers().get(0).getItems().get(1), Item.BACKPACK);
        assertEquals(game.getOtherPlayers().get(0).getItems().get(2), Item.PICKAXE);
        assertEquals(game.getOtherPlayers().get(0).getItems().get(3), Item.SHIELD);
        assertEquals(game.getOtherPlayers().get(0).getItems().get(4), Item.HEALTH_POTION);

        /* assert other player */
        assertEquals(game.getOtherPlayers().size(), 2);
        assertEquals(game.getOtherPlayers().get(1).getCurrentHealth(), 22);
        assertEquals(game.getOtherPlayers().get(1).getMaximumHealth(), 23);
        assertEquals(game.getOtherPlayers().get(1).getCarriedResource(), 24);
        assertEquals(game.getOtherPlayers().get(1).getResourceCapacity(), 25);
        assertEquals(game.getOtherPlayers().get(1).getCollectingSpeed(), 26.0, 0.01);
        assertEquals(game.getOtherPlayers().get(1).getTotalResource(), 27);
        assertEquals(game.getOtherPlayers().get(1).getAttack(), 28);
        assertEquals(game.getOtherPlayers().get(1).getDefence(), 29);
        assertEquals(game.getOtherPlayers().get(1).getPosition().getX(), 30);
        assertEquals(game.getOtherPlayers().get(1).getPosition().getY(), 31);
        assertEquals(game.getOtherPlayers().get(1).getHousePosition().getX(), 32);
        assertEquals(game.getOtherPlayers().get(1).getHousePosition().getY(), 33);
        assertEquals(game.getOtherPlayers().get(1).getScore(), 34);
        assertEquals(game.getOtherPlayers().get(1).getName(), "Player 35");
        assertEquals(game.getOtherPlayers().get(1).getCapacityLevel(), 36);
        assertEquals(game.getOtherPlayers().get(1).getAttackLevel(), 37);
        assertEquals(game.getOtherPlayers().get(1).getDefenceLevel(), 38);
        assertEquals(game.getOtherPlayers().get(1).getHealthLevel(), 39);
        assertEquals(game.getOtherPlayers().get(1).getCollectingSpeedLevel(), 40);
        assertEquals(game.getOtherPlayers().get(1).getItems().size(), 0);
    }
}