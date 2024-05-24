package Src.Entities.Zombie;

import Src.GameMaps.GameMap;

public class ZDolphonRider extends Zombie implements ZombieFactory<ZDolphonRider>{
    public ZDolphonRider (int[] position, GameMap gameMap){
        super("Dolphon", 175, 100, 1, position, true, false, gameMap, "../Image/ZombieImage/DolphinZ.png");
    }

    public Zombie createZombie(int[] position, GameMap gameMap) {
        return new ZDolphonRider(position, gameMap);
    }

}
