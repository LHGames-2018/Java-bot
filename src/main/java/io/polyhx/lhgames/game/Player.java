////////////////////////////////////////////
//         DO NOT TOUCH THIS FILE         //
////////////////////////////////////////////

package io.polyhx.lhgames.game;

import io.polyhx.lhgames.game.point.IPoint;
import io.polyhx.lhgames.game.point.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains the information about a player.
 */
public class Player implements IPoint {
    /**
     * The position of the player.
     */
    private final Point fPosition;

    /**
     * The position of the house of the player.
     */
    private final Point fHouse;

    /**
     * The current health of the player.
     */
    private final int fHealthCurrent;

    /**
     * The maximum health of the player.
     */
    private final int fHealthMax;

    /**
     * The current resources carried by the player.
     */
    private final int fResourceCurrent;

    /**
     * The maximum resource capacity of the player.
     */
    private final int fResourceCapacity;

    /**
     * The total resource of the player (carried + at the house).
     */
    private final int fResourceTotal;

    /**
     * The attack of the player.
     */
    private final int fAttack;

    /**
     * The defence of the player.
     */
    private final int fDefence;

    /**
     * The score of the player.
     */
    private final int fScore;

    /**
     * The collecting speed of the player.
     */
    private final float fCollectingSpeed;

    /**
     * The name of the player.
     */
    private final String fName;

    /**
     * The current upgrades of the player.
     */
    private final Upgrades fUpgrades;

    /**
     * The list of carried items by the player.
     */
    private final List<Item> fItems;

    /**
     * Constructor.
     *
     * @param pos              The position of the player.
     * @param house            The position of the house of the player.
     * @param healthCurrent    The current health of the player.
     * @param healthMax        The maximum health of the player.
     * @param resourceCurrent  The current carried resource of the player.
     * @param resourceCapacity The maximum resource capacity of the player.
     * @param resourceTotal    The total resource of the player.
     * @param attack           The attack of the player.
     * @param defence          The defence of the player.
     * @param score            The score of the player.
     * @param collectingSpeed  The collecting speed of the player.
     * @param name             The name of the player.
     * @param upgrades         The upgrades of the player.
     * @param items            The carried items.
     */
    public Player(Point pos, Point house, int healthCurrent, int healthMax, int resourceCurrent, int resourceCapacity,
                  int resourceTotal, int attack, int defence, int score, float collectingSpeed, String name,
                  int[] upgrades, int[] items) {
        fPosition = pos;
        fHouse = house;
        fHealthCurrent = healthCurrent;
        fHealthMax = healthMax;
        fResourceCurrent = resourceCurrent;
        fResourceCapacity = resourceCapacity;
        fResourceTotal = resourceTotal;
        fAttack = attack;
        fDefence = defence;
        fScore = score;
        fCollectingSpeed = collectingSpeed;
        fName = name;

        /* make sure we have an upgrade array */
        if (upgrades == null) {
            upgrades = new int[]{0, 0, 0, 0, 0};
        }

        /* make the array of length 5 if it isn't for some reason */
        if (upgrades.length < 5) {
            System.out.println("Upgrade array has less than 5 elements: " + upgrades);
            int[] newUpgrades = new int[]{0, 0, 0, 0, 0};
            for (int i = 0; i < newUpgrades.length; i++) {
                newUpgrades[i] = (i < upgrades.length) ? upgrades[i] : 0;
            }
        }

        /* warning if the array is of a size higher than 5 */
        if (upgrades.length > 5) {
            System.out.println("Upgrade array has more than 5 elements: " + upgrades);
        }

        /* create the upgrades */
        fUpgrades = new Upgrades(
                upgrades[Upgrade.CARRYING_CAPACITY.getID()],
                upgrades[Upgrade.MAXIMUM_HEALTH.getID()],
                upgrades[Upgrade.COLLECTING_SPEED.getID()],
                upgrades[Upgrade.ATTACK.getID()],
                upgrades[Upgrade.DEFENCE.getID()]
        );

        /* create the items */
        fItems = new ArrayList<>();
        for (int item : items) {
            fItems.add(Item.fromID(item));
        }
    }

    /**
     * This method returns the position of the player.
     *
     * @return The position of the player.
     */
    public Point getPosition() {
        return fPosition;
    }

    /**
     * This method returns the position of the house of the player.
     *
     * @return The position of the house of the player.
     */
    public Point getHousePosition() {
        return fHouse;
    }

    /**
     * This method returns the current health of the player.
     *
     * @return The current health of the player.
     */
    public int getCurrentHealth() {
        return fHealthCurrent;
    }

    /**
     * This method returns the maximum health of the player.
     *
     * @return The maximum health of the player.
     */
    public int getMaximumHealth() {
        return fHealthMax;
    }

    /**
     * This method returns the carried resources of the player.
     *
     * @return The carried resources of the player.
     */
    public int getCarriedResource() {
        return fResourceCurrent;
    }

    /**
     * This method returns the capacity of resource of the player.
     *
     * @return The capacity of the player.
     */
    public int getResourceCapacity() {
        return fResourceCapacity;
    }

    /**
     * This method returns the total resources of the player, that is the amount carried and the amount at the house.
     *
     * @return The total amount of resources of the player.
     */
    public int getTotalResource() {
        return fResourceTotal;
    }

    /**
     * This method returns the attack of the player.
     *
     * @return The attack of the player.
     */
    public int getAttack() {
        return fAttack;
    }

    /**
     * This method returns the defence of the player.
     *
     * @return The defence of the player.
     */
    public int getDefence() {
        return fDefence;
    }

    /**
     * This method returns the score of the player.
     *
     * @return The score of the player.
     */
    public int getScore() {
        return fScore;
    }

    /**
     * This method returns the collecting speed of the player.
     *
     * @return The collecting speed of the player.
     */
    public float getCollectingSpeed() {
        return fCollectingSpeed;
    }

    /**
     * This method returns the name of the player.
     *
     * @return The name of the player.
     */
    public String getName() {
        return fName;
    }

    @Override
    public int getX() {
        return fPosition.getX();
    }

    @Override
    public int getY() {
        return fPosition.getY();
    }

    /**
     * The class represents the upgrades of a player.
     */
    class Upgrades {
        /**
         * The current carrying capacity level.
         */
        final int fCarryingCapacity;

        /**
         * The current maximum health level.
         */
        final int fMaximumHealth;

        /**
         * The current collecting speed level.
         */
        final int fCollectingSpeed;

        /**
         * The current attack level.
         */
        final int fAttack;

        /**
         * The current defence level.
         */
        final int fDefence;

        /**
         * Constructor.
         *
         * @param capacity The current carrying capacity level.
         * @param health   The current maximum health level.
         * @param speed    The current collecting speed level.
         * @param attack   The current attack level.
         * @param defence  The current defence level.
         */
        Upgrades(int capacity, int health, int speed, int attack, int defence) {
            fCarryingCapacity = capacity;
            fMaximumHealth = health;
            fCollectingSpeed = speed;
            fAttack = attack;
            fDefence = defence;
        }
    }

    /**
     * This method returns the carrying capacity level.
     *
     * @return The carrying capacity level.
     */
    public int getCapacityLevel() {
        return fUpgrades.fCarryingCapacity;
    }

    /**
     * This method returns the health level.
     *
     * @return The health level.
     */
    public int getHealthLevel() {
        return fUpgrades.fMaximumHealth;
    }

    /**
     * This method returns the collecting speed level.
     *
     * @return The collecting speed level.
     */
    public int getCollectingSpeedLevel() {
        return fUpgrades.fCollectingSpeed;
    }

    /**
     * This method returns the attack level.
     *
     * @return The attack level.
     */
    public int getAttackLevel() {
        return fUpgrades.fAttack;
    }

    /**
     * This method returns the defence level.
     *
     * @return The defence level.
     */
    public int getDefenceLevel() {
        return fUpgrades.fDefence;
    }

    /**
     * This method returns the carried items.
     *
     * @return The carried items.
     */
    public List<Item> getItems() {
        return fItems;
    }
}
