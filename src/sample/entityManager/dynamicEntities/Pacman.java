package sample.entityManager.dynamicEntities;

import sample.map.Tile;

public class Pacman extends DynamicEntity {

    private int lifes = 3; // the life of the player
    private int points = 0; // the points gained by the player

    public Pacman(String assetPath) {
        super(assetPath);
    }

    public int getLifes() {
        return lifes;
    }

    public int getPoints() {
        return points;
    }

    public void loseOneLife() {
        this.lifes --;
    }

    public void gainOnePoint() {
        this.points ++;
    }
}
