package Src.Entities.Plant.Passive;

import Src.Entities.Plant.Shooter.Snowpea;
import Src.GameMaps.*;

public class Sunflower extends PassivePlant {
    private int sunWaitingTime;
    private static int planttime = 999;
    Sun sun = new Sun();

    public Sunflower(int[] position, GameMap gameMap) {
        super("Sunflower", 300, attackDmg, attackSpd, position, 50, range, 10, gameMap,
                "../Image/PlantImage/Sunflower.png", 0);
        this.sunWaitingTime = 3;
    }

    @Override
    public void attack() {
        try {
            while (true) {
                Thread.sleep(sunWaitingTime * 1000);
                // System.out.println("\nREGAININGG SUNNNN!!!!!!!!!!!");
                Sun.addSun(25);
            }
        } catch (InterruptedException e) {
            System.out.println("Sunflower gagal");
        }
    }

    public static int getLastPlantedTime() {
        return planttime;
    }

    public static void setLastPlantedTime(int planttime) {
        Sunflower.planttime = planttime;
    }
}
