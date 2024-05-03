package Src.Entities.Plant.Passive;

import Src.Entities.Plant.Plant;

public class PassivePlant extends Plant{
    public static final int attackDmg = 0;
    public static final int attackSpd = 0;
    public static final int range = 0;

    public PassivePlant(String name, int health, int attackDmg, int attackSpd, int[] position, int cost, int range, int cooldown) {
        super(name, health, attackDmg, attackSpd, position, cost, range, cooldown);
    }

    public void action () {};
}
