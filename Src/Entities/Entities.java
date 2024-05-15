package Src.Entities;

import Src.GameMaps.GameMap;
import Src.GameMaps.Tile;

public class Entities {
    private String name;
    private int health;
    private int attackDmg;
    protected int attackSpd;
    private int[] position;
    private GameMap gameMap;

    // Constructor
    public Entities(String name, int health, int attackDmg, int attackSpd, int[] position, GameMap gameMap) {
        this.name = name;
        this.health = health;
        this.attackDmg = attackDmg;
        this.attackSpd = attackSpd;
        this.position = position;
        this.gameMap = gameMap;
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

    public void die(GameMap gameMap) {
        Tile tile = gameMap.getTile(position[0], position[1]);
        tile.removeEntity(this);
    }
}
