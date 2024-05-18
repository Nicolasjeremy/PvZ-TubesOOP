package Src.Entities.Plant.Melee;

import java.util.ArrayList;
import Src.Entities.Entities;
import Src.Entities.Zombie.Zombie;
import Src.GameMaps.*;

public class TangleKelp extends MeleePlant{
    public TangleKelp(int[] position, GameMap gameMap) {
        super("TangleKelp", 100, attackDmg, attackSpd, position, 50, range, 20, gameMap, "../Image/PlantImage/TangleKelp.png");
    }   
    
    @Override
    public void attack() {
        System.out.println("TangleKelp Attacking!!!!!!!!!!!!!");
        int[] position = getPosition(); 
        Tile tile = getGameMap().getTile(position[0], position[1]); 
        ArrayList<Entities> entitiesInFront = tile.getAllEntities();
        for (Entities entity : entitiesInFront) {
            if (entity instanceof Zombie) {
                Zombie zombie = (Zombie) entity;
                System.out.println("KELPINGGGGG!!!!!!!!!!!!!!!");
                zombie.die();
                this.die();
            }
        }
    }
}
