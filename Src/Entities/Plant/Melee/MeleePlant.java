package Src.Entities.Plant.Melee;
import Src.GameMaps.*;
import Src.Entities.Plant.Plant;

public class MeleePlant extends Plant{
    public static final int attackDmg = 5000;
    public static final int attackSpd = 0;
    public static final int range = 1;

    public MeleePlant(String name, int health, int attackDmg, int attackSpd, int[] position, int cost, int range, int cooldown, GameMap gameMap) {
        super(name, health, attackDmg, attackSpd, position, cost, range, cooldown, gameMap);
    }

    public void action () {};

}
