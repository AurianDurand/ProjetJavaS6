package sample.model;

import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MGame extends Observable implements Runnable {

    private LevelManager levelManager;
    private Thread th;

    public MGame() {
        this.levelManager = new LevelManager();
        th = new Thread(this);
    }

    @Override
    public void run() {
        while(true) {
            //update entities position
            setChanged();
            notifyObservers(); // notification de l'observer

            try {
                Thread.sleep(300); // pause
            } catch (InterruptedException ex) {
                Logger.getLogger(MGame.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void start() {
        System.out.println("Game started");
        th.start();
    }

    public void stop() {
        System.out.println("Game stoped");
        th.stop();
    }
}
