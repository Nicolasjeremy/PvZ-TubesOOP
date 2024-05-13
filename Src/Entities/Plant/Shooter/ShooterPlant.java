package Src.Entities.Plant.Shooter;
import Src.GameMaps.*;
import Src.Entities.Plant.Plant;

public class ShooterPlant extends Plant{
    private int bulletWaitingTime;
    public static final int range = -1;

    public ShooterPlant(String name, int health, int attackDmg, int attackSpd, int[] position, int cost, int range, int cooldown, int bulletWaitingTime, GameMap gameMap) {
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

    public void action () {};
}
