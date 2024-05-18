package Src.Entities.Zombie;

import Src.GameMaps.GameMap;

public class ZDolphonRider extends Zombie implements ZombieFactory{
    public ZDolphonRider (int[] position, GameMap gameMap){
        super("Dolphon", 175, 100, 1, position, true, false, gameMap, "imagepath");
    }

    public Zombie createZombie(int[] position, GameMap gameMap) {
        return new ZDolphonRider(position, gameMap);
    }

}
