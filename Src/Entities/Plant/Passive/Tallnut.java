package Src.Entities.Plant.Passive;
import Src.GameMaps.*;

public class Tallnut extends PassivePlant{
    public Tallnut (int[] position, GameMap gameMap, String imagepath) {
        super("Tallnut", 20000, attackDmg, attackSpd, position, 100, range, 30, gameMap, imagepath);
    }

    public void attack() {

    };
}
