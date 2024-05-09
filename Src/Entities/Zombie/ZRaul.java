package Src.Entities.Zombie;

import Src.GameMaps.GameMap;

public class ZRaul extends Zombie{ //! Zombie special dimana kalo darahnya kurang dari setengah atk damagenya bakal * 2
    public ZRaul (String name, int[] position){
        super(name, 250, 100, 1, position, false, false);
    }

    @Override
    public void special(GameMap gameMap){
        if (this.getHealth() < 125){
            this.setAttackDmg(getAttackDmg()*2);
            setSpecial(false);
        }
        else{
            //gada
        }
    }
}
