package sample.view.structure;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import java.awt.*;
import java.util.HashMap;

public class ImageTemplate {
    private HashMap<Integer, WritableImage> entitiesImage = new HashMap<Integer, WritableImage>();
    private HashMap<String, Image> images = new HashMap<String, Image>();

    public WritableImage getImageOf(int entityId) {
        return this.entitiesImage.get(entityId);
    }

    public Image getImage(String imageSource) {
        this.images.computeIfAbsent(imageSource, k -> new Image(imageSource));
        return this.images.get(imageSource);
    }

    public boolean add(int entityId, String imageSource, Point coordTexture, Point tilesize) {
        Image img = this.getImage(imageSource);

        if(!img.isError()) {
            this.images.put(imageSource, img);

            int x = coordTexture.x * tilesize.x;
            int y = coordTexture.y * tilesize.y;

            try {
                this.entitiesImage.put(entityId, new WritableImage(this.images.get(imageSource).getPixelReader(), x, y, tilesize.x, tilesize.y));
                return true;
            }
            catch (final IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
