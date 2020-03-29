package sample.view.structure;

import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class CWritableImage extends WritableImage {
    private int gid;

    public int getGid() { return this.gid; }

    public CWritableImage(int gid, PixelReader reader, int x, int y, int width, int height) {
        super(reader, x, y, width, height);
        this.gid = gid;
    }
}
