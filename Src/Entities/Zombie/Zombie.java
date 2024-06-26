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
    private UnslowThread unslowThread;
    private Thread zombiThread;
    private boolean alive;

    public Zombie(String name, int health, int attackDmg, int attackSpd, int[] position, boolean isAquatic,
            boolean special, GameMap gameMap, String imagepath) {
        super(name, health, attackDmg, attackSpd, position, gameMap, imagepath);
        this.isAquatic = isAquatic;
        this.special = special;
        this.slow = false;
        this.alive = true;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setZombieThread(Thread thread) {
        this.zombiThread = thread;
    }

    public Thread getZombiThread() {
        return this.zombiThread;
    }

    public boolean getAquatic() {
        return isAquatic;
    }

    public void stopThread() {
        if (zombiThread != null) {
            zombiThread.interrupt();
        }
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
            if (zombiThread != null) {
                zombiThread.interrupt();
                try {
                    zombiThread.join(); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Interrupted while waiting for zombie thread to stop: " + e.getMessage());
                }
            }
            alive = false; 
        }
    }

    public void action() throws InterruptedException {
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

        if (!this.isAlive()) {
            System.out.println("Zombie is not alive, calling die()");
            this.die();
            return;

        } else if (this.isSlow()) {
            int sleepDuration = 1500 / 10;
            for (int i = 0; i < 10 && !Thread.currentThread().isInterrupted(); i++) {
                if (isPlant) {
                    if (getSpecial()) {
                        special(plantInTile);
                        setSpecial(false);
                    } else {
                        attack(plantInTile);
                        Thread.sleep(sleepDuration * 10);
                    }
                } else {
                    for (int x = 0; x < 150 && !Thread.currentThread().isInterrupted(); x++) {
                        Thread.sleep(100);
                        entity = tile.getAllEntities();
                        for (Entities entities : entity) {
                            if (entities instanceof Plant) {
                                isPlant = true;
                                Plant plant = (Plant) entities;
                                plantInTile = plant;
                                break;
                            }
                        }
                        if (isPlant == true) {
                            break;
                        }
                    }
                    if (isPlant == true) {
                        return;
                    }
                    walk(getGameMap());
                }
            }
        } else {
            if (isPlant) {
                if (getSpecial()) {
                    special(plantInTile);
                    setSpecial(false);
                } else {
                    attack(plantInTile);
                    Thread.sleep(1000);
                }
            } else {
                for (int x = 0; x < 100 && !Thread.currentThread().isInterrupted(); x++) {
                    Thread.sleep(100);
                    entity = tile.getAllEntities();
                    for (Entities entities : entity) {
                        if (entities instanceof Plant) {
                            isPlant = true;
                            Plant plant = (Plant) entities;
                            plantInTile = plant;
                            break;
                        }
                    }
                    if (isPlant == true) {
                        break;
                    }
                }
                if (isPlant == true) {
                    System.out.println("ada plant");
                    return;
                }
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
        if (!alive) { 
            return;
        }
        int[] position = getPosition();
        int row = position[0];
        int col = position[1];
        int nextCol = col - 1;
        if (nextCol > 0 && alive) {
            Tile tile = gameMap.getTile(row, col);
            Tile nextTile = gameMap.getTile(row, nextCol);
            tile.removeEntity(this);
            if (alive) {
                int[] nextPosition = { row, nextCol };
                setPosition(nextPosition);
                nextTile.addEntity(this);
            }
        } else {
            Gameplay.setIsEnd(true);
            Gameplay.setWinningState(false);
            this.die();
        }
    }

    public void special(Plant plant) {
        plant.die();
        this.walk(getGameMap());
    }

    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted() && Gameplay.getIsEnd() == false && this.getHealth() > 0) {
                if (alive) {
                    // System.out.println(this.getName() + "berjalan");
                    action();
                }
            }
            Thread.currentThread().interrupt();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Zombie thread interrupted: " + this.getName());
            alive = false;
        }
    }

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
            }
        }
    }
}
