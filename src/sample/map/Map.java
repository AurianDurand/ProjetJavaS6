package sample.map;

import sample.entityManager.Entity;
import sample.entityManager.dynamicEntities.DynamicEntity;
import sample.entityManager.physicalEntities.Wall;

public class Map {

    // private ArrayList<Tile> tiles = new ArrayList<>();
    private Tile[][] tiles;
    private int width;
    private int height;

    //private ArrayList<Entity> entities;

    public Map(int width, int height) {
        this.tiles = new Tile[width][height];
        this.width = width;
        this.height = height;

        generateTiles();
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

    public Tile getTile(int x, int y) {
        return this.tiles[x][y];
    }

    public void addEntityOnTile(Entity entity, int x, int y) {
        this.tiles[x][y].addEntity(entity);
    }

    public void removeEntityOnTile(Entity entity, int x, int y) {
        this.tiles[x][y].removeEntity(entity);
    }

}
