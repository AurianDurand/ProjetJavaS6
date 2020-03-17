package sample.map;

public class Map {

    // private ArrayList<Tile> tiles = new ArrayList<>();
    private Tile[][] tiles;
    private int width;
    private int height;

    public Map(int width, int height) {
        this.tiles = new Tile[width][height];
        this.width = width;
        this.height = height;

        this.generateTiles();
    }

    /**
     * this method generate the tiles based on a given width and height
     */
    private void generateTiles() {

        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {

                tiles[i][j] = new Tile(i,j);

            }
        }
    }

    public boolean isTheTileAPath(int x, int y) {
        return tiles[x][y]instanceof Path;
    }

    public boolean isTheTileAWall(int x, int y) {
        return tiles[x][y]instanceof Wall;
    }
}
