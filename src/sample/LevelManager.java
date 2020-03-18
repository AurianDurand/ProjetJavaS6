package sample;

import sample.entityManager.EntityManager;
import sample.map.Map;

public class LevelManager {

    private Map map;
    private EntityManager entityManager= new EntityManager();
    private String mapName;

    public LevelManager(String mapName) {
        this.mapName = mapName;
        this.callParser();
    }

    /**
     * this method call the parser and implements the map
     * and entities (objects and dynamic entities)
     */
    private void callParser() {

        // args
        


        // instantiate the parser here


    }
}
