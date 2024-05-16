package Src.Entities.Plant.Shooter;

import Src.Entities.Plant.Projectile.Projectile;
import Src.GameMaps.*;

public class Peashooter extends ShooterPlant {
    public Peashooter(int[] position, GameMap gameMap) {
        super("Peashooter", 100, 25, 4, position, 100, range, 10, 3, gameMap);
    }

    @Override
    public void attack() {
        int[] position = this.getPosition();
        
        Projectile projectile1 = new Projectile(getAttackDmg(), position, getGameMap());
        getGameMap().getTile(position[0], position[1]).addEntity(projectile1);
        Thread projectileThread1 = new Thread(projectile1);
        projectileThread1.start();
    }
}
