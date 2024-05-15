package Src.Entities.Plant.Shooter;

import Src.GameMaps.*;
import Src.Entities.Plant.Projectile.Projectile;

public class Repeater extends ShooterPlant{
    public Repeater (int[] position, GameMap gameMap) {
        super("Repeater", 100, 25, 4, position, 150, range, 10, 3, gameMap);
    }
    @Override
    public void attack() {
        int[] position = this.getPosition();
        
        Projectile projectile1 = new Projectile(getAttackDmg(), position, getGameMap());
        getGameMap().getTile(position[0], position[1]).addEntity(projectile1);
        Thread projectileThread1 = new Thread(projectile1);
        projectileThread1.start();
        
        try {
            Thread.sleep(500); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        Projectile projectile2 = new Projectile(getAttackDmg(), position, getGameMap());
        getGameMap().getTile(position[0], position[1]).addEntity(projectile2);
        Thread projectileThread2 = new Thread(projectile2);
        projectileThread2.start();
    }
}
