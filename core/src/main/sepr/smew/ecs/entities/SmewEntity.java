package sepr.smew.ecs.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.World;
import sepr.smew.display.SmewActor;
import sepr.smew.ecs.components.DisplayComponent;
import sepr.smew.ecs.components.PhysicsComponent;
import sepr.smew.ecs.components.SmewMovementComponent;

/**
 * The one and only Smew! Controlled with the keyboard.
 */
public class SmewEntity extends Entity {
    public SmewEntity(World world) {
        this.add(new DisplayComponent(new SmewActor(this)));
        this.add(new SmewMovementComponent(80f));
        this.add(PhysicsComponent.dynamicBox(world, 100, 100, 25, 35));
    }
}
