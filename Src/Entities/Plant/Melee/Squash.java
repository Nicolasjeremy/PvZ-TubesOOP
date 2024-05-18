package Src.Entities.Plant.Melee;

import java.util.ArrayList;
import Src.Entities.Entities;
import Src.Entities.Zombie.Zombie;
import Src.GameMaps.*;

public class Squash extends MeleePlant {
    public Squash(int[] position, GameMap gameMap) {
        super("Squash", 100, attackDmg, attackSpd, position, 50, range, 20, gameMap, "imagepath");
    }

    @Override
    public void attack() {
        System.out.println("Squash Attack!!!!!!!!!!!!!");
        int[] position = this.getPosition(); 
        Tile tile = getGameMap().getTile(position[0], position[1]); 
        ArrayList<Entities> entitiesInFront = tile.getAllEntities();
        for (Entities entity : entitiesInFront) {
            if (entity instanceof Zombie) {
                Zombie newzombie = (Zombie) entity;
                System.out.println("SQUASHINGGG!!!!!!!!!!!!!!!");
                newzombie.die();
                this.die();
            }
        }
    }
}