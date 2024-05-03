package Src.GameMaps;

public class GameMap {
    private Tile[][] grid;

    public GameMap(Tile[][] grid) {
        this.grid = new Tile[6][9];

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 9; col++) {
                if (col == 0) {
                    grid[row][col] = new Home();
                }
                else if (col == 8) {
                    grid[row][col] = new ZombieSpawn();
                }
                else if (row >= 2 && row <= 3) {
                    grid[row][col] = new Pool(false);
                }
                else {
                    grid[row][col] = new ZombieSpawn();
                }
            }
        }
    }

    public Tile getTile(int row, int col) {
        if (row < 0 || row > 6 || col < 0 || col > 9) {
            //throw exception
        }
        return this.grid[row][col];
    }
}
 