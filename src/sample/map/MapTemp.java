package sample.map;

import sample.entityManager.Entity;
import sample.entityManager.EntityManager;
import sample.entityManager.EntityType;

import java.util.ArrayList;
import java.util.HashMap;

public class MapTemp {

    private Tile[][] tiles;
    private HashMap<Entity, Tile> entitiesPosition = new HashMap<>();
    private int width;
    private int height;
    private EntityManager entityManager= new EntityManager();

    public MapTemp(int width, int height) {

        this.tiles = new Tile[width][height];
        this.width = width;
        this.height = height;

        // when instantiate, the map builds its tiles
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

    public void buildMap(ArrayList<Object> paserOutput) {

        // let's suppose we have a 10*10 map

        this.createEntity(EntityType.PACMAN,1,1,"assets/theme1");
        this.createEntity(EntityType.GHOST,8,1,"assets/theme1");
        this.createEntity(EntityType.GHOST,8,8,"assets/theme1");
        this.createEntity(EntityType.PACGUM,1,2,"assets/theme1");
        this.createEntity(EntityType.PACGUM,1,4,"assets/theme1");
        this.createEntity(EntityType.PACGUM,2,1,"assets/theme1");
        this.createEntity(EntityType.PACGUM,4,2,"assets/theme1");
    }

    public Tile getTile(int x, int y) {
        return this.tiles[x][y];
    }

    public ArrayList<Entity> getEntitiesOnTile(int x, int y) {
        return this.tiles[x][y].getEntities();
    }

    public void createEntity(EntityType entityType, int x, int y, String assetPath) {

        // first, ask the entityManager to create the entity
        Entity createdEntity = this.entityManager.createEntity(entityType,x,y,assetPath);

        // second, add the entity to the HashMap
        this.entitiesPosition.put(createdEntity, this.tiles[x][y]);

        // third, add the entity to the tile
        this.tiles[x][y].addEntity(createdEntity);
    }

    public void destroyEntity(Entity entity) {

        // first, remove the entity from the tile
        this.tiles[this.entitiesPosition.get(entity).getX()][this.entitiesPosition.get(entity).getY()].removeEntity(entity);

        // second, remove the entity from the HasMap
        this.entitiesPosition.remove(entity);

        // third, destroy the entity
        this.entityManager.destroyEntity(entity);
    }
}
