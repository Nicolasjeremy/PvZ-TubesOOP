package Src.Entities.Plant.Shooter;

import Src.Entities.Plant.Projectile.SnowProjectile;
import Src.GameMaps.*;

public class Snowpea extends ShooterPlant {
    private static int planttime = 999;

    public Snowpea(int[] position, GameMap gameMap) {
        super("Snowpea", 100, 25, 4, position, 100, range, 10, 3, gameMap, "../Image/PlantImage/Snowpea.png", 0);
    }

    @Override
    public void attack() {
        int[] position = this.getPosition();
        SnowProjectile snowProjectile = new SnowProjectile(getAttackDmg(), position, getGameMap());
        getGameMap().getTile(position[0], position[1]).addEntity(snowProjectile);
        Thread SnowProjectileThread = new Thread(snowProjectile);
        SnowProjectileThread.start();
    };

    public static int getLastPlantedTime() {
        return planttime;
    }

    public static void setLastPlantedTime(int planttime) {
        Snowpea.planttime = planttime;
    }
}
