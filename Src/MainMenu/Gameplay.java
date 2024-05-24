package Src.MainMenu;

import java.util.ArrayList;

import Src.Entities.Entities;
import Src.Entities.Plant.Melee.Jalapeno;
import Src.Entities.Plant.Melee.Squash;
import Src.Entities.Plant.Melee.TangleKelp;
import Src.Entities.Plant.Passive.Lilypad;
import Src.Entities.Plant.Passive.Sunflower;
import Src.Entities.Plant.Passive.Tallnut;
import Src.Entities.Plant.Passive.Wallnut;
import Src.Entities.Plant.Shooter.Peashooter;
import Src.Entities.Plant.Shooter.Repeater;
import Src.Entities.Plant.Shooter.Snowpea;
import Src.GameMaps.GameMap;
import Src.GameMaps.Sun;
import Src.GameMaps.ZombieManager;

public class Gameplay implements Runnable {
    private ArrayList<Entities> entities;
    private static int current_time;
    private boolean isDay;
    private static boolean isEnd;
    private static Sun sun;
    private GameMap gameMap;
    private Deck deck;
    private Inventory inventory;
    private static boolean winningstate;

    public Gameplay() {
        this.entities = new ArrayList<Entities>();
        Gameplay.current_time = 200;
        this.isDay = true;
        Gameplay.isEnd = false;
        Gameplay.sun = new Sun();
        this.gameMap = new GameMap();
        this.deck = new Deck(gameMap);
        this.inventory = new Inventory(deck);
        Gameplay.winningstate = false;
    }

    @Override
    public void run() {

        try {

            ZombieManager zombieManager = new ZombieManager(gameMap);
            Thread zombieManagerThread = new Thread(zombieManager);
            Sun sun = new Sun();
            Thread sunThread = new Thread(sun);
            boolean game_end_run = false;

            if (isDay) {
                sunThread.start();
            }

            while (!game_end_run && !Gameplay.getIsEnd()) {

                if (current_time == 180) {
                    zombieManagerThread.start();
                } else if (current_time == 100) {
                    this.isDay = false;
                    sunThread.interrupt();
                    System.out.println("\nNight has come");
                } else if (current_time <= 40) {
                    zombieManagerThread.interrupt();
                    if (this.getGameMap().getAllZombieinGame().size() == 0) {
                        Gameplay.setWinningState(true);
                        Gameplay.setIsEnd(true);
                        game_end_run = true;
                    }
                }
                if (current_time == 0) {
                    System.out.println("Day has come");
                    this.isDay = true;
                }
                Thread.sleep(1000);
                Gameplay.current_time--;
                // System.out.print(current_time);
            }
            if (Gameplay.winningstate == true) {
                System.out.println("\nYou win!");
            } else {
                System.out.println("You lose!");
            }

        } catch (InterruptedException e) {
            System.out.println("Gameplay stop");
        }

    }

    public ArrayList<Entities> getEntities() {
        return this.entities;
    }

    public static synchronized int getCurrentTime() {
        return Gameplay.current_time;
    }

    public boolean getIsDay() {
        return this.isDay;
    }

    public static boolean getIsEnd() {
        return isEnd;
    }

    public Sun getSun() {
        return sun;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public Deck getDeck() {
        return deck;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public static boolean getWinningState() {
        return winningstate;
    }

    public void setEntities(ArrayList<Entities> entities) {
        this.entities = entities;
    }

    public static synchronized void setCurrentTime(int current_time) {
        Gameplay.current_time = current_time;
    }

    public void setIsDay(boolean isDay) {
        this.isDay = isDay;
    }

    public static synchronized void setIsEnd(boolean isEnd) {
        Gameplay.isEnd = isEnd;
    }

    public void setSun(Sun sun) {
        Gameplay.sun = sun;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public static synchronized void setWinningState(boolean winningstate) {
        Gameplay.winningstate = winningstate;
    }

    // Karena ini game singleton maka harus di reset setiap kali game dimulai
    public void resetAttributes() {
        Jalapeno.setLastPlantedTime(999);
        Squash.setLastPlantedTime(999);
        TangleKelp.setLastPlantedTime(999);
        Lilypad.setLastPlantedTime(999);
        Sunflower.setLastPlantedTime(999);
        Tallnut.setLastPlantedTime(999);
        Wallnut.setLastPlantedTime(999);
        Peashooter.setLastPlantedTime(999);
        Repeater.setLastPlantedTime(999);
        Snowpea.setLastPlantedTime(999);
        this.entities = new ArrayList<Entities>();
        this.getGameMap().clearEntities();
        Gameplay.current_time = 200;
        this.isDay = true;
        Gameplay.sun = new Sun();
        this.gameMap = new GameMap();
        this.deck = new Deck(gameMap);
        this.inventory = new Inventory(deck);
        Gameplay.winningstate = false;
        Thread.currentThread().interrupt();
        Gameplay.setIsEnd(false);

    }

}
