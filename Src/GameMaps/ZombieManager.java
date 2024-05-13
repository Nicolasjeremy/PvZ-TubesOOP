package Src.GameMaps;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Src.Entities.Zombie.*;

public class ZombieManager extends ZombieSpawn implements Runnable {
    private List<Class<? extends Zombie>> zombieTypes;
    private Random random;
    private GameMap gameMap;

    public ZombieManager(GameMap gameMap) {
        this.gameMap = gameMap;
        this.zombieTypes = new ArrayList<>();
        this.random = new Random();
        // Add the subclasses of Zombie here
        zombieTypes.add(ZRaul.class);
        zombieTypes.add(ZConeHead.class);
        zombieTypes.add(ZBucketHead.class);
        zombieTypes.add(ZDipsy.class);
        zombieTypes.add(ZDolphonRider.class);
        zombieTypes.add(ZDuckyTube.class);
        zombieTypes.add(ZLala.class);
        zombieTypes.add(ZNormal.class);
        zombieTypes.add(ZPoleVault.class);
    }

    public synchronized void spawnZombie() {
        int randomIndex = random.nextInt(zombieTypes.size());
        Class<? extends Zombie> selectedType = zombieTypes.get(randomIndex);
        try {
            // Get the constructor that accepts int[] and GameMap parameters
            Constructor<? extends Zombie> constructor = selectedType.getDeclaredConstructor(int[].class, GameMap.class);

            // Generate a random row within column 7
            int randomRow = random.nextInt(6); // Assuming 6 rows

            // Create an array for the position
            int[] position = { randomRow, 8 }; // Column 7

            // Create a new instance using the constructor and the position and gameMap
            Zombie newZombie = constructor.newInstance(position, gameMap);

            // Add the new zombie to the container
            gameMap.getTile(position[0], position[1]).addEntity(newZombie);
            Thread zombieThread = new Thread(newZombie);
            zombieThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(3000);
                spawnZombie();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
