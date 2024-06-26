package Src.GameMaps;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Src.Entities.Entities;
import Src.Entities.Zombie.*;
import Src.MainMenu.*;

public class ZombieManager extends ZombieSpawn implements Runnable {
    private List<ZombieFactory<? extends Zombie>> zombieFactories;
    private List<ZombieFactory<? extends Zombie>> zombieFactoriesWater;
    private Random random;
    private GameMap gameMap;
    private static final Object lock = new Object();

    public ZombieManager(GameMap gameMap) {
        this.gameMap = gameMap;
        this.zombieFactories = new ArrayList<>();
        this.zombieFactoriesWater = new ArrayList<>();
        this.random = new Random();

        zombieFactories.add(new ZRaul(null, gameMap));
        zombieFactories.add(new ZConeHead(null, gameMap));
        zombieFactories.add(new ZBucketHead(null, gameMap));
        zombieFactories.add(new ZDipsy(null, gameMap));
        zombieFactoriesWater.add(new ZDolphonRider(null, gameMap));
        zombieFactoriesWater.add(new ZDuckyTube(null, gameMap));
        zombieFactories.add(new ZLala(null, gameMap));
        zombieFactories.add(new ZNormal(null, gameMap));
        zombieFactories.add(new ZPoleVault(null, gameMap));
        zombieFactories.add(new ZAsep(null, gameMap));
    }

    public void spawnZombie() {
        synchronized (lock) {
            for (int i = 0; i <= 5; i++) {
                int[] position = { i, 10 };
                try {
                    if (Gameplay.getCurrentTime() >= 100) {
                        if (checkZombiecount(gameMap) < 10) {
                            if (i == 2 || i == 3) {
                                int randomspwn = random.nextInt(10);
                                if (randomspwn < 3) { 
                                    int randomIndex = random.nextInt(zombieFactoriesWater.size());
                                    ZombieFactory<? extends Zombie> selectedFactory = zombieFactoriesWater
                                            .get(randomIndex);
                                    Zombie newZombie = selectedFactory.createZombie(position, gameMap);
                                    gameMap.getTile(position[0], position[1]).addEntity(newZombie);
                                    Thread zombieThread = new Thread(newZombie);
                                    zombieThread.start();
                                }
                            } else {
                                int randomspwn = random.nextInt(10);
                                if (randomspwn < 3) { // 30% chance to spawn
                                    int randomIndex = random.nextInt(zombieFactories.size());
                                    ZombieFactory<? extends Zombie> selectedFactory = zombieFactories.get(randomIndex);
                                    Zombie newZombie = selectedFactory.createZombie(position, gameMap);
                                    gameMap.getTile(position[0], position[1]).addEntity(newZombie);
                                    Thread zombieThread = new Thread(newZombie);
                                    zombieThread.start();
                                }
                            }
                        }

                    } else {
                        if (checkZombiecount(gameMap) < 25) {
                            if (i == 2 || i == 3) { // If tile 2 and 3, spawn water zombies
                                int randomspwn = random.nextInt(10);
                                if (randomspwn < 3) { // 30% chance to spawn
                                    int randomIndex = random.nextInt(zombieFactoriesWater.size());
                                    ZombieFactory<? extends Zombie> selectedFactory = zombieFactoriesWater.get(randomIndex);
                                    Zombie newZombie = selectedFactory.createZombie(position, gameMap);
                                    gameMap.getTile(position[0], position[1]).addEntity(newZombie);
                                    Thread zombieThread = new Thread(newZombie);
                                    zombieThread.start();
                                }
                            } else {
                                int randomspwn = random.nextInt(10);
                                if (randomspwn < 3) { // 30% chance to spawn
                                    int randomIndex = random.nextInt(zombieFactories.size());
                                    ZombieFactory<? extends Zombie> selectedFactory = zombieFactories.get(randomIndex);
                                    Zombie newZombie = selectedFactory.createZombie(position, gameMap);
                                    gameMap.getTile(position[0], position[1]).addEntity(newZombie);
                                    Thread zombieThread = new Thread(newZombie);
                                    zombieThread.start();
                                }
                            }
                        }
                    }

                } catch (Exception e) {
                    System.out.println("Error spawning zombie");
                }
            }
        }
    }

    public static int checkZombiecount(GameMap gameMap) {
        int count = 0;
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 11; col++) {
                Tile tile = gameMap.getTile(row, col);
                ArrayList<Entities> entities = tile.getAllEntities();
                for (Entities entity : entities) {
                    if (entity instanceof Zombie) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(3000);
                if (Gameplay.getCurrentTime() > 100) {
                    if (checkZombiecount(gameMap) < 10) {
                        spawnZombie();
                    }
                } else {
                    if (checkZombiecount(gameMap) < 25) {
                        spawnZombie();
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Zombie Manager interrupted");
            Thread.currentThread().interrupt(); // Preserve the interrupt status
        }
    }
}
