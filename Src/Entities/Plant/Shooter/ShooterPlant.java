package Src.Entities.Plant.Shooter;

import Src.GameMaps.*;
import Src.MainMenu.Gameplay;
import Src.Entities.Entities;
import Src.Entities.Plant.*;
// import Src.Entities.Plant.Projectile.*;
import Src.Entities.Zombie.Zombie;

public class ShooterPlant extends Plant {
    public static final int range = -1;

    public ShooterPlant(String name, int health, int attackDmg, int attackSpd, int[] position, int cost, int range,
            int cooldown, int bulletWaitingTime, GameMap gameMap, String imagepath, int lastPlantedTime) {
        super(name, health, attackDmg, attackSpd, position, cost, range, cooldown, gameMap, imagepath, lastPlantedTime);
    }

    public void attack() {
    };

    public boolean zombiechecker() {
        int row = this.getPosition()[0];
        boolean status = false;
        int col = 0;
        while (!status && col < 10) {
            Tile check_tile = this.getGameMap().getTile(row, col);
            for (Entities zombie : check_tile.getAllEntities()) {
                if (zombie instanceof Zombie) {
                    status = true;
                }
            }
            col++;
        }
        return status;
    }

    public void run() {
        try {
            while (!Gameplay.getIsEnd() && this.getHealth() > 0) {
                if (zombiechecker()) {
                    attack();
                    Thread.sleep(attackSpd * 1000);
                }
                if (this.getCooldown()!=0){
                    Thread.sleep(1000);
                    this.setCooldown(this.getCooldown()-1);
                }
            }
        } catch (InterruptedException e) {
        }
    }
}
