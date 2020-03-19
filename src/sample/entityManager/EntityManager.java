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

    public Entity createEntity(EntityType entityType, int x, int y, String assetPath) {

        // first, create the entity
        Entity createdEntity = null;
        switch (entityType) {
            case PACMAN:
                createdEntity = new Pacman(assetPath);
                break;
            case GHOST:
                createdEntity = new Ghost(assetPath);
                break;
            case PACGUM:
                createdEntity = new PacGum(assetPath);
                break;
            case SUPERPACGUM:
                createdEntity = new SuperPacGum(assetPath);
                break;
            case WALL:
                createdEntity = new Wall(assetPath);
                break;
        }

        // second, save the entity into the entitiesList
        entities.add(createdEntity);

        // third, return the created entity
        return createdEntity;
    }

    public ArrayList<Entity> getEntities() {
        return this.entities;
    }

    public void destroyEntity(Entity entity) {
        this.entities.remove(entity);
    }

    public void updateEntity() {

    }
}
