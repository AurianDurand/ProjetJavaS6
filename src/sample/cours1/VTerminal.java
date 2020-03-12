package sample.cours1;

import java.util.Observable;
import java.util.Observer;

public class VTerminal implements Observer {

    private ModelePacmanSimple modele;

    public VTerminal (ModelePacmanSimple modele) {
        this.modele = modele;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("View notified");
    }
}
