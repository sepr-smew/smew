package sepr.smew.ces.entities;

import com.badlogic.ashley.core.Entity;



import sepr.smew.ces.components.*;

import com.badlogic.gdx.physics.box2d.World;

/**
 * The one and only Smew! Controled with the keyboard.
 */
public class ObstacleEntity extends Entity {
    public ObstacleEntity(World world, float x, float y, float width, float height) {
        this.add(PhysicsComponent.staticBox(world, x, y, width, height));
    }
}

