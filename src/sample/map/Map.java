package sample.map;

import sample.entityManager.Entity;
import sample.entityManager.EntityManager;
import sample.entityManager.EntityType;

import sample.entityManager.dynamicEntities.Direction;
import sample.entityManager.dynamicEntities.DynamicEntity;
import sample.entityManager.dynamicEntities.Ghost;
import sample.entityManager.dynamicEntities.Pacman;
import sample.parser.Layers;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Map {

    // for the model
    private Tile[][] tiles;
    private HashMap<Entity, Tile> entitiesPosition = new HashMap<>();
    private int width;
    private int height;
    private EntityManager entityManager= new EntityManager(this);

    // for the view
    private Point size;
    private Point tilesize;
    private int nbLayer;
    private Layers layers;
    private ArrayList<HashMap<Point, Entity>> entityMaps = new ArrayList<>();

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

            // for the view
            this.layers = cLayers;
            this.size = new Point(cLayers.getWidth(), cLayers.getHeight());
            this.tilesize = new Point(cLayers.getTilewidth(), cLayers.getTileheight());
            this.nbLayer = layers.size();
            this.entityMaps.clear();

            // for each layer
            int layerIndex = 0;
            for(sample.parser.Tile[][] layer : layers){

                // initialise the layer to rebuild for the view
                this.entityMaps.add(new HashMap<Point, Entity>());

                // for each tile
                for(int i = 0; i < layer.length; i++){
                    for(int j = 0; j < layer[i].length; j++){

                        // if the tile contains something (an entity)
                        if(layer[i][j] != null) {

                            // create the entity

                            if (layer[i][j].getType() != null) {

                                switch (layer[i][j].getType()) {
                                    case "PACMAN":

                                        // i and j are inverted because for the parser, j = lines = x and i = columns = y
                                        this.createEntity(EntityType.PACMAN, j, i, layer[i][j], layerIndex);
                                        break;
                                    case "GHOST":
                                        this.createEntity(EntityType.GHOST, j, i, layer[i][j], layerIndex);
                                        break;
                                    case "PACGUM":
                                        this.createEntity(EntityType.PACGUM, j, i, layer[i][j], layerIndex);
                                        break;
                                    case "SUPERPACGUM":
                                        this.createEntity(EntityType.SUPERPACGUM, j, i, layer[i][j], layerIndex);
                                        break;
                                    case "WALL":
                                        this.createEntity(EntityType.WALL, j, i, layer[i][j], layerIndex);
                                        break;
                                    default:
                                        this.createEntity(EntityType.OTHER, j, i, layer[i][j], layerIndex);
                                        break;
                                }
                            }
                        }

                        // if the tile is empty on this layer
                        else {
                        }
                    }
                }

                layerIndex ++;
            }
        }
    }

    public Tile getTile(int x, int y) {
        return this.tiles[x][y];
    }

    public ArrayList<Entity> getEntitiesOnTile(int x, int y) {
        return this.tiles[x][y].getEntities();
    }

    /**
     * this method will ask every dynamic entity to move (if it's possible)
     */
    public void moveAllDynamicEntities() {

        // search the dynamic entities
        for (Entity entity : this.entityManager.getEntities()) {
            if(entity instanceof DynamicEntity) {

                int currentX = this.entitiesPosition.get(entity).getX();
                int currentY = this.entitiesPosition.get(entity).getY();

//                System.out.println("Move:");
//                System.out.println("  " + entity + ": " + this.entitiesPosition.get(entity) +" / " + currentX + "," + currentY);
//                System.out.println("  direction: " + ((DynamicEntity) entity).getBuffer());

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
                    case IDLE:
                        if ((DynamicEntity) entity instanceof Ghost) {
                            ((DynamicEntity) entity).setBuffer(Direction.getRandomDirection());
                        }
                        break;
                }
            }
        }

//        System.out.println("Pacman: "+this.entitiesPosition.get(this.getPacman()).getX()+","+this.entitiesPosition.get(this.getPacman()).getY());

        for (Entity entity : this.entityManager.getEntitiesToDelete()) {
//            System.out.println("To delete: "+entity+" -> "+this.entitiesPosition.get(entity).getX()+","+this.entitiesPosition.get(entity).getY());
            this.destroyEntity(entity);
        }
        this.entityManager.clearEntitiesToDelete();
    }

    /**
     * this method verifies if the tile to move on exists and if so,
     * move the entity
     * @param entity,x,y the entity to move and the new coordinates
     */
    private void setEntityToNewTile(Entity entity, int x, int y) {

//        if(entity instanceof Pacman) System.out.println("  has moved to " + x + "," + y);

        // remove the entity from the current tile
        this.entitiesPosition.get(entity).removeEntity(entity);

        // set the new tile for the entity in the HashMap
//        this.entitiesPosition.replace(entity, this.tiles[x][y]);
        this.entitiesPosition.put(entity, this.tiles[x][y]);

        // add the entity into the new tile
        this.tiles[x][y].addEntity(entity);

        // fourth, move the entity in the entityMaps for the view
        boolean theEntityHasMoved = false;
        for (HashMap<Point,Entity> layer: this.entityMaps) {

            for(int l = 0; l < this.getSize().y; l++) {
                for(int m = 0; m < this.getSize().x; m++) {

                    if(layer.get(new Point(m, l)) == entity && !theEntityHasMoved) {
                        layer.remove(new Point(m, l));
                        layer.put(new Point(x,y),entity);
                        theEntityHasMoved = true;
                    }
                }
            }
        }
    }

    /**
     * this method creates the entity and add it to every variable which may need it
     * @param entityType
     * @param x
     * @param y
     * @param assetPath
     * @param layerIndex
     */
    public void createEntity(EntityType entityType, int x, int y, sample.parser.Tile assetPath, int layerIndex) {

        // first, ask the entityManager to create the entity
        Entity createdEntity = this.entityManager.createEntity(entityType,assetPath);

        // second, add the entity to the HashMap
        this.entitiesPosition.put(createdEntity, this.tiles[x][y]);

        // third, add the entity to the tile
        this.tiles[x][y].addEntity(createdEntity);

        // fourth, add the entity to the entityMaps for the view
        if(this.tiles[x][y].getEntities().size() >= 1) {
            this.entityMaps.get(layerIndex).put(new Point(x,y), this.tiles[x][y].getEntities().get(this.tiles[x][y].getEntities().size()-1));
        }

//        System.out.println(createdEntity+" -> "+this.entitiesPosition.get(createdEntity).getY()+","+this.entitiesPosition.get(createdEntity).getY());
    }

    /**
     * this method deletes the given entity in every variable where it is stored
     * @param entity
     */
    public void destroyEntity(Entity entity) {

        // first, remove the entity from the tile
        this.tiles[this.entitiesPosition.get(entity).getX()][this.entitiesPosition.get(entity).getY()].removeEntity(entity);

        // second, remove the entity from the HasMap
        this.entitiesPosition.remove(entity);

        // third, destroy the entity
        this.entityManager.destroyEntity(entity);

        // fourth, destroy the entity from the entityMaps (for the view)
        boolean theEntityHasBeenRemoved = false;
        for (HashMap<Point,Entity> layer: this.entityMaps) {

            for(int l = 0; l < this.getSize().y; l++) {
                for(int m = 0; m < this.getSize().x; m++) {

                    if(layer.get(new Point(m, l)) == entity && !theEntityHasBeenRemoved) {
                        layer.remove(new Point(m, l));
                        theEntityHasBeenRemoved = true;
                    }
                }
            }
        }
    }

    // -------------------------

    // for the view

    public Entity getEntityByPosition(Point point, int idLayer) {
        if(idLayer < this.entityMaps.size()) {
            return this.entityMaps.get(idLayer).get(point);
        }
        return null;
    }

    public ArrayList<Entity> getEntitiesByPosition(Point point) {
        ArrayList<Entity> e = new ArrayList<>();

        for(HashMap<Point, Entity> hashMap : this.entityMaps) {
            if(hashMap.containsKey(point)) {
                e.add(hashMap.get(point));
            }
        }
        return e;
    }

    // -------------------------

    public void setDirectionFirstPacman(Direction d) {
        this.getPacman().setBuffer(d);
    }

    public Pacman getPacman() {
        return this.entityManager.getPacman().get(0);
    }

    public HashMap<Entity, Tile> getEntitiesPosition() {
        return entitiesPosition;
    }

    public Point getSize() {
        return size;
    }

    public Point getTilesize() {
        return tilesize;
    }

    public int getNbLayer() {
        return nbLayer;
    }
}
