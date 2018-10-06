package io.polyhx.lhgames.game.bot;

/**
 * This class can be used for saving data that can be reused across multiples deployments.
 * <p>
 * In order to use it, simple add fields to this class. You can add the `transient` keyword to the fields you do not
 * want to save/load.
 * <p>
 * It is recommended not to rename the field names across multiple deployments as this may result in deserialization
 * errors. The data is saved in JSON using the field names.
 */
public class BotData {

}
