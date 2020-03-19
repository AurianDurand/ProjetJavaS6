package sample.map;

import sample.entityManager.Entity;
import sample.entityManager.dynamicEntities.DynamicEntity;
import sample.entityManager.objects.Object;

import java.awt.*;
import java.util.ArrayList;

public class Tile {

    private Point point = new Point();

    private ArrayList<Entity> entities = new ArrayList<>();

    public Tile(int x, int y) {

        this.point.setLocation(x,y);
    }



    // *** getters and setters ***

    public int getX() {
        return this.point.x;
    }

    public int getY() {
        return this.point.y;
    }

    public ArrayList<Entity> getEntities() {
        return this.entities;
    }


    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        this.entities.remove(entity);
    }

}
