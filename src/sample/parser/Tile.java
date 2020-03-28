package sample.parser;

import java.awt.*;

public class Tile {
    private int gid;
    private String source;
    private Point coordTexture;
    private Point size;
    private int coordTextureX;
    private int coordTextureY;
    private int width;
    private int height;
    private String type;

    public Tile(int gid, String source, int coordTextureX, int coordTextureY, int width, int height)
    {
        this.gid = gid;
        this.source = source;
        this.coordTextureX = coordTextureX;
        this.coordTextureY = coordTextureY;
        this.width = width;
        this.height = height;

        this.coordTexture = new Point(coordTextureX, coordTextureY);
        this.size = new Point(width, height);
        this.type = "";
    }

    public int getGid() { return this.gid; }
    public String getSource() { return this.source; }
    public int getCoordTextureX() { return this.coordTextureX; }
    public int getCoordTextureY() { return this.coordTextureY; }
    public int getWidth() { return this.width; }
    public int getHeight() { return this.height; }
    public String getType() { return this.type; }
    public Point getCoordTexture() { return this.coordTexture; }
    public Point getSize() { return this.size; }

    void setType(String type) { this.type = type; }
}
