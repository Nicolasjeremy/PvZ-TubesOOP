package Src.Entities.Plant.Melee;

import java.util.ArrayList;
import Src.Entities.Entities;
// import Src.Entities.Plant.Shooter.Snowpea;
import Src.Entities.Zombie.Zombie;
import Src.GameMaps.*;

public class Jalapeno extends MeleePlant {
    private ArrayList<Zombie> listZombie;
    private static int planttime = 999;

    public Jalapeno(int[] position, GameMap gameMap) {
        super("Jalapeno", 100, attackDmg, attackSpd, position, 50, range, 20, gameMap,
                "../Image/PlantImage/Jalapeno.png", 0);
        listZombie = new ArrayList<>();
    }

    @Override
    public synchronized void attack() {
        int[] position = this.getPosition();
        int row = position[0];

        Tile tile;
        for (int col = 0; col < 11; col++) {
            tile = getGameMap().getTile(row, col);
            for (Entities entity : tile.getAllEntities()) {
                if (entity instanceof Zombie) {
                    Zombie zombie = (Zombie) entity;
                    zombie.stopThread(); 
                    listZombie.add(zombie);
                }
            }
        }

        waitForZombieThreadsToStop();

        for (Zombie zombie : listZombie) {
            synchronized (zombie) {
                zombie.setHealth(zombie.getHealth() - this.getAttackDmg());
                zombie.die(); 
            }
        }

        this.die();
    }

    private void waitForZombieThreadsToStop() {
        // Wait for all zombie threads to stop
        for (Zombie zombie : listZombie) {
            Thread thread = zombie.getZombiThread();
            try {
                if (thread != null) {
                    thread.join(); 
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Interrupted while waiting for zombie thread to stop: " + e.getMessage());
            }
        }
    }

    public static int getLastPlantedTime() {
        return planttime;
    }

    public static void setLastPlantedTime(int planttime) {
        Jalapeno.planttime = planttime;
    }
}
