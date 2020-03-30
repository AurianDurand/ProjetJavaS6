package sample.entityManager;

import sample.parser.Tile;

import java.awt.*;

public class Entity {

    private static int lastId = 1;
    private int id;
    private Tile tile;

    public Entity() {
        this.id = lastId++;
    }

    public Entity(Tile tile) {
        this.id = lastId++;
        this.tile = tile;
    }

    public int getId() { return this.id; }
    public Tile getTile() { return this.tile; }

}
