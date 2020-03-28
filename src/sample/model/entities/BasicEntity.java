package sample.model.entities;

import sample.parser.Tile;

public class BasicEntity {
    private static int lastId = 1;
    private int id;
    private Tile tile;

    public BasicEntity(Tile tile) {
        this.id = lastId++;
        this.tile = tile;
    }

    public int getId() { return this.id; }
    public Tile getTile() { return this.tile; }
}
