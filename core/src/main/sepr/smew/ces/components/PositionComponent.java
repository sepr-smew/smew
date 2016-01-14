package sepr.smew.ces.components;

import com.badlogic.ashley.core.Component;


public class PositionComponent implements Component {
    /**
     * Holds an object's position. Should be removed if every unmovable entity will be a static body (PhysicsComponent).
     */
    public int x;
    public int y;
    public int width;
    public int height;

    public PositionComponent() {
    }

    public PositionComponent(int x, int y, int width, int height) {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }
}
