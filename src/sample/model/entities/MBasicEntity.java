package sample.model.entities;

import sample.parser.Tile;

public class MBasicEntity {
    private static int lastId = 1;
    private int id;
    private Tile tile;
    boolean isAlive;

    public MBasicEntity(Tile tile) {
        this.id = lastId++;
        this.tile = tile;
        this.isAlive = true;
    }

    public int getId() { return this.id; }
    public Tile getTile() { return this.tile; }
    public boolean getIsAlive() { return this.isAlive; }

    public void kill() {
        this.isAlive = false;
    }
}
