package sepr.smew.ces.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.World;

import sepr.smew.ces.components.PhysicsComponent;

class SmewEntity extends Entity {
    public SmewEntity(World world) {
        this.add(PhysicsComponent.kinematicBox(world, 50, 50, 100, 100));
    }
}
