package sample.map;

import sample.entityManager.Entity;
import sample.entityManager.EntityManager;
import sample.entityManager.EntityType;

import sample.entityManager.dynamicEntities.DynamicEntity;
import sample.parser.Layers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapTemp {

    private Tile[][] tiles;
    private HashMap<Entity, Tile> entitiesPosition = new HashMap<>();
    private int width;
    private int height;
    private EntityManager entityManager= new EntityManager(this);

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

//                            System.out.print(layer[i][j].getGid()); // getter -> Source, taille, posTexture, type
//                            System.out.print(layer[i][j].getSource());
//                            System.out.print(layer[i][j].getCoordTextureX());
//                            System.out.print(layer[i][j].getCoordTextureY());
//                            System.out.print(layer[i][j].getType());

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
//                            System.out.print("0");
                        }

//                        System.out.print(", ");
                    }
//                    System.out.println("");
                }
//                System.out.println("");
            }
        }

//        System.out.println("Map de taille : " + cLayers.getWidth() + " : " + cLayers.getHeight());
//        System.out.println("Tile de taille : " + cLayers.getTilewidth() + " : " + cLayers.getTileheight());
//
//        System.out.println("number of entities : " + this.entityManager.getEntities().size());
//        System.out.println("0,0 : "+this.getEntitiesOnTile(0,0));
//        System.out.println("1,1 : "+this.getEntitiesOnTile(1,1));
//        System.out.println("8,1 : "+this.getEntitiesOnTile(8,1));

    }

    public Tile getTile(int x, int y) {
        return this.tiles[x][y];
    }

    public ArrayList<Entity> getEntitiesOnTile(int x, int y) {
        return this.tiles[x][y].getEntities();
    }

    public void moveAllDynamicEntities() {

        // search the dynamic entities
        for (Entity entity : this.entityManager.getEntities()) {
            if(entity instanceof DynamicEntity) {

                int currentX = this.entitiesPosition.get(entity).getX();
                int currentY = this.entitiesPosition.get(entity).getY();

                System.out.println("Move:");
                System.out.println("  " + entity + ": " + this.entitiesPosition.get(entity) +" / " + currentX + "," + currentY);

                ArrayList<Entity> entitiesOnTileToMOveOn;
                boolean canTheEntityMove;

                // handle each move case
                switch (((DynamicEntity) entity).getBuffer()) {
                    case UP:

                        // get the entities on the tile to move on
                        entitiesOnTileToMOveOn = this.tiles[currentX][currentY-1].getEntities();

                        // ask the entity manager to handle the collide case
                        canTheEntityMove = this.entityManager.onCollide(entity, entitiesOnTileToMOveOn);

                        // if the method canTheEntityMove returned true, the entity can move to the desired tile
                        if(canTheEntityMove) {

                            // move the entity here
                            this.setEntityToNewTile(entity,currentX,currentY-1);
                        }
                        break;

                    case DOWN:

                        // get the entities on the tile to move on
                        entitiesOnTileToMOveOn = this.tiles[currentX][currentY+1].getEntities();

                        // ask the entity manager to handle the collide case
                        canTheEntityMove = this.entityManager.onCollide(entity, entitiesOnTileToMOveOn);

                        // if the method canTheEntityMove returned true, the entity can move to the desired tile
                        if(canTheEntityMove) {

                            // move the entity here
                            this.setEntityToNewTile(entity,currentX,currentY+1);
                        }
                        break;

                    case LEFT:

                        // get the entities on the tile to move on
                        entitiesOnTileToMOveOn = this.tiles[currentX-1][currentY].getEntities();

                        // ask the entity manager to handle the collide case
                        canTheEntityMove = this.entityManager.onCollide(entity, entitiesOnTileToMOveOn);

                        // if the method canTheEntityMove returned true, the entity can move to the desired tile
                        if(canTheEntityMove) {

                            // move the entity here
                            this.setEntityToNewTile(entity,currentX-1,currentY);
                        }
                        break;

                    case RIGHT:

                        // get the entities on the tile to move on
                        entitiesOnTileToMOveOn = this.tiles[currentX+1][currentY].getEntities();

                        // ask the entity manager to handle the collide case
                        canTheEntityMove = this.entityManager.onCollide(entity, entitiesOnTileToMOveOn);

                        // if the method canTheEntityMove returned true, the entity can move to the desired tile
                        if(canTheEntityMove) {

                            // move the entity here
                            this.setEntityToNewTile(entity,currentX+1,currentY);
                        }
                        break;
                }
            }
        }
    }

    /**
     * this method verify if the tile to move on exists and if so,
     * move the entity
     * @param entity,x,y the entity to move and the new coordinates
     */
    private void setEntityToNewTile(Entity entity, int x, int y) {

        System.out.println("  to " + x + "," + y);

        // remove the entity from the current tile
        this.entitiesPosition.get(entity).removeEntity(entity);

        // set the new tile for the entity in the HashMap
        this.entitiesPosition.replace(entity, this.tiles[x][y]);

        // add the entity into the new tile
        this.tiles[x][y].addEntity(entity);
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

    public HashMap<Entity, Tile> getEntitiesPosition() {
        return entitiesPosition;
    }
}
