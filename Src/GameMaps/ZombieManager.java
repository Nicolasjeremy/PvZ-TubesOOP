package Src.GameMaps;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Src.Entities.Zombie.*;

public class ZombieManager extends ZombieSpawn implements Runnable {
    private List<Class<? extends Zombie>> zombieTypes;
    private List<Class<? extends Zombie>> zombieTypesWater;
    private Random random;
    private GameMap gameMap;
    public static int ZombieCounter = 0;

    public ZombieManager(GameMap gameMap) {
        this.gameMap = gameMap;
        this.zombieTypes = new ArrayList<>();
        this.zombieTypesWater = new ArrayList<>();
        this.random = new Random();
        // Add the subclasses of Zombie here
        zombieTypes.add(ZRaul.class);
        zombieTypes.add(ZConeHead.class);
        zombieTypes.add(ZBucketHead.class);
        zombieTypes.add(ZDipsy.class);
        zombieTypesWater.add(ZDolphonRider.class);
        zombieTypesWater.add(ZDuckyTube.class);
        zombieTypes.add(ZLala.class);
        zombieTypes.add(ZNormal.class);
        zombieTypes.add(ZPoleVault.class);
    }

    public synchronized void spawnZombie() { // ! Zombie harusnya spawn di air/darat blom dibikin
        // ? ini udah di bedain buat yang water atau bukan
        // Buat random Zombie apa yang di spawn
        int randomIndex = random.nextInt(zombieTypes.size());
        int randomIndexWater = random.nextInt(zombieTypesWater.size());
        // Buat simpen Class Zombie yang terpilih
        Class<? extends Zombie> selectedType = zombieTypes.get(randomIndex);
        Class<? extends Zombie> selectedTypeWater = zombieTypesWater.get(randomIndexWater);
        try {
            // Constructor Zombie
            Constructor<? extends Zombie> constructor = selectedType.getDeclaredConstructor(int[].class, GameMap.class);
            Constructor<? extends Zombie> constructorWater = selectedTypeWater.getDeclaredConstructor(int[].class,
                    GameMap.class);
            // Di loop buat semua Tile
            for (int i = 0; i <= 5; i++) {
                if (ZombieCounter >= 9) {
                    break;
                }

                int[] position = { i, 8 };
                if (i == 2 || i == 3) { // Kalo tile 3 dan 4 yang ke spawn bakal Zombie di air
                    int randomspwn = random.nextInt(10);
                    if (randomspwn == 1 || randomspwn == 2 || randomspwn == 3) {// dari angka 1-10 kalo kepilih 1-3
                                                                                // zombie di spawn = 30%
                        Zombie newZombie = constructorWater.newInstance(position, gameMap);
                        gameMap.getTile(position[0], position[1]).addEntity(newZombie);// Tambahin Zombie ke map
                        Thread zombieThread = new Thread(newZombie);// Start Zombie Thread
                        zombieThread.start();
                        ZombieCounter++;
                    } else {
                    }
                } else {
                    int randomspwn = random.nextInt(10);// Sama aja tapi Buat yang land
                    if (randomspwn == 1 || randomspwn == 2 || randomspwn == 3) {
                        Zombie newZombie = constructor.newInstance(position, gameMap);
                        gameMap.getTile(position[0], position[1]).addEntity(newZombie);
                        Thread zombieThread = new Thread(newZombie);
                        zombieThread.start();
                        ZombieCounter++;
                    } else {
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) { // todo: Hanya boleh ada maksimal 10 zombie di game map dalam satu waktu.
            try {
                Thread.sleep(1000);
                System.out.println(ZombieCounter);
                if (ZombieCounter <= 10) {
                    spawnZombie();

                } else {
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
