package Src.Entities.Plant;

import Src.GameMaps.*;
// import Src.MainMenu.Gameplay;

import javax.swing.ImageIcon;

import Src.Entities.Entities;

public abstract class Plant extends Entities implements Runnable {
    private Integer cost;
    private Integer range;
    private Integer cooldown;
    private ImageIcon icon;
    private static int lastPlantedTime;

    public Plant(String name, int health, int attackDmg, int attackSpd, int[] position, int cost, int range,
            int cooldown, GameMap gameMap, String imagepath, int lastPlantedTime) {
        super(name, health, attackDmg, attackSpd, position, gameMap, imagepath);
        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
        this.icon = new ImageIcon(imagepath);
        Plant.lastPlantedTime = lastPlantedTime;
    }

    public int getCost() {
        return cost;
    }

    public int getRange() {
        return range;
    }

    public int getCooldown() {
        return cooldown;
    }

    public ImageIcon getIcon(){
        return icon;
    }

    public int getLastPlantedTime() {
        return lastPlantedTime;
    }

    public void setLastPlantedTime(int planttime) {
        lastPlantedTime = planttime;
    }

    public abstract void attack();
    
    public abstract void run();
}
