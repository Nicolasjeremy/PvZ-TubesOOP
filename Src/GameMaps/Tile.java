package Src.GameMaps;

import Src.Entities.Entities;
import Src.Entities.Plant.Plant;
import java.util.ArrayList;
import java.util.List;

public class Tile {
    private String area;
    private boolean canPlant;
    private boolean hasPlant;
    private ArrayList<Entities> entity;
    private List<TileObserver> observers;

    public Tile(String area, boolean canPlant) {
        this.area = area;
        this.canPlant = canPlant;
        this.entity = new ArrayList<>();
        this.hasPlant = false;
        this.observers = new ArrayList<>();
    }

    public String getType() {
        return this.area;
    }

    public void addEntity(Entities entities) {
        entity.add(entities);
        if (entities instanceof Plant) {
            this.hasPlant = true;
        }
        notifyEntityAdded(entities);
        
    }

    public void removeEntity(Entities entities) {
        entity.remove(entities);
        // Thread.currentThread().interrupt();
        notifyEntityRemoved(entities);
    }

    public Entities getEntities(int index) {
        return this.entity.get(index);
    }

    public ArrayList<Entities> getAllEntities() {
        return this.entity;
    }
    public ArrayList<Entities> getAllPlant() {
        ArrayList<Entities> plant = new ArrayList<>();
        for (Entities entities : entity) {
            if (entities instanceof Plant) {
                plant.add(entities);
            }
        }
        return plant;
    }

    public Plant getTilePlant() {
        for (Entities entities : entity) {
            if (entities instanceof Plant) {
                return (Plant) entities;
            }
        }
        return null;
    }

    public void setPlanted(boolean hasplant) {
        this.hasPlant = hasplant;
    }

    public boolean hasPlanted() {
        return hasPlant;
    }

    public boolean getCanPlant() {
        return canPlant;
    }

    public void addObserver(TileObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TileObserver observer) {
        observers.remove(observer);
    }

    private void notifyEntityAdded(Entities entity) {
        for (TileObserver observer : observers) {
            observer.entityAdded(entity.getPosition()[0], entity.getPosition()[1], entity);
        }
    }

    private void notifyEntityRemoved(Entities entity) {
        for (TileObserver observer : observers) {
            observer.entityRemoved(entity.getPosition()[0], entity.getPosition()[1], entity);
        }
    }
}
