package sample.parser;

import java.util.ArrayList;
import java.util.List;

public class Layers {
    private int width;
    private int height;
    private int tilewidth;
    private int tileheight;
    List<Tile[][]> layers;

    public Layers(int width, int height, int tilewidth, int tileheight){
        this.width = width;
        this.height = height;
        this.tilewidth = tilewidth;
        this.tileheight = tileheight;
        layers = new ArrayList<Tile[][]>();
    }

    public void add(Tile[][] layer){
        this.layers.add(layer);
    }

    public void clear(){
        this.layers.clear();
    }

    public int getWidth() { return this.width; }
    public int getHeight() { return this.height; }
    public int getTilewidth() { return this.tilewidth; }
    public int getTileheight() { return this.tileheight; }
    public List<Tile[][]> getLayers() { return this.layers; }
}
