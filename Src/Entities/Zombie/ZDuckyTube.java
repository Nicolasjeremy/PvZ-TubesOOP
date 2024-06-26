package Src.Entities.Zombie;

import Src.GameMaps.GameMap;

public class ZDuckyTube extends Zombie implements ZombieFactory<ZDuckyTube>{
    public ZDuckyTube (int[] position, GameMap gameMap){
        super("DuckyTube", 100, 100, 1, position, true, true, gameMap, "../Image/ZombieImage/DuckyZ.png");
    }

    public Zombie createZombie(int[] position, GameMap gameMap) {
        return new ZDuckyTube(position, gameMap);
    }

}
