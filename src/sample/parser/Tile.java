package sample.parser;

public class Tile {
    private int id;
    private int coordTextureX;
    private int coordTextureY;

    public Tile(int id, int coordTextureX, int coordTextureY)
    {
        this.id = id;
        this.coordTextureX = coordTextureX;
        this.coordTextureY = coordTextureY;
    }

    public int getId() { return this.id; }
    public int getCoordTextureX() { return this.coordTextureX; }
    public int getCoordTextureY() { return this.coordTextureY; }
}
