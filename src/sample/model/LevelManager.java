package sample.model;

import sample.map.Map;
import sample.parser.Layers;
import sample.parser.TMXParser;
import sample.parser.Tile;

import java.util.ArrayList;
import java.util.List;

public class LevelManager {

    private Map map;
    private String mapName;

    public LevelManager() {

    }

    public LevelManager(String mapName) {
        this.mapName = mapName;

        // first, call the parser to get the map elements
        Layers clayers = this.callParser();

        // second, instantiate the map with the size returned by the parser
        this.map = new Map(clayers.getWidth(),clayers.getHeight());

        // third, build the map with the elements returned by the parser
        this.buildMap(clayers);
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
        Layers cLayers = parser.Parse("Res/Maps/", "map_1.tmx");

        // return the parse output
        return cLayers;
    }
}
