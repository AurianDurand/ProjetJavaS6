package sample.map;

import sample.entityManager.dynamicEntities.DynamicEntity;
import sample.entityManager.objects.Object;

public class Tile {

    private int x;
    private int y;

    private Object object = null;
    private DynamicEntity dynamicEntity = null;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }



    // *** getters and setters ***

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Object getObject() {
        return object;
    }

    public DynamicEntity getDynamicEntity() {
        return dynamicEntity;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public void setDynamicEntity(DynamicEntity dynamicEntity) {
        this.dynamicEntity = dynamicEntity;
    }
}
