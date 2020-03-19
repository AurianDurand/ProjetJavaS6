package sample.cours1;

import sample.parser.Layers;
import sample.parser.TMXParser;
import sample.parser.Tile;

import java.util.List;
import java.util.Observable;

public class ModelePacmanSimple extends Observable implements Runnable {

    public ModelePacmanSimple()
    {
        TMXParser parser = new TMXParser();
        Layers cLayers = parser.Parse("Res/Maps/", "map_1.tmx");

        List<Tile[][]> layers = cLayers.getLayers();
        //Exemple d'utilisation
        //On test si le parser n'a pas d'erreur
        if(layers != null){
            //On parcourt chaque layer
            for(Tile[][] layer : layers){
                //On parcourt chaque case
                for(int i = 0; i < layer.length; i++){
                    for(int j = 0; j < layer[i].length; j++){
                        if(layer[i][j] != null){
                            //la case est pleine
                            System.out.print(layer[i][j].getGid());//getter -> Source, taille, posTexture, type
                        }
                        else{
                            //la case est vide
                            System.out.print("0");
                        }
                        System.out.print(", ");
                    }
                    System.out.println("");
                }
                System.out.println("");
            }
        }

        System.out.println("Map de taille : " + cLayers.getWidth() + " : " + cLayers.getHeight());
        System.out.println("Tile de taille : " + cLayers.getTilewidth() + " : " + cLayers.getTileheight());
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
