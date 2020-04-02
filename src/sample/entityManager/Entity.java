package sample.entityManager;

import sample.parser.Tile;

public class Entity {

    private static int lastId = 1;
    private int id;
    private Tile tile;
    private String assetPath;

    public Entity(String assetPath) {
        this.id = lastId++;
        this.assetPath = assetPath;
    }

    public Entity(Tile tile) {
        this.id = lastId++;
        this.tile = tile;
    }

    public int getId() { return this.id; }
    public Tile getTile() { return this.tile; }
    public String getAssetPath() {
        return assetPath;
    }
}
