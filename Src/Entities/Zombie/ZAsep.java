package Src.Entities.Zombie;

import Src.GameMaps.GameMap;

public class ZAsep extends Zombie implements ZombieFactory<ZAsep>{ // ! Zombie special dimana gabisa di freeze
    public ZAsep(int[] position, GameMap gameMap) {
        super("ZAsep", 125, 100, 1, position, false, false, gameMap, "../Image/ZombieImage/Asep.png");
    }

    public Zombie createZombie(int[] position, GameMap gameMap) {
        return new ZAsep(position, gameMap);
    }


    @Override
    public void setSlow(boolean slow) {
        super.setSlow(false);
    }
}
