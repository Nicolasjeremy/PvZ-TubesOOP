package Src.Entities.Zombie;

import java.util.ArrayList;
import Src.Entities.Entities;
import Src.Entities.Plant.Plant;
import Src.GameMaps.*;
import Src.MainMenu.Gameplay;

public abstract class Zombie extends Entities implements Runnable {
    private boolean isAquatic;
    private boolean slow;
    private boolean special;
    private UnslowThread unslowThread; // Thread to handle unslowing

    public Zombie(String name, int health, int attackDmg, int attackSpd, int[] position, boolean isAquatic,
            boolean special, GameMap gameMap, String imagepath) {
        super(name, health, attackDmg, attackSpd, position, gameMap, imagepath);
        this.isAquatic = isAquatic;
        this.special = special;
        this.slow = false;
    }

    public boolean getAquatic() {
        return isAquatic;
    }

    public void setAquatic(boolean isAquatic) {
        this.isAquatic = isAquatic;
    }

    public boolean isSlow() {
        return slow;
    }

    public void setSlow(boolean slow) {
        this.slow = slow;
        if (slow == true) {
            if (unslowThread == null || !unslowThread.isAlive()) {
                unslowThread = new UnslowThread();
                unslowThread.start();
            } else {
                unslowThread.resetTimer();
            }
        }
    }

    public boolean getSpecial() {
        return special;
    }

    public void setSpecial(boolean bool) {
        special = bool;
    }

    @Override
    public void die() {
        synchronized (ZombieManager.class) {
            int[] position = this.getPosition();
            Tile tile = getGameMap().getTile(position[0], position[1]);
            tile.removeEntity(this);
            this.stop();
            if (unslowThread != null) {
                unslowThread.interrupt();
            }
        }
    }

    public void action() throws InterruptedException {
        if (this.isSlow() == true) {
        }
        Plant plantInTile = null;
        boolean isPlant = false;
        int[] position = getPosition();
        Tile tile = getGameMap().getTile(position[0], position[1]);

        ArrayList<Entities> entity = tile.getAllEntities();

        for (Entities entities : entity) {
            if (entities instanceof Plant) {
                isPlant = true;
                Plant plant = (Plant) entities;
                plantInTile = plant;
                break;
            }
        }

        if (this.isSlow()) {
            // Case terkena slow
            int sleepDuration = 1500 / 10; // Check every 150ms
            for (int i = 0; i < 10; i++) {
                if (this.isSlow()) {
                    if (isPlant) {
                        if (getSpecial()) {
                            special(plantInTile);
                            setSpecial(false);
                        } else {
                            Thread.sleep(sleepDuration * 10);
                        }
                    } else {
                        Thread.sleep(sleepDuration * 100); // 1500ms
                        walk(getGameMap());
                        return;
                    }
                } else {
                    break;
                }
            }
        } else {
            // Case tidak terkena slow
            if (isPlant) {
                if (getSpecial()) {
                    special(plantInTile);
                    setSpecial(false);
                } else {
                    Thread.sleep(1000);
                    attack(plantInTile);
                }
            } else {
                Thread.sleep(10000);
                walk(getGameMap());
            }
        }
    }

    public void attack(Plant plant) {
        int dmg = this.getAttackDmg();
        plant.setHealth(plant.getHealth() - dmg);
        if (plant.getHealth() <= 0) {
            plant.die();
        }
    }

    public void walk(GameMap gameMap) {
        int[] position = getPosition();
        int row = position[0];
        int col = position[1];
        int nextCol = col - 1;
        if (nextCol >= 0) {
            Tile tile = gameMap.getTile(row, col);
            Tile nextTile = gameMap.getTile(row, nextCol);
            tile.removeEntity(this);
            int[] nextPosition = { row, nextCol };
            setPosition(nextPosition);
            nextTile.addEntity(this);
        } else {
            this.die();
            Gameplay.setIsEnd(true);
            Gameplay.setWinningState(false);
        }
    }

    public void special(Plant plant) {
        plant.die();
    }

    public void run() {
        try {
            while (!Gameplay.getIsEnd() && this.getHealth() > 0) {
                action();
            }
        } catch (InterruptedException e) {
        }
    }

    // Inner class to handle unslowing logic
    private class UnslowThread extends Thread {
        private final Object lock = new Object();
        private long startTime;

        public UnslowThread() {
            resetTimer();
        }

        public void resetTimer() {
            synchronized (lock) {
                startTime = System.currentTimeMillis();
                lock.notify();
            }
        }

        @Override
        public void run() {
            try {
                while (true) {
                    synchronized (lock) {
                        lock.wait(3000);
                        long elapsed = System.currentTimeMillis() - startTime;
                        if (elapsed >= 3000) {
                            setSlow(false);
                            return;
                        }
                    }
                }
            } catch (InterruptedException e) {
                // Handle interruption
            }
        }
    }
}
