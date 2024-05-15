package Src.Entities.Plant;

import Src.GameMaps.*;
import Src.Entities.Entities;

public abstract class Plant extends Entities {
    private Integer cost;
    private Integer range;
    private Integer cooldown;
    private int[] position;

    public Plant(String name, int health, int attackDmg, int attackSpd, int[] position, int cost, int range,
            int cooldown, GameMap gameMap) {
        super(name, health, attackDmg, attackSpd, position, gameMap);
        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
    }

    /**
     * Getter cost
     * 
     * @return cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * Getter range
     * 
     * @return range
     */
    public int getRange() {
        return range;
    }

    /**
     * Getter cooldown
     * 
     * @return cooldown
     */
    public int getCooldown() {
        return cooldown;
    }

    public abstract void attack();
}