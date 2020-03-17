package sample.entityManager;

import sample.map.Tile;

public class Entity {

    private Tile tile;

    public Entity(Tile tile) {
        this.tile = tile;
    }

    public void onCollide(Entity entity) {

    }

    public Tile getTile() {
        return tile;
    }
}
