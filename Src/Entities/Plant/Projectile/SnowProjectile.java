package Src.Entities.Plant.Projectile;

import Src.GameMaps.*;
import Src.Entities.Entities;
import Src.Entities.Zombie.*;

public class SnowProjectile extends Projectile {
    public SnowProjectile(int damage, int[] position, GameMap gameMap) {
        super(damage, position, gameMap);
    }

    @Override
    public void action() {
        int[] position = getPosition();
        Tile tile = getGameMap().getTile(position[0], position[1]);
        boolean hitZombie = false;

        for (Entities entity : tile.getAllEntities()) {
            if (entity instanceof Zombie) {
                hitZombie = true;
                Zombie zombie = (Zombie) entity;
                zombie.setHealth(getHealth() - this.getAttackDmg());
                zombie.setSlow(true); //ini ga yakin sih, sebenernya slow ini khusus buat nentuin zomboe yang lambat apa gmn ya je
                if (zombie.getHealth() <= 0) {
                    zombie.die(zombie.getGameMap());
                } else {
                }
                break; 
            }
        }

        if (hitZombie) {
            getGameMap().getTile(position[0], position[1]).removeEntity(this);
        } else {
            walk(getGameMap());
        }
    }
}

//masih bingung apply slow effectnya