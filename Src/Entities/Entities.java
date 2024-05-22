package Src.Entities;

import Src.Entities.Plant.Plant;
import Src.GameMaps.GameMap;
import Src.GameMaps.Tile;

public class Entities {
    private String name;
    protected int health;
    private int attackDmg;
    protected int attackSpd;
    private int[] position;
    private GameMap gameMap;
    private String imagepath;

    // Constructor
    public Entities(String name, int health, int attackDmg, int attackSpd, int[] position, GameMap gameMap,
            String imagepath) {
        this.name = name;
        this.health = health;
        this.attackDmg = attackDmg;
        this.attackSpd = attackSpd;
        this.position = position;
        this.gameMap = gameMap;
        this.imagepath = imagepath;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackDmg() {
        return attackDmg;
    }

    public void setAttackDmg(int attackDmg) {
        this.attackDmg = attackDmg;
    }

    public int getAttackSpd() {
        return attackSpd;
    }

    public void setAttackSpd(int attackSpd) {
        this.attackSpd = attackSpd;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setgameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void setimagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getimagepath() {
        return imagepath;
    }

    public void die() {
        Tile tile = this.gameMap.getTile(this.position[0], this.position[1]);
        if (this instanceof Plant){
            tile.setPlanted(false);
        }
        tile.removeEntity(this);
        this.setHealth(-1);
        System.out.println(this.getName() + " mati");
        this.stop();
    }

    public void stop() {
        Thread.currentThread().interrupt(); // Interrupt the thread
    }
}
