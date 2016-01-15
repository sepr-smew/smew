package sepr.smew.ces.components;

import com.badlogic.ashley.core.Component;

/**
 * Stores the width and height of the object and allows clipping of the
 * texture.
 */
public class BoundsComponent implements Component {
    public int width;
    public int height;

    public BoundsComponent(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
