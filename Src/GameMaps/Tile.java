package Src.GameMaps;

import Src.Entities.*;

import java.util.List;
import java.util.ArrayList;

public class Tile {
    private String area;
    private boolean canPlant;
    private ArrayList<Entities> entity;

    public Tile(String area, boolean canPlant) {
        this.area = area;
        this.canPlant = canPlant;
        this.entity = new ArrayList<>();
    }

    public String getType() {
        return this.area;
    }

    public void addEntity(Entities entities) {
        entity.add(entities);
    }

    public void removeEntity(Entities entities) {
        entity.add(entities);
    }

    public List<Entities> getEntities() {
        return this.entity;
    }

    public boolean getCanPlant() {
        return canPlant;
    }
}

