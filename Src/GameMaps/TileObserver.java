package Src.GameMaps;

import Src.Entities.Entities;

public interface TileObserver {
    void entityAdded(int row, int col, Entities entity);

    void entityRemoved(int row, int col, Entities entity);
}
