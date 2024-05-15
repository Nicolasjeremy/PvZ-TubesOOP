package Src.Entities.Plant.Melee;

import Src.GameMaps.*;
//import Src.Entities.Zombie.*;

public class Chomper extends MeleePlant{
    public Chomper(int[] position, GameMap gameMap) {
        super("Chomper", 100, attackDmg, attackSpd, position, 50, range, 20, gameMap);
    }    
}