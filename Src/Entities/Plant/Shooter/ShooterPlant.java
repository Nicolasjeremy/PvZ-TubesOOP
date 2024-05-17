package Src.Entities.Plant.Shooter;

import Src.GameMaps.*;
import Src.Entities.Plant.*;
import Src.Entities.Plant.Projectile.*;

public class ShooterPlant extends Plant {
    public static final int range = -1;

    public ShooterPlant(String name, int health, int attackDmg, int attackSpd, int[] position, int cost, int range, int cooldown, int bulletWaitingTime, GameMap gameMap) {
        super(name, health, attackDmg, attackSpd, position, cost, range, cooldown, gameMap);
    }

    public void attack() {
        int[] position = this.getPosition();
        Projectile projectile = new Projectile(getAttackDmg(), position, getGameMap());
        getGameMap().getTile(position[0], position[1]).addEntity(projectile);
        Thread projectileThread = new Thread(projectile);
        projectileThread.start();
    };
}
