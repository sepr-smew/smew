package sepr.smew.ces.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import sepr.smew.ces.components.EnemyComponent;
import sepr.smew.ces.components.PhysicsComponent;
import sepr.smew.ces.entities.SmewEntity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EnemySystemTest {
    private Engine      engine;
    private Entity      entity;
    private World       world;
    private SmewEntity  smew;
    private EnemySystem es;

    @Before
    public void setUp() {
        engine = new Engine(); // we're not mocking Engine or World, because... what's the point?
        world  = new World(Vector2.Zero, true);
        entity = new Entity();
        smew   = new SmewEntity(world);
        es     = new EnemySystem(1, smew);

        engine.addSystem(es);
    }

    private void step(float delta) {
        engine.update(delta);
        world.step(delta, 6, 2);
    }

    @Ignore // Needs to load the tests in an ApplicationListener
    public void FollowsSmew() {
        // Setup entity
        entity.add(new EnemyComponent());
        PhysicsComponent physics = PhysicsComponent.dynamicBox(world, 50, 50, 5, 10);
        entity.add(physics);
        smew.add(PhysicsComponent.dynamicBox(world, 50, 100, 5, 10));
        // Add entity
        engine.addEntity(entity);
        // Update the engine and world.
        step(0.1f);
        // Check to see that it hasn't moved.
        assertEquals(physics.getBottomLeft(), new Vector2(50, 50));
    }
}
