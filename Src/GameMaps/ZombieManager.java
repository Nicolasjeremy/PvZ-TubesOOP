package Src.GameMaps;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import Src.Entities.Zombie.*;

public class ZombieManager extends ZombieSpawn implements Runnable {
    private List<ZombieFactory> zombieFactories;
    private List<ZombieFactory> zombieFactoriesWater;
    private Random random;
    private GameMap gameMap;
    private static final Object lock = new Object(); // Lock object for synchronization
    private static final AtomicInteger ZombieCounter = new AtomicInteger(0); // Use AtomicInteger for thread-safe counter

    public ZombieManager(GameMap gameMap) {
        this.gameMap = gameMap;
        this.zombieFactories = new ArrayList<>();
        this.zombieFactoriesWater = new ArrayList<>();
        this.random = new Random();

        // Add the zombie factory instances here
        zombieFactories.add(new ZRaul(null, gameMap)); // Assuming ZRaul implements ZombieFactory
        zombieFactories.add(new ZConeHead(null, gameMap));
        zombieFactories.add(new ZBucketHead(null, gameMap));
        zombieFactories.add(new ZDipsy(null, gameMap));
        zombieFactoriesWater.add(new ZDolphonRider(null, gameMap));
        zombieFactoriesWater.add(new ZDuckyTube(null, gameMap));
        zombieFactories.add(new ZLala(null, gameMap));
        zombieFactories.add(new ZNormal(null, gameMap));
        zombieFactories.add(new ZPoleVault(null, gameMap));
    }

    public int getZombieCounter() {
        return ZombieCounter.get();
    }

    public void incrementCounter() {
        ZombieCounter.incrementAndGet();
    }

    public static void decrementCounter() {
        ZombieCounter.decrementAndGet();
    }

    public void spawnZombie() {
        synchronized (lock) {
            for (int i = 0; i <= 5; i++) {
                if (getZombieCounter() >= 10) {
                    break;
                }

                int[] position = { i, 10 };
                try {
                    if (i == 2 || i == 3) { // If tile 3 and 4, spawn water zombies
                        int randomspwn = random.nextInt(10);
                        if (randomspwn < 1) { // 30% chance to spawn
                            int randomIndex = random.nextInt(zombieFactoriesWater.size());
                            ZombieFactory selectedFactory = zombieFactoriesWater.get(randomIndex);
                            Zombie newZombie = selectedFactory.createZombie(position, gameMap);
                            gameMap.getTile(position[0], position[1]).addEntity(newZombie);
                            Thread zombieThread = new Thread(newZombie);
                            zombieThread.start();
                            incrementCounter();
                        }
                    } else {
                        int randomspwn = random.nextInt(10);
                        if (randomspwn < 1) { // 30% chance to spawn
                            int randomIndex = random.nextInt(zombieFactories.size());
                            ZombieFactory selectedFactory = zombieFactories.get(randomIndex);
                            Zombie newZombie = selectedFactory.createZombie(position, gameMap);
                            gameMap.getTile(position[0], position[1]).addEntity(newZombie);
                            Thread zombieThread = new Thread(newZombie);
                            zombieThread.start();
                            incrementCounter();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                if (getZombieCounter() < 10) {
                    spawnZombie();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
