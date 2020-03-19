package sample;

public class Game {

    private LevelManager levelManager;

    public Game() {
    }

    public void startLevel() {
        this.levelManager = new LevelManager("Level 1");
    }

    public void quitGame() {

    }
}
