package Src.Entities.Zombie;

import Src.GameMaps.GameMap;

public class ZAsep extends Zombie implements ZombieFactory{ // ! Zombie special dimana gabisa di freeze
    public ZAsep(int[] position, GameMap gameMap, String imagepath) {
        super("ZAsep", 125, 100, 1, position, false, false, gameMap, imagepath);
    }

    public Zombie createZombie(int[] position, GameMap gameMap) {
        return new ZAsep(position, gameMap, null);
    }


    @Override
    public void setSlow(boolean slow) {
        super.setSlow(false);
    }
}
