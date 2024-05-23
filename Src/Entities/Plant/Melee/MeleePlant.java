package Src.Entities.Plant.Melee;

import Src.GameMaps.*;
import Src.Entities.Entities;
import Src.Entities.Plant.Plant;
import Src.Entities.Zombie.*;

public class MeleePlant extends Plant {
    public static final int attackDmg = 5000;
    public static final int attackSpd = 0;
    public static final int range = 1;

    public MeleePlant(String name, int health, int attackDmg, int attackSpd, int[] position, int cost, int range,
            int cooldown, GameMap gameMap, String imagepath, int lastPlantedTime) {
        super(name, health, attackDmg, attackSpd, position, cost, range, cooldown, gameMap, imagepath, lastPlantedTime);
    }

    public void attack() {
    };

    public boolean zombiechecker() {
        int row = this.getPosition()[0];
        boolean status = false;
        int col = 0;
        while (!status && col < 11) {
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
        while (true) {
            boolean kill = false;
            try {
                if (this.getCooldown()!=0){
                    Thread.sleep(1000);
                    this.setCooldown(this.getCooldown()-1);
                }
                if (this instanceof Jalapeno) {
                    if (zombiechecker() && !kill) {
                        // this.attack();
                        // System.out.println("Zombie exist in this row");
                        // System.out.println("Jalapeno ready to attack");
                        this.attack();
                        this.die();
                        kill = true;
                        break;
                    }
                } else {
                    boolean cek = false;
                    Tile zombieDetected = this.getGameMap().getTile(getPosition()[0], getPosition()[1]);
                    for (Entities zombie : zombieDetected.getAllEntities()) {
                        if (zombie instanceof Zombie) {
                            cek = true;
                        }
                    }
                    if (cek == true) {
                        this.attack();
                    }
                }
            } catch (Exception e) {
            }
        }
    }
}
