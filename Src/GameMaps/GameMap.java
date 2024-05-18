package Src.GameMaps;
import java.util.ArrayList;
import Src.Entities.Entities;
import Src.Entities.Plant.Plant;
import Src.Entities.Zombie.Zombie;

public class GameMap {
    private Tile[][] grid;

    public static final String RESET = "\033[0m";
    public static final String GREEN = "\033[0;32m";
    public static final String CYAN = "\033[0;36m";
    public static final String PINK = "\033[1;35m";
    public static final String BROWN = "\033[0;33m";
    public static final String PLANTCOLOR = "\033[0;32m";
    public static final String ZOMBIECOLOR = "\033[0;31m";

    public GameMap() {
        this.grid = new Tile[6][11];

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 11; col++) {
                if (col == 0) {
                    grid[row][col] = new Home();
                } else if (col == 10) {
                    grid[row][col] = new ZombieSpawn();
                } else if (row >= 2 && row <= 3) {
                    grid[row][col] = new Pool(false);
                } else {
                    grid[row][col] = new Grass();
                }
            }
        }
    }

    public Tile getTile(int row, int col) {
        if (row < 0 || row >= 6 || col < 0 || col >= 11) {
            throw new IllegalArgumentException("Invalid tile position");
        }
        return this.grid[row][col];
    }

    public void displayMap() {
        System.out.println("--------------------------------------------------------------------------");
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 11; col++) {
                Tile tile = getTile(row, col);
                String tileColor = getTileColor(tile);

                StringBuilder sb = new StringBuilder();
                sb.append(tileColor).append("[");

                ArrayList<Entities> entities = tile.getAllEntities();
                if (entities.isEmpty()) {
                    sb.append(" "); // Add a space if no entities
                } else {
                    for (int i = 0; i < entities.size(); i++) {
                        Entities entity = entities.get(i);
                        if (entity instanceof Plant) {
                            sb.append(PLANTCOLOR).append(entity.getName()).append(RESET);
                        } else if (entity instanceof Zombie) {
                            sb.append(ZOMBIECOLOR).append(entity.getName()).append(RESET);
                        } else {
                            sb.append(entity.getName());
                        }
                        if (i < entities.size() - 1) {
                            sb.append(", ");
                        }
                    }
                }

                sb.append(tileColor).append("]");
                sb.append(RESET);
                System.out.print(sb.toString());
            }
            System.out.println();
        }
        System.out.println("--------------------------------------------------------------------------");
    }

    public String getTileColor(Tile tile) {
        if (tile instanceof Home) {
            return PINK;
        } else if (tile instanceof Grass) {
            return GREEN;
        } else if (tile instanceof ZombieSpawn) {
            return BROWN;
        } else {
            return CYAN; // For Pool
        }
    }
}
