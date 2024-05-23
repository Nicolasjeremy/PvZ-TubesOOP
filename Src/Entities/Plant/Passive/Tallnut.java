package Src.Entities.Plant.Passive;

import Src.Entities.Plant.Shooter.Snowpea;
import Src.GameMaps.*;

public class Tallnut extends PassivePlant {
    private static int planttime = 999;

    public Tallnut(int[] position, GameMap gameMap) {
        super("Tallnut", 20000, attackDmg, attackSpd, position, 100, range, 30, gameMap,
                "../Image/PlantImage/Tallnut.png", 0);
    }

    public void attack() {

    };

    public static int getLastPlantedTime() {
        return planttime;
    }

    public static void setLastPlantedTime(int planttime) {
        Tallnut.planttime = planttime;
    }
}
