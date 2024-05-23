package Src.Entities.Plant.Melee;

import java.util.ArrayList;
import Src.Entities.Entities;
import Src.Entities.Zombie.Zombie;
import Src.GameMaps.*;

public class Jalapeno extends MeleePlant {
    private ArrayList<Zombie> listZombie;

    public Jalapeno(int[] position, GameMap gameMap) {
        super("Jalapeno", 100, attackDmg, attackSpd, position, 50, range, 20, gameMap, "../Image/PlantImage/Jalapeno.png", 0);
        listZombie = new ArrayList<>();
    }

    @Override
    public synchronized void attack() {
        System.out.println("Jalapeno Attack!!!!!!!!!!!!!");
        int[] position = this.getPosition();
        int row = position[0];

        Tile tile;  
        for (int col = 0; col < 11; col++) {
            tile = getGameMap().getTile(row, col);
            for (Entities entity : tile.getAllEntities()) { 
                if (entity instanceof Zombie) {
                    Zombie zombie = (Zombie) entity;
                    zombie.stopThread(); // Stop the zombie thread
                    listZombie.add(zombie);
                }
            }
        }

        // Wait for all zombie threads to stop
        waitForZombieThreadsToStop();

        // Remove zombies from the game map
        for (Zombie zombie : listZombie) {
            System.out.println(zombie.getName() + " terbunuh");
            synchronized (zombie) {
                zombie.setHealth(zombie.getHealth() - this.getAttackDmg());
                zombie.die(); // This will also join the zombie threads
            }
        }

        this.die(); // Optionally, call die() method if needed
    }

    // Existing code...

    private void waitForZombieThreadsToStop() {
        // Wait for all zombie threads to stop
        for (Zombie zombie : listZombie) {
            Thread thread = zombie.getZombiThread();
            try {
                if (thread != null) {
                    thread.join(); // Wait for the zombie thread to stop
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Set the interrupted flag again
                System.err.println("Interrupted while waiting for zombie thread to stop: " + e.getMessage());
            }
        }
    }

    // Existing code...
}