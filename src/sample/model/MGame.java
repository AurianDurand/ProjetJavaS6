package sample.model;

import java.util.HashMap;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MGame extends Observable implements Runnable {

    private MLevelManager levelManager;
    private Thread th;
    private boolean isRunning;
    long deltaTime = 30;//ms

    public MGame() {
        this.levelManager = new MLevelManager();
        HashMap<MLevelManager.Level, String> maps = new HashMap<MLevelManager.Level, String>();
        maps.put(MLevelManager.Level.LEVEL_1, "map_1.tmx");
        maps.put(MLevelManager.Level.LEVEL_2, "map_2.tmx");
        maps.put(MLevelManager.Level.LEVEL_3, "map_3.tmx");
        this.levelManager.setLevels(maps);

        this.isRunning = false;
        this.th = new Thread(this);
        this.th.setDaemon(true);
    }

    public boolean initialization() {
        return this.levelManager.load(MLevelManager.Level.LEVEL_3);
    }

    public MMap getMap() {
        return this.levelManager.getMap();
    }

    @Override
    public void run() {
        while(isRunning) {
            this.levelManager.update(this.deltaTime);

            setChanged();
            notifyObservers(); // notification de l'observer

            try {
                Thread.sleep(this.deltaTime); // pause
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
