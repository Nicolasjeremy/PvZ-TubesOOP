package Src.Entities.Zombie;

import Src.GameMaps.GameMap;

public class ZDolphonRider extends Zombie{
    public ZDolphonRider (int[] position, GameMap gameMap){
        super("Dolphon", 175, 100, 1, position, true, false, gameMap);
    }
}
