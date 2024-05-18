package Src.Entities.Plant.Shooter;
import Src.Entities.Plant.Projectile.SnowProjectile;
import Src.GameMaps.*;

public class Snowpea extends ShooterPlant{
    public Snowpea(int[] position ,GameMap gameMap, String imagepath) {
        super("Snowepea", 100, 25, 4, position, 100, range, 10, 3, gameMap, imagepath);
    }

    @Override
    public void attack() {
        int[] position = this.getPosition();
        SnowProjectile snowProjectile = new SnowProjectile(getAttackDmg(), position, getGameMap(), null);
        getGameMap().getTile(position[0], position[1]).addEntity(snowProjectile);
        Thread SnowProjectileThread = new Thread(snowProjectile);
        SnowProjectileThread.start();
    };
}
