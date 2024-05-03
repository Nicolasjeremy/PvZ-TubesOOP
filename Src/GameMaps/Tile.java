package Src.GameMaps;

import Src.Actors.*;
import java.util.List;
import java.util.ArrayList;

public class Tile {
    private String area;
    private boolean canPlant;
    private ArrayList<Actor> entity;

    public Tile(String area, boolean canPlant) {
        this.area = area;
        this.canPlant = canPlant;
        this.entity = new ArrayList<>();
    }

    public String getType() {
        return this.area;
    }

    public void addEntity(Actor actor) {
        entity.add(actor);
    }

    public void removeEntity(Actor actor) {
        entity.add(actor);
    }

    public List<Actor> getEntities() {
        return this.entity;
    }

    public boolean getCanPlant() {
        return canPlant;
    }
}

