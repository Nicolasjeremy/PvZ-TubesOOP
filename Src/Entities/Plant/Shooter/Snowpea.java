package Src.Entities.Plant.Shooter;
import Src.GameMaps.*;

public class Snowpea extends ShooterPlant{
    public Snowpea(String name, int[] position ,GameMap gameMap) {
        super(name, 100, 25, 4, position, 100, range, 10, 3, gameMap);
    }
}
