package sample.entityManager;

import sample.entityManager.dynamicEntities.Ghost;
import sample.entityManager.dynamicEntities.Pacman;
import sample.entityManager.objects.PacGum;
import sample.entityManager.objects.SuperPacGum;
import sample.entityManager.physicalEntities.Wall;
import sample.map.Tile;

import java.util.ArrayList;

public class EntityManager {

    private ArrayList<Entity> entities = new ArrayList<>();

    public EntityManager() {
    }

    public void createEntity(EntityType entityType, int x, int y, String assetPath) {
        switch (entityType) {
            case PACMAN:
                entities.add(new Pacman(x,y,assetPath));
                break;
            case GHOST:
                entities.add(new Ghost(x,y,assetPath));
                break;
            case PACGUM:
                entities.add(new PacGum(x,y,assetPath));
                break;
            case SUPERPACGUM:
                entities.add(new SuperPacGum(x,y,assetPath));
                break;
            case WALL:
                entities.add(new Wall(x,y,assetPath));
                break;
        }
    }

    public ArrayList<Entity> getEntities() {
        return this.entities;
    }

    public void destroyEntity(Entity entity) {
        this.entities.remove(entity);
    }

    public void updateEntity() {

    }

    private boolean testCollide() {
        return false;
    }
}
