package Src.Entities.Plant.Passive;
import Src.GameMaps.*;

public class Tallnut extends PassivePlant{
    public Tallnut (int[] position, GameMap gameMap) {
        super("Tallnut", 20000, attackDmg, attackSpd, position, 100, range, 30, gameMap, "../Image/PlantImage/Tallnut.png", 0);
    }

    public void attack() {

    };
}
