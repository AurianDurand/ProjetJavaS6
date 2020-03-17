package sample.map;

import sample.entityManager.Entity;
import sample.entityManager.dynamicEntities.DynamicEntity;

import java.util.ArrayList;

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
    }

    /**
     * this method generate the tiles based on a given width and height
     */
    /*private void generateTiles() {

        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {

                tiles[i][j] = new Tile(i,j);

            }
        }
    }*/

    public void addPath(int x, int y) {
        this.tiles[x][y] = new Path(x, y);
    }

    public void addWall(int x, int y) {
        this.tiles[x][y] = new Wall(x, y);
    }

    public void addObjectOnTile(Object object, int x, int y) {
        this.tiles[x][y].setObject((sample.entityManager.objects.Object) object);
    }

    public void addDynamicEntityOnTile(DynamicEntity dynamicEntity, int x, int y) {
        this.tiles[x][y].setDynamicEntity(dynamicEntity);
    }

    public boolean isTheTileAPath(int x, int y) {
        return tiles[x][y]instanceof Path;
    }

    public boolean isTheTileAWall(int x, int y) {
        return tiles[x][y]instanceof Wall;
    }
}
