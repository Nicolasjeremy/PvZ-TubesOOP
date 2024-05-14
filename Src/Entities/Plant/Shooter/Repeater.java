package Src.Entities.Plant.Shooter;
import Src.GameMaps.*;

public class Repeater extends ShooterPlant{
    public Repeater (int[] position, GameMap gameMap) {
        super("Repeater", 100, 25, 4, position, 150, range, 10, 3, gameMap);
    }
}
