package Src.Entities.Zombie;
import Src.Entities.Plant.Plant;
import Src.GameMaps.GameMap;

public class ZRaul extends Zombie {
    public ZRaul(int[] position, GameMap gameMap) {
        super("Raul", 250, 100, 1, position, false, false, gameMap);
    }

    @Override
    public void special(GameMap gameMap, Plant plant) {
        if (this.getHealth() < 125) {
            this.setAttackDmg(getAttackDmg() * 2);
            setSpecial(false);
            System.out.println("ZRaul's health is below half, attack damage doubled!");
        } else {
            System.out.println("ZRaul's health is above half, no special action taken.");
        }
    }
}