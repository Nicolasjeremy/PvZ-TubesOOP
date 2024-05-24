package Src.Entities.Plant.Melee;

import java.util.ArrayList;
import Src.Entities.Entities;
// import Src.Entities.Plant.Shooter.Snowpea;
import Src.Entities.Zombie.Zombie;
import Src.GameMaps.*;

public class TangleKelp extends MeleePlant{
    private static int planttime = 999;
    public TangleKelp(int[] position, GameMap gameMap) {
        super("TangleKelp", 100, attackDmg, attackSpd, position, 50, range, 20, gameMap, "../Image/PlantImage/TangleKelp.png", 0);
    }   
    
    @Override
    public void attack() {
        int[] position = getPosition(); 
        Tile tile = getGameMap().getTile(position[0], position[1]); 
        ArrayList<Entities> entitiesInFront = tile.getAllEntities();
        for (Entities entity : entitiesInFront) {
            if (entity instanceof Zombie) {
                Zombie zombie = (Zombie) entity;
                zombie.die();
                this.die();
            }
        }
    }

    public static int getLastPlantedTime(){
        return planttime;
    }

    public static void setLastPlantedTime(int planttime){
        TangleKelp.planttime = planttime;
    }
}
