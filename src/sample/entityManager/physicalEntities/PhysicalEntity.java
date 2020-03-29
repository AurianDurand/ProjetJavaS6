package sample.entityManager.physicalEntities;

import sample.entityManager.Entity;

public class PhysicalEntity implements Entity {

    private String assetPath;

    public PhysicalEntity(String assetPath) {
        this.assetPath = assetPath;
    }
}
