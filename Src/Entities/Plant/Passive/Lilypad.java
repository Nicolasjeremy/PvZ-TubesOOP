package Src.Entities.Plant.Passive;
import Src.GameMaps.*;

public class Lilypad extends PassivePlant{
    public Lilypad (int[] position, GameMap gameMap) {
        super("LilyPad", 100, attackDmg, attackSpd, position, 25, range, 10, gameMap);
    }    
}
