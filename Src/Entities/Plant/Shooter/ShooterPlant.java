package Src.Entities.Plant.Shooter;

import Src.GameMaps.*;
import Src.Entities.Plant.*;
// import Src.Entities.Plant.Projectile.*;

public class ShooterPlant extends Plant {
    public static final int range = -1;

    public ShooterPlant(String name, int health, int attackDmg, int attackSpd, int[] position, int cost, int range, int cooldown, int bulletWaitingTime, GameMap gameMap, String imagepath) {
        super(name, health, attackDmg, attackSpd, position, cost, range, cooldown, gameMap, imagepath);
    }

    public void attack() {
    };

    public void run() {
        System.out.println("SHOOTTINGGG DA ZOMBIEEE!!!!!!!");
        try {
            while (true) {
                Thread.sleep(attackSpd * 1000);
                attack();
            }
        } catch (InterruptedException e) {
        }
    }
}
