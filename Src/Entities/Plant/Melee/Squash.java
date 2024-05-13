package Src.Entities.Plant.Melee;
import Src.GameMaps.*;

public class Squash extends MeleePlant {
    public Squash(String name, int[] position, GameMap gameMap) {
        super(name, 100, attackDmg, attackSpd, position, 50, range, 20, gameMap);
    }
}
