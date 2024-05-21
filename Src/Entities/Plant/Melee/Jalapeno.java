package Src.Entities.Plant.Melee;

import java.util.ArrayList;
import Src.Entities.Entities;
import Src.Entities.Zombie.Zombie;
import Src.GameMaps.*;

public class Jalapeno extends MeleePlant {
    public Jalapeno(int[] position, GameMap gameMap) {
        super("Jalapeno", 100, attackDmg, attackSpd, position, 50, range, 20, gameMap, "../Image/PlantImage/Jalapeno.png");
    }

    @Override
    public void attack() {
        System.out.println("Jalapeno Attack!!!!!!!!!!!!!");
        int[] position = this.getPosition();
        int row = position[0];
        for (int col = 0; col < 11; col++) {
            // System.out.println(col);
            Tile tile = getGameMap().getTile(row, col);
            ArrayList<Entities> entitiesInTile = tile.getAllEntities();
            for (Entities entity : entitiesInTile) {
                if (entity instanceof Zombie) {
                    Zombie zombie = (Zombie) entity;
                    System.out.println("BURNING ZOMBIES!!!!!!!!!!!!!!!");
                    System.out.println(zombie.getName() + " terbunuh");
                    zombie.die();
                }
            }
        }
        System.out.println("Jalapeno Mati!!!!!!!!!!!!!");
        this.die();
        Thread.interrupted();
    }
}

