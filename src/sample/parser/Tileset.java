package sample.parser;

import java.util.ArrayList;
import java.util.List;

public class Tileset {
    private String source;
    private int width;
    private int height;
    private int tilewidth;
    private int tileheight;
    private List<Tile> tiles;

    public Tileset(String source, int width, int height, int tilewidth, int tileheight)
    {
        tiles = new ArrayList<Tile>();
        this.source = source;
        this.width = width;
        this.height = height;
        this.tilewidth = tilewidth;
        this.tileheight = tileheight;

        this.createTiles();
    }

    private void createTiles()
    {

    }

    public String getSource() { return this.source; }
    public int getWidth() { return this.width; }
    public int getHeight() { return this.height; }
}
