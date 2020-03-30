package sample.model;

import sample.parser.Layers;
import sample.parser.TMXParser;
import java.util.HashMap;

public class MLevelManager {
    enum Level {
        LEVEL_1,
        LEVEL_2,
        LEVEL_3,
        LEVEL_4,
        LEVEL_5
    }

    private HashMap<Level, String> maps;
    private MMap map;

    public MLevelManager() {
        maps = new HashMap<>();
    }

    public void setLevels(HashMap<Level, String> maps) {
        this.maps = maps;
    }

    public MMap getMap() {
        return this.map;
    }

    public void update(long deltaTime) {
        this.map.update(deltaTime);
    }

    public boolean load(Level level) {
        TMXParser parser = new TMXParser();
        Layers layers = parser.Parse("src/res/maps/", maps.get(level));

        map = new MMap();
        return map.create(layers);
    }
}
