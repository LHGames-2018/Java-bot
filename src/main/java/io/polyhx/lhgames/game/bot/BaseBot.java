package io.polyhx.lhgames.game.bot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import io.polyhx.lhgames.game.Item;
import io.polyhx.lhgames.game.Upgrade;
import io.polyhx.lhgames.game.action.*;
import io.polyhx.lhgames.game.point.IPoint;

import java.io.*;

/**
 * This class hides the basic code the bot.
 */
public class BaseBot {
    /**
     * The path to the data file.
     */
    private final String fBotDataPath;

    /**
     * The bot data used for saving data across multiple deployments.
     */
    protected BotData fBotData;

    /**
     * Constructor.
     */
    public BaseBot() {
        fBotDataPath = "/data/bot.json";
        fBotData = new BotData();

        /* try to load the bot's data */
        load();
    }

    /**
     * This method saves the bot's data into the persistent storage.
     */
    public void save() {
        /* create the writer */
        FileWriter writer = null;
        try {
            writer = new FileWriter(fBotDataPath);
        } catch (IOException e) {
            System.out.println("Failed to save bot's data: " + e);
            return;
        }

        /* create the JSON serializer */
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        /* serialize the bot's data */
        try {
            gson.toJson(fBotData, writer);
            writer.flush();
            writer.close();
        } catch (JsonIOException e) {
            System.out.println("Failed to save bot's data: " + e);
            return;
        } catch (IOException e) {
            System.out.println("Failed to save bot's data: " + e);
            return;
        }

        System.out.println("should be saved");
    }

    /**
     * This method loads the bot's data from the persistent storage.
     */
    public void load() {
        /* make sure the data file exists */
        File datafile = new File(fBotDataPath);
        if (!datafile.exists()) {
            System.out.println("Failed to load bot's data: file doesn't exist");
            return;
        }

        /* create the reader */
        FileReader reader = null;
        try {
            reader = new FileReader(datafile);
        } catch (FileNotFoundException e) {
            System.out.println("Failed to load bot's data: " + e);
            return;
        }

        /* deserialize the data */
        BotData loaded;
        try {
            Gson gson = new Gson();
            loaded = gson.fromJson(reader, BotData.class);
        } catch (JsonSyntaxException e) {
            System.out.println("Failed to load bot's data: " + e);
            return;
        }

        /* use the new data */
        if (loaded != null) {
            fBotData = loaded;
        }
    }

    /**
     * This method creates a steal action.
     *
     * @param point The point we want to steal from.
     * @return The steal action.
     */
    static StealAction createStealAction(IPoint point) {
        return new StealAction(point);
    }

    /**
     * This method creates a melee attack action.
     *
     * @param point The point we want to attack.
     * @return The attack action.
     */
    static MeleeAttackAction createMeleeAttackAction(IPoint point) {
        return new MeleeAttackAction(point);
    }

    /**
     * This method creates a collect action.
     *
     * @param point The point we want to collect from.
     * @return The collect action.
     */
    static CollectAction createCollectAction(IPoint point) {
        return new CollectAction(point);
    }

    /**
     * This method creates a move action.
     *
     * @param point The point we want to move to.
     * @return The move action.
     */
    static MoveAction createMoveAction(IPoint point) {
        return new MoveAction(point);
    }

    /**
     * This method creates an upgrade action.
     *
     * @param upgrade The upgrade we want to perform.
     * @return The upgrade action.
     */
    static UpgradeAction createUpgradeAction(Upgrade upgrade) {
        return new UpgradeAction(upgrade);
    }

    /**
     * This method creates a purchase action.
     *
     * @param item The item we want to purchase.
     * @return The upgrade action.
     */
    static PurchaseAction createPurchaseAction(Item item) {
        return new PurchaseAction(item);
    }

    /**
     * This action creates an heal action.
     *
     * @return The heal action.
     */
    static HealAction createHealAction() {
        return new HealAction();
    }
}
