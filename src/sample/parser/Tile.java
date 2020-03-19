package sample.parser;

public class Tile {
    private int gid;
    private String source;
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
    }

    public int getGid() { return this.gid; }
    public String getSource() { return this.source; }
    public int getCoordTextureX() { return this.coordTextureX; }
    public int getCoordTextureY() { return this.coordTextureY; }
    public int getWidth() { return this.width; }
    public int getHeight() { return this.height; }
    public String getType() { return this.type; }

    void setType(String type) { this.type = type; }
}
