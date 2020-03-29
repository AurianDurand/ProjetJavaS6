package sample;

import sample.entityManager.Entity;
import sample.entityManager.dynamicEntities.DynamicEntity;
import sample.map.Tile;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Game {

    private LevelManager levelManager;

    // each level name is associated with a map name
    private HashMap<String,String> level_map = new HashMap<>();

    // each map name is associated with a path
    private HashMap<String,String> map_path = new HashMap<>();

    public Game() {

        // the game levels
        this.level_map.put("Level 1","Map 1");
        this.level_map.put("Level 2","Map 2");

        // the maps paths
        this.map_path.put("Map 1","Path 1");
        this.map_path.put("Map 2","Path 2");
    }

    public void startLevel() {
        this.levelManager = new LevelManager(this.map_path.get(this.level_map.get("Level 1")));

//        System.out.println(this.levelManager.getEntitiesPosition());

        try
        {
            Thread.sleep(1000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

        show(this.levelManager.getEntitiesPosition());

        this.levelManager.moveAllDynamicEntities();

        show(this.levelManager.getEntitiesPosition());

//        try
//        {
//            Thread.sleep(1000);
//        }
//        catch(InterruptedException ex)
//        {
//            Thread.currentThread().interrupt();
//        }
//
//        show(this.levelManager.getEntitiesPosition());

//        Iterator it = entitiesPosition.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry pair = (Map.Entry)it.next();
//
//            if(pair.getKey() instanceof DynamicEntity) {
//                System.out.println(pair.getKey() + " = " + ((Tile) pair.getValue()).getY() + ((Tile) pair.getValue()).getY());
//            }
//
//            it.remove(); // avoids a ConcurrentModificationException
//        }
    }

    private void show(HashMap<Entity, Tile> entitiesPosition) {
        System.out.println("\nPositions:");
        Iterator it = entitiesPosition.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();

            if(pair.getKey() instanceof DynamicEntity) {
                System.out.println(pair.getKey() + " = " + pair.getValue() + " / " + ((Tile) pair.getValue()).getX() + "," + ((Tile) pair.getValue()).getY());
            }

//            it.remove(); // avoids a ConcurrentModificationException
        }
        System.out.println();
    }

    public void quitGame() {

    }
}
