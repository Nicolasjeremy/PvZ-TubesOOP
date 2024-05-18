package Src.Entities.Plant;

import Src.GameMaps.*;
import Src.Entities.Entities;

public abstract class Plant extends Entities implements Runnable {
    private Integer cost;
    private Integer range;
    private Integer cooldown;

    public Plant(String name, int health, int attackDmg, int attackSpd, int[] position, int cost, int range,
            int cooldown, GameMap gameMap) {
        super(name, health, attackDmg, attackSpd, position, gameMap);
        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
    }

    public int getCost() {
        return cost;
    }

    public int getRange() {
        return range;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void run() {
        try {
            while (true) {
                Thread.sleep(attackSpd * 1000);
                attack();
            }
        } catch (InterruptedException e) {
        }
    }

    public abstract void attack();
}
