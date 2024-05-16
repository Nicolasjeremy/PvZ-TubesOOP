package Src.MainMenu;
import java.util.ArrayList;

import Src.Entities.Entities;
import Src.GameMaps.GameMap;
import Src.GameMaps.Sun;
import Src.GameMaps.ZombieManager;
import java.util.ArrayList;

public class Gameplay implements Runnable{
    private ArrayList<Entities> entities;
    private int current_time;
    private boolean isDay;
    private boolean isEnd;
    private static Sun sun;
    private GameMap gameMap;



    public Gameplay() {
        this.entities = new ArrayList<Entities>();
        this.current_time = 200;
        this.isDay = true;
        this.isEnd = false;
        Gameplay.sun = new Sun();
        this.gameMap = new GameMap();
    }

    @Override
    public void run() {
        while (true) {
            try {
                ZombieManager zombieManager = new ZombieManager(gameMap);
                Thread zombieManagerThread = new Thread(zombieManager);
                zombieManagerThread.start();

                for (int i = 0; i < current_time; i++) {
                    Thread.sleep(1000);
                    this.current_time--;
                    System.out.println(current_time);  
                }
                if (current_time == 100) {
                    this.isDay = !this.isDay;
                    System.out.println("Night has come");
                }
                System.out.println("Game end");
                this.isEnd = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

}
