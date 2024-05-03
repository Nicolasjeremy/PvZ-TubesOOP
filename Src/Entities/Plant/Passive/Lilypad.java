package Src.Entities.Plant.Passive;

public class Lilypad extends PassivePlant{
    public Lilypad (String name, int[] position) {
        super(name, 100, attackDmg, attackSpd, position, 25, range, 10);
    }    
}
