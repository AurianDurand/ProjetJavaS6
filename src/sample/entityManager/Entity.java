package sample.entityManager;

import sample.map.Tile;

import java.awt.*;

public class Entity {

    private Point point = new Point();
    private String assetPath;

    public Entity(int x, int y, String assetPath) {
        this.point.setLocation(x,y);
        this.assetPath = assetPath;
    }

    public void onCollide(Entity entity) {

    }

    public Point getPoint() {
        return this.point;
    }
}
