package Src.Entities.Plant.Melee;

import java.util.ArrayList;

import Src.Entities.Entities;
import Src.Entities.Zombie.Zombie;
import Src.GameMaps.*;

public class Chomper extends MeleePlant{
    private boolean isChewing = false;

    public Chomper(int[] position, GameMap gameMap) {
        super("Chomper", 100, attackDmg, attackSpd, position, 50, range, 20, gameMap);
        // Thread chomperThread = new Thread(this);
        // chomperThread.start();
    }

    @Override
    public void attack() {
        int[] position = this.getPosition(); 
        Tile tile = getGameMap().getTile(position[0], position[1]); 
        ArrayList<Entities> entitiesInFront = tile.getAllEntities();
        for (Entities entity : entitiesInFront) {
            if (entity instanceof Zombie) {
                Zombie zombie = (Zombie) entity;
                if (!isChewing) {
                    eatZombie(zombie);
                } else {
                    // masih mengunyah, zombie bisa makan
                }
                return; 
            }
        }
        super.attack();
    }


    public void eatZombie(Zombie zombie) {
        isChewing = true;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isChewing = false;
        zombie.die();
    }
}