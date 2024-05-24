package Src.Entities.Plant.Passive;

import Src.GameMaps.*;

public class Wallnut extends PassivePlant {
    private static int planttime = 999;

    public Wallnut(int[] position, GameMap gameMap) {
        super("Wallnut", 1000, attackDmg, attackSpd, position, 50, range, 20, gameMap,
                "../Image/PlantImage/Wallnut.png", 0);
    }

    public void attack() {

    };

    public static int getLastPlantedTime() {
        return planttime;
    }

    public static void setLastPlantedTime(int planttime) {
        Wallnut.planttime = planttime;
    }
}
