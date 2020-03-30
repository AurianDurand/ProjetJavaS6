package sample.model.entities;

import sample.parser.Tile;

public class MBasicEntity {
    public enum ENTITY_TYPE {
        PACMAN,
        GHOST,
        PACGUM,
        WALL,
        TILE
    }

    private static int lastId = 1;
    private int id;
    private Tile tile;
    boolean isAlive;
    private ENTITY_TYPE type;

    public MBasicEntity(Tile tile, ENTITY_TYPE type) {
        this.id = lastId++;
        this.tile = tile;
        this.isAlive = true;
        this.type = type;
    }

    public int getId() { return this.id; }
    public Tile getTile() { return this.tile; }
    public boolean getIsAlive() { return this.isAlive; }
    public ENTITY_TYPE getType() { return this.type; }

    public void kill() {
        this.isAlive = false;
    }
}
