package Src.Entities.Plant.Passive;

import Src.Entities.Plant.*;
import Src.GameMaps.*;

public class Lilypad extends PassivePlant {
    private Plant plantOnTop;
    private static int planttime = 999;

    public Lilypad(int[] position, GameMap gameMap) {
        super("LilyPad", 100, attackDmg, attackSpd, position, 25, range, 10, gameMap, "../Image/PlantImage/Lilypad.png",
                0);
    }

    public boolean isOccupied() {
        return plantOnTop != null;
    }

    public void occupy(Plant plant) {
        if (!this.isOccupied()) { 
            this.plantOnTop = plant;
            this.health += plant.getHealth();
        } else {
            System.out.println("Lilypad is already occupied.");
        }
    }

    public void checkAndRemove() {
        if (this.health <= 0) {
            this.plantOnTop = null;
        }
    }

    @Override
    public void setHealth(int health) {
        super.setHealth(health);
        if (this.health <= 0 && this.plantOnTop != null) {
            this.plantOnTop.setHealth(0);
            this.plantOnTop.die();
        }
    }

    public void attack() {
    };

    public static int getLastPlantedTime() {
        return planttime;
    }

    public static void setLastPlantedTime(int planttime) {
        Lilypad.planttime = planttime;
    }
}
