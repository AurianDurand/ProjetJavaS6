package sample.parser;

import java.awt.*;

public class Tile {
    private int gid;
    private String source;
    private Point coordTexture;
    private Point size;
    private String type;
    private Double rotation;

    public Tile(int gid, String source, int coordTextureX, int coordTextureY, int width, int height)
    {
        this.gid = gid;
        this.source = source;

        this.coordTexture = new Point(coordTextureX, coordTextureY);
        this.size = new Point(width, height);
        this.type = "";
        this.rotation = 0.0;
    }

    public int getGid() { return this.gid; }
    public String getSource() { return this.source; }
    public String getType() { return this.type; }
    public Point getCoordTexture() { return this.coordTexture; }
    public Point getSize() { return this.size; }
    public Double getRotation() { return this.rotation; }

    public void setType(String type) { this.type = type; }
    public void setRotation(Double rotation) { this.rotation = rotation; }
}
