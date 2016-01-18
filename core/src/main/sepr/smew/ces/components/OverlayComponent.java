package sepr.smew.ces.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Represents an Entity that isn't part of the physics world, but overlayed on
 * top of it, like a UI object or something.
 */
public class OverlayComponent implements Component {
    public float x;
    public float y;

    public OverlayComponent(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public Vector2 getPosition(){
        return new Vector2(x, y);
    }
}
