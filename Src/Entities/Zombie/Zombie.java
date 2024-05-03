package Src.Entities.Zombie;
import Src.Entities.Entities;

public abstract class Zombie extends Entities {
    private boolean isAquatic;

    public Zombie(String name, int health, int attackDmg, int attackSpd, int[] position, boolean isAquatic) {
        super(name, health, attackDmg, attackSpd, position);
        this.isAquatic = isAquatic;
    }

    public boolean getAquatic() {
        return isAquatic;
    }

    public void setAquatic(boolean isAquatic) {
        this.isAquatic = isAquatic;
    }

    public abstract void action();

    public abstract void attack();
}
