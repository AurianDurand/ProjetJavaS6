package sample.map;

import sample.entityManager.Entity;
import sample.entityManager.EntityManager;
import sample.entityManager.EntityType;

import sample.parser.Layers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Map {

    private Tile[][] tiles;
    private HashMap<Entity, Tile> entitiesPosition = new HashMap<>();
    private int width;
    private int height;
    private EntityManager entityManager= new EntityManager();

    public Map(int width, int height) {

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

    public void buildMap(Layers cLayers) {

        List<sample.parser.Tile[][]> layers = cLayers.getLayers();

        if(layers != null){

            // for each layer
            for(sample.parser.Tile[][] layer : layers){

                // for each tile
                for(int i = 0; i < layer.length; i++){
                    for(int j = 0; j < layer[i].length; j++){

                        // if the tile contains something (an entity)
                        if(layer[i][j] != null) {

                            System.out.print(layer[i][j].getGid()); // getter -> Source, taille, posTexture, type
                            System.out.print(layer[i][j].getSource());
                            System.out.print(layer[i][j].getCoordTextureX());
                            System.out.print(layer[i][j].getCoordTextureY());
                            System.out.print(layer[i][j].getType());

                            // create the entity

                            if (layer[i][j].getType() != null) {

                                switch (layer[i][j].getType()) {
                                    case "PACMAN":

                                        // i and j are inverted because for the parser, j = lines = x and i = columns = y
                                        this.createEntity(EntityType.PACMAN, j, i, layer[i][j].getSource());
                                        break;
                                    case "GHOST":
                                        this.createEntity(EntityType.GHOST, j, i, layer[i][j].getSource());
                                        break;
                                    case "PACGUM":
                                        this.createEntity(EntityType.PACGUM, j, i, layer[i][j].getSource());
                                        break;
                                    case "SUPERPACGUM":
                                        this.createEntity(EntityType.SUPERPACGUM, j, i, layer[i][j].getSource());
                                        break;
                                    case "WALL":
                                        this.createEntity(EntityType.WALL, j, i, layer[i][j].getSource());
                                        break;
                                }
                            }

                        }
                        // if the tile is empty on this layer
                        else {
                            System.out.print("0");
                        }

                        System.out.print(", ");
                    }
                    System.out.println("");
                }
                System.out.println("");
            }
        }

        System.out.println("Map de taille : " + cLayers.getWidth() + " : " + cLayers.getHeight());
        System.out.println("Tile de taille : " + cLayers.getTilewidth() + " : " + cLayers.getTileheight());

        System.out.println("number of entities : " + this.entityManager.getEntities().size());
        System.out.println("0,0 : "+this.getEntitiesOnTile(0,0));
        System.out.println("1,1 : "+this.getEntitiesOnTile(1,1));
        System.out.println("8,1 : "+this.getEntitiesOnTile(8,1));

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
