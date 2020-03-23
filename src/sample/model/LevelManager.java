package sample.model;

import sample.map.Map;

import java.util.ArrayList;

public class LevelManager {

    private Map map;
    private String mapName;

    public LevelManager() {

    }

    public LevelManager(String mapName) {
        this.mapName = mapName;

        // first, call the parser to get the map elements
        this.callParser();

        // second, instantiate the map with the size returned by the parser
        this.map = new Map(10,10);

        // third, build the map with the elements returned by the parser
        this.buildMap();
    }

    /**
     * this method build the map with all entities (walls, pacman, ghosts, etc)
     */
    private void buildMap() {

        // first, call the parser
        ArrayList<Object> parserOutput = this.callParser();

        // build the map here
        this.map.buildMap(parserOutput);
    }

    /**
     * this method call the parser and implements the map
     * and entities (objects and dynamic entities)
     */
    private ArrayList<Object> callParser() {

        // instantiate the parser here

        // then call its parse method

        // return the parse output
        return null;
    }
}
