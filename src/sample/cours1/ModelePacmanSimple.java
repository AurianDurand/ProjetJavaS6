package sample.cours1;

import sample.parser.TMXParser;

import java.util.Observable;

public class ModelePacmanSimple extends Observable implements Runnable {

    public ModelePacmanSimple()
    {
        TMXParser parser = new TMXParser("Res/Maps/", "map_1.tmx");
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setChanged();
            notifyObservers();
        }
    }
}
