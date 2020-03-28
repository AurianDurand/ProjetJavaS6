package sample.model;

import sample.model.entities.BasicEntity;
import sample.parser.Layers;
import sample.parser.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Map {
    enum DIRECTION {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    private Point size;
    private Point tilesize;
    private int nbLayer;
    private Layers layers;
    private ArrayList<HashMap<Point, BasicEntity>> entityMaps;

    public Point getSize() { return this.size; }
    public Point getTilesize() { return this.tilesize; }
    public int getNbLayer() { return this.nbLayer; }

    public Map() {
        this.entityMaps = new ArrayList<>();
    }

    public ArrayList<BasicEntity> getEntitiesByPosition(Point point) {
        ArrayList<BasicEntity> e = new ArrayList<>();

        for(HashMap<Point, BasicEntity> hashMap : this.entityMaps) {
            if(hashMap.containsKey(point)) {
                e.add(hashMap.get(point));
            }
        }
        return e;
    }

    public boolean moveEntity(String entityType) {
//        for (HashMap.Entry<Point, ArrayList<BasicEntity>> entry : mEntities.entrySet()) {
//            ArrayList<BasicEntity> myListOfEntity = entry.getValue();
//            for(BasicEntity e : myListOfEntity) {
//                if(e.getTile().getType().equals(entityType)) {
//                    System.out.println(entry.getKey());
//
//                    Point currentPos = entry.getKey();
//                    Point nextPos = new Point(currentPos.x, currentPos.y - 1);
//                    myListOfEntity.remove(e);
//                    this.addEntity(nextPos, e);
//
//                    System.out.println(nextPos);
//                    return true;
//                }
//            }
//        }


        return false;
    }


    private void addEntity(int idLayer, Point pos, BasicEntity entity) {
        if(idLayer < this.entityMaps.size()) {
            this.entityMaps.get(idLayer).put(pos, entity);
        }
    }

    public boolean create(Layers data) {
        //On test si le parser n'a pas d'erreur
        if(data != null) {
            List<Tile[][]> layers = data.getLayers();
            if(layers != null) {
                this.layers = data;
                this.size = new Point(data.getWidth(), data.getHeight());
                this.tilesize = new Point(data.getTilewidth(), data.getTileheight());
                this.nbLayer = layers.size();

                this.entityMaps.clear();

                //On parcourt chaque layer
                for(int k = 0; k < layers.size(); k++) {
                    Tile[][] layer = layers.get(k);
                    this.entityMaps.add(new HashMap<Point, BasicEntity>());
                    //On parcourt chaque case
                    for (int i = 0; i < layer.length; i++) {
                        for (int j = 0; j < layer[i].length; j++) {
                            //si la case est pleine
                            if (layer[i][j] != null) {
                                //Si il n'y en a pas, on n'en créer une. Sinon on ajoute une autre entité
                                this.addEntity(k, new Point(j, i), new BasicEntity(layer[i][j]));
                            }
                        }
                    }
                }
//                this.display();
                return true;
            }
        }

        return false;
    }

    public void display() {
        //On test si le parser n'a pas d'erreur
        if(this.layers != null) {
            List<Tile[][]> layers = this.layers.getLayers();
            if(layers != null){
                System.out.println("<--------------------------------------------------------------->");
                //On parcourt chaque layer
                for(Tile[][] layer : layers){
                    //On parcourt chaque case
                    for (int i = 0; i < layer.length; i++) {
                        for (int j = 0; j < layer[i].length; j++) {
                            if (layer[i][j] != null) {
                                //la case est pleine
                                System.out.print(layer[i][j].getGid());//getter -> Source, taille, posTexture, type
                            } else {
                                //la case est vide
                                System.out.print("0");
                            }
                            System.out.print(", ");
                        }
                        System.out.println("");
                    }
                    System.out.println("");
                }

                System.out.println("Map de taille : " + this.layers.getWidth() + " : " + this.layers.getHeight());
                System.out.println("Tile de taille : " + this.layers.getTilewidth() + " : " + this.layers.getTileheight());
                System.out.println("<--------------------------------------------------------------->");
            }
        }
    }
}
