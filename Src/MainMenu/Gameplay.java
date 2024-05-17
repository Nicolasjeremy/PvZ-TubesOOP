package Src.MainMenu;

import java.util.ArrayList;

import Src.Entities.Entities;
import Src.GameMaps.GameMap;
import Src.GameMaps.Sun;
import Src.GameMaps.ZombieManager;

public class Gameplay implements Runnable {
    private ArrayList<Entities> entities;
    private int current_time;
    private boolean isDay;
    private boolean isEnd;
    private static Sun sun;
    private GameMap gameMap;
    private Deck deck;
    private Inventory inventory;

    public Gameplay() {
        this.entities = new ArrayList<Entities>();
        this.current_time = 200;
        this.isDay = true;
        this.isEnd = false;
        Gameplay.sun = new Sun();
        this.gameMap = new GameMap();
        this.deck = new Deck(gameMap);
        this.inventory = new Inventory(deck);
    }

    @Override
    public void run() {

        try {

            ZombieManager zombieManager = new ZombieManager(gameMap);
            Thread zombieManagerThread = new Thread(zombieManager);
            Sun sun = new Sun();
            Thread sunThread = new Thread(sun);

            if (isDay) {
                sunThread.start();
            }

            while (current_time > 0) {
                if (current_time == 180) {
                    zombieManagerThread.start();
                } else if (current_time == 40) {
                    zombieManagerThread.interrupt();
                }
                if (current_time == 100) {
                    this.isDay = false;
                    sunThread.interrupt();
                    System.out.println("\nNight has come");
                }
                Thread.sleep(1000);
                this.current_time--;
                // System.out.print(current_time);
            }
            this.isEnd = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Entities> getEntities() {
        return this.entities;
    }

    public int getCurrentTime() {
        return this.current_time;
    }

    public boolean getIsDay() {
        return this.isDay;
    }

    public boolean getIsEnd() {
        return this.isEnd;
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

    public void setEntities(ArrayList<Entities> entities) {
        this.entities = entities;
    }

    public void setCurrentTime(int current_time) {
        this.current_time = current_time;
    }

    public void setIsDay(boolean isDay) {
        this.isDay = isDay;
    }

    public void setIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
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

    public void resetAttributes() {
        this.entities = new ArrayList<Entities>();
        this.current_time = 200;
        this.isDay = true;
        this.isEnd = false;
        Gameplay.sun = new Sun();
        this.gameMap = new GameMap();
        this.deck = new Deck(gameMap);
        this.inventory = new Inventory(deck);
    }

}
