package sample.model;

import java.util.HashMap;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MGame extends Observable implements Runnable {

    private LevelManager levelManager;
    private Thread th;
    private boolean isRunning;

    public MGame() {
        this.levelManager = new LevelManager();
        HashMap<LevelManager.Level, String> maps = new HashMap<LevelManager.Level, String>();
        maps.put(LevelManager.Level.LEVEL_1, "map_1.tmx");
        maps.put(LevelManager.Level.LEVEL_2, "map_2.tmx");
        maps.put(LevelManager.Level.LEVEL_3, "map_3.tmx");
        this.levelManager.setLevels(maps);

        this.isRunning = false;
        this.th = new Thread(this);
        this.th.setDaemon(true);
    }

    public boolean initialization() {
        return this.levelManager.load(LevelManager.Level.LEVEL_3);
    }

    public Map getMap() {
        return this.levelManager.getMap();
    }

    @Override
    public void run() {
        while(isRunning) {
            this.levelManager.update();

            setChanged();
            notifyObservers(); // notification de l'observer

            try {
                Thread.sleep(30); // pause
            } catch (InterruptedException ex) {
                Logger.getLogger(MGame.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void start() {
        System.out.println("Game started");
        this.th.start();
        this.isRunning = true;
    }

    public void stop() {
        System.out.println("Game stoped");
        this.th.stop();
        this.isRunning = false;
    }
}
