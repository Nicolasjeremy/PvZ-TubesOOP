package Src.Entities.Plant.Melee;

import java.util.ArrayList;
import Src.Entities.Entities;
import Src.Entities.Zombie.Zombie;
import Src.GameMaps.*;

public class Squash extends MeleePlant {
    public Squash(int[] position, GameMap gameMap) {
        super("Squash", 100, attackDmg, attackSpd, position, 50, range, 20, gameMap, "../Image/PlantImage/Squash.png");
    }

    @Override
    public void attack() {
        int[] position = this.getPosition();
        Tile tile = getGameMap().getTile(position[0], position[1]);
        ArrayList<Entities> entitiesInFront = tile.getAllEntities();
        for (Entities entity : entitiesInFront) {
            if (entity instanceof Zombie) {
                Zombie newzombie = (Zombie) entity;
                newzombie.die();
                this.die();
            }
        }
    }
}