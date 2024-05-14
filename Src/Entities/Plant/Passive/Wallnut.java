package Src.Entities.Plant.Passive;
import Src.GameMaps.*;

public class Wallnut extends PassivePlant{
    public Wallnut (int[] position, GameMap gameMap) {
        super("Wallnut", 1000, attackDmg, attackSpd, position, 50, range, 20, gameMap);
    }
}
