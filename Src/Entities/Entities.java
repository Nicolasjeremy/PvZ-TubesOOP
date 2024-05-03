package Src.Entities;

public class Entities {
    private String name;
    private int health;
    private int attackDmg;
    private int attackSpd;
    private int[] position;

    // Constructor
    public Entities(String name, int health, int attackDmg, int attackSpd, int[] position) {
        this.name = name;
        this.health = health;
        this.attackDmg = attackDmg;
        this.attackSpd = attackSpd;
        this.position = position;
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
}
