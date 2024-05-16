package Src.Entities.Plant.Melee;

import java.util.ArrayList;
import Src.Entities.Entities;
import Src.Entities.Zombie.Zombie;
import Src.GameMaps.*;

public class TangleKelp extends MeleePlant{
    public TangleKelp(int[] position, GameMap gameMap) {
        super("TangleKelp", 100, attackDmg, attackSpd, position, 50, range, 20, gameMap);
    }   
    
    @Override
    public void attack() {
        int[] position = getPosition(); 
        Tile tile = getGameMap().getTile(position[0], position[1]); 
        ArrayList<Entities> entitiesInFront = tile.getEntities();
        for (Entities entity : entitiesInFront) {
            if (entity instanceof Zombie) {
                Zombie zombie = (Zombie) entity;
                zombie.die(getGameMap());
                this.die(getGameMap());
            }
        }
    }
}
