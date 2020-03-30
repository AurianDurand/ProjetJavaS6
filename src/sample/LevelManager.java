package sample;

import sample.entityManager.Entity;
import sample.map.Map;
import sample.map.Tile;
import sample.parser.Layers;
import sample.parser.TMXParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LevelManager {

    private Map map;
    private String mapPath;

    public LevelManager(String mapPath) {
        this.mapPath = mapPath;

        // first, call the parser to get the map elements
        Layers clayers = this.callParser();

        // second, instantiate the map with the size returned by the parser
        this.map = new Map(clayers.getWidth(),clayers.getHeight());

        // third, build the map with the elements returned by the parser
        this.buildMap(clayers);
    }

    /**
     * ask the map to move all dynamic entities if they are allowed to do so
     */
    public void moveAllDynamicEntities() {
        this.map.moveAllDynamicEntities();
    }

    /**
     * @return a HashMap with all entities positions
     */
    public HashMap<Entity, Tile> getEntitiesPosition() {
        return this.map.getEntitiesPosition();
    }

    /**
     * this method build the map with all entities (walls, pacman, ghosts, etc)
     */
    private void buildMap(Layers cLayers) {

        this.map.buildMap(cLayers);

    }

    /**
     * this method call the parser and implements the map
     * and entities (objects and dynamic entities)
     */
    private Layers callParser() {

        // instantiate the parser
        TMXParser parser = new TMXParser();

        // call its parse method
        Layers cLayers = parser.Parse("src/res/maps/", this.mapPath);

        // return the parse output
        return cLayers;
    }

    public void update() {

    }

}
