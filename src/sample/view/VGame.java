package sample.view;

import sample.model.MGame;

import java.util.Observable;
import java.util.Observer;

public class VGame implements Observer {
    private MGame game;

    public VGame(MGame game) {
        this.game = game;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("View notified");
    }
}
