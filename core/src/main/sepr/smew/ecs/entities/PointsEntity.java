package sepr.smew.ecs.entities;

import com.badlogic.ashley.core.Entity;
import sepr.smew.display.PointsActor;
import sepr.smew.ecs.components.DisplayComponent;
import sepr.smew.ecs.components.PointsComponent;

/**
 * Points.
 */
public class PointsEntity extends Entity {
    public PointsEntity() {
        this.add(new PointsComponent());
        this.add(new DisplayComponent(new PointsActor(this)));
    }
}
