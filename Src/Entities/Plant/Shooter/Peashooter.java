package Src.Entities.Plant.Shooter;

import Src.Entities.Plant.Projectile.Projectile;
// import Src.GUI.StartGameMapPanel;
import Src.GameMaps.*;
// import Src.GUI.*;

public class Peashooter extends ShooterPlant {
    public Peashooter(int[] position, GameMap gameMap) {
        super("Peashooter", 100, 25, 4, position, 100, range, 10, 3, gameMap,
                "../Image/PlantImage/peashooterfigma.png", 0);
    }

    @Override
    public void attack() {
        int[] position = this.getPosition();
        if (position != null) {
            int[] projectilePosition = { position[0], position[1] };
            Projectile projectile1 = new Projectile(getAttackDmg(), projectilePosition, getGameMap());
            getGameMap().getTile(position[0], position[1]).addEntity(projectile1);
            Thread projectileThread1 = new Thread(projectile1);
            projectileThread1.start();
        } else {
            System.err.println("Error: Position is null in Peashooter.attack()");
        }
    }
}
