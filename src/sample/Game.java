package sample;

import sample.entityManager.Entity;
import sample.entityManager.dynamicEntities.DynamicEntity;
import sample.map.Tile;
import sample.model.MGame;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game extends Observable implements Runnable {

    private LevelManager levelManager;
    private Thread th;
    private boolean isRunning;

    // each level name is associated with a map path
    private HashMap<String,String> level_mapPath = new HashMap<>();

    public Game() {

        // the game levels
        this.level_mapPath.put("Level 1","map_1.tmx");
        this.level_mapPath.put("Level 2","map_2.tmx");
        this.level_mapPath.put("Level 3","map_3.tmx");

        this.isRunning = false;
        this.th = new Thread(this);
        this.th.setDaemon(true);
    }

    public void loadLevel(String levelName) {
        this.levelManager = new LevelManager(this.level_mapPath.get(levelName));
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

    @Override
    public void run() {
        while(isRunning) {

            this.levelManager.moveAllDynamicEntities();

            System.out.println("-----> game clock");

            setChanged();
            notifyObservers(); // observer notification

        try {
            Thread.sleep(250);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        }
    }

    public void start() {
        System.out.println("GAME STARTED");
        this.th.start();
        this.isRunning = true;
    }

    public void stop() {
        System.out.println("GAME STOPPED");
        this.th.stop();
        this.isRunning = false;
    }
    public sample.map.Map getMap() {
        return this.levelManager.getMap();
    }

}
