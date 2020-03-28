package sample.model;

import sample.parser.Layers;
import sample.parser.TMXParser;
import java.util.HashMap;

public class LevelManager {
    enum Level {
        LEVEL_1,
        LEVEL_2,
        LEVEL_3,
        LEVEL_4,
        LEVEL_5
    }

    private HashMap<Level, String> maps;
    private Map map;

    public LevelManager() {
        maps = new HashMap<>();
    }

    public void setLevels(HashMap<Level, String> maps) {
        this.maps = maps;
    }

    public Map getMap() {
        return this.map;
    }

    public void update() {

    }

    public boolean load(Level level) {
        TMXParser parser = new TMXParser();
        Layers layers = parser.Parse("src/res/maps/", maps.get(level));

        map = new Map();
        return map.create(layers);
    }
}
