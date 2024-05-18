package Src.Entities.Plant.Passive;

import Src.Entities.Plant.*;
import Src.GameMaps.*;

public class Lilypad extends PassivePlant{
    private Plant plantOnTop;

    public Lilypad (int[] position, GameMap gameMap) {
        super("LilyPad", 100, attackDmg, attackSpd, position, 25, range, 10, gameMap);
    }

    public boolean isOccupied() { 
        return plantOnTop != null;
    }

    public void occupy(Plant plant) {
        if (!this.isOccupied()) { // kalau masih kosong, baru inisialisasi plant on top
            this.plantOnTop = plant;
            this.health += plant.getHealth();
        } else { // kalau udah ada plant on top, gabisa
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
}

