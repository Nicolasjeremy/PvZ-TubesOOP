package Src.GameMaps;

import Src.Entities.*;
import Src.Entities.Plant.Plant;

// import java.util.List;
import java.util.ArrayList;

public class Tile {
    private String area;
    private boolean canPlant;
    private boolean hasPlant;
    private ArrayList<Entities> entity;

    public Tile(String area, boolean canPlant) {
        this.area = area;
        this.canPlant = canPlant;
        this.entity = new ArrayList<>();
        this.hasPlant = false;
    }

    public String getType() {
        return this.area;
    }

    public void addEntity(Entities entities) {
        entity.add(entities);
        this.hasPlant = true;
    }

    public void removeEntity(Entities entities) {
        entity.remove(entities);
    }

    public ArrayList<Entities> getEntities() {
        return this.entity;
    }

    public Plant getTilePlant() {
        for (Entities entities : entity) {
            if (entities instanceof Plant) {
                Plant plant = (Plant) entities;
                return plant;
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
    
}

