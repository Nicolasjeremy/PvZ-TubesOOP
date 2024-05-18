package Src.Entities.Zombie;

import Src.Entities.Plant.Plant;
import Src.GameMaps.GameMap;

public class ZRaul extends Zombie implements ZombieFactory {
    public ZRaul(int[] position, GameMap gameMap) {
        super("Raul", 250, 100, 1, position, false, false, gameMap, "../../Image/ZombieImage/Raul.png");
    }

    public Zombie createZombie(int[] position, GameMap gameMap) {
        return new ZRaul(position, gameMap);
    }

    @Override
    public void special(Plant plant) {
        if (this.getHealth() < 125) {
            this.setAttackDmg(getAttackDmg() * 2);
            setSpecial(false);
        } else {
        }
    }
}