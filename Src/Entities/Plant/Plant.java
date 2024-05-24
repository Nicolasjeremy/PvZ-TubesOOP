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

    public Plant(String name, int health, int attackDmg, int attackSpd, int[] position, int cost, int range,
            int cooldown, GameMap gameMap, String imagepath, int lastPlantedTime) {
        super(name, health, attackDmg, attackSpd, position, gameMap, imagepath);
        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
        this.icon = new ImageIcon(imagepath);
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

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public abstract void attack();

    public abstract void run();
}
