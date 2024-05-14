package Src.Entities.Plant.Shooter;

import Src.GameMaps.*;
import Src.Entities.Plant.*;

public class ShooterPlant extends Plant implements Runnable {
    private int bulletWaitingTime;
    public static final int range = -1;

    public ShooterPlant(String name, int health, int attackDmg, int attackSpd, int[] position, int cost, int range,
            int cooldown, int bulletWaitingTime, GameMap gameMap) {
        super(name, health, attackDmg, attackSpd, position, cost, range, cooldown, gameMap);
        this.bulletWaitingTime = bulletWaitingTime;

    }

    /**
     * Getter bulletWaitingTime
     * 
     * @return bulletWaitingTime
     */
    public int getBulletWaitingTime() {
        return bulletWaitingTime;
    }

    public void action() {
        int[] position = this.getPosition();
        Projectile projectile = new Projectile(getAttackDmg(), position, getGameMap());
        getGameMap().getTile(position[0], position[1]).addEntity(projectile);
        Thread projectileThread = new Thread(projectile);
        projectileThread.start();
    };

    public void run() {
        int i = 0;
        try {
            while (i < 15) {
                Thread.sleep(3000);
                action();
                i++;

            }
        } catch (InterruptedException e) {
        }
    }
}
