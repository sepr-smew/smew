package sepr.smew.ces.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import org.junit.Before;
import org.junit.Test;
import sepr.smew.ces.components.PhysicsComponent;
import sepr.smew.ces.components.SmewMovementComponent;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Test The SmewMovementSystem
 *
 * BEWARE ALL YE WHO ENTER HERE:
 * "There is a maximum movement limit of 2.0 units per time step, given in the file b2Settings.h in the source code."
 */
public class SmewMovementSystemTest {
    private Engine engine;
    private Entity entity;
    private World world;

    private SmewMovementSystem sms;

    @Before
    public void setUp() {
        engine = new Engine(); // we're not mocking Engine or World, because... what's the point?
        world  = new World(Vector2.Zero, true);
        entity = new Entity();
        sms    = new SmewMovementSystem(1);

        engine.addSystem(sms);
    }

    private void step(float delta) {
        engine.update(delta);
        world.step(delta, 6, 2);
    }

    @Test
    public void DoesNotMoveWithoutInput() {
        // Setup entity
        entity.add(new SmewMovementComponent(5f));
        PhysicsComponent physics = PhysicsComponent.dynamicBox(world, 50, 50, 50, 50);
        entity.add(physics);
        // Add entity
        engine.addEntity(entity);
        // Update the engine and world.
        step(0.1f);
        // Check to see that it hasn't moved.
        assertEquals(physics.getBottomLeft(), new Vector2(50, 50));
    }

    @Test
    public void MovesAccordingToMagnitude() {
        // Setup entity
        entity.add(new SmewMovementComponent(5f));
        PhysicsComponent physics = PhysicsComponent.dynamicBox(world, 50, 50, 50, 50);
        entity.add(physics);
        // Add entity
        engine.addEntity(entity);
        // Send in a key event.
        sms.inputProcessor.keyDown(Input.Keys.LEFT);
        // Update the engine and world.
        step(0.1f);
        // Check to see that it has moved.
        assertEquals(physics.getBottomLeft(), new Vector2(49.5f, 50));
    }

    @Test
    public void MovementIsKeySpecific() {
        // Setup entity
        entity.add(new SmewMovementComponent(10f));
        PhysicsComponent physics = PhysicsComponent.dynamicBox(world, 50, 50, 50, 50);
        entity.add(physics);
        // Add entity
        engine.addEntity(entity);
        // Send in a key event.
        sms.inputProcessor.keyDown(Input.Keys.UP);
        // Update the engine and world.
        step(0.1f);
        // Check to see that it has moved.
        assertEquals(physics.getBottomLeft(), new Vector2(50, 51));
    }

    @Test
    public void KeepsMovingOnHold() {
        // Setup entity
        entity.add(new SmewMovementComponent(10f));
        PhysicsComponent physics = PhysicsComponent.dynamicBox(world, 50, 50, 50, 50);
        entity.add(physics);
        // Add entity
        engine.addEntity(entity);
        // Send in a key event.
        sms.inputProcessor.keyDown(Input.Keys.UP);
        // Update the engine and world four times.
        for (int i = 0; i < 4; i++) step(0.1f);
        // Check to see that it has moved correctly.
        assertEquals(physics.getBottomLeft(), new Vector2(50, 54));
    }

    @Test
    public void StopsOnKeyUp() {
        // Setup entity
        entity.add(new SmewMovementComponent(10f));
        PhysicsComponent physics = PhysicsComponent.dynamicBox(world, 50, 50, 50, 50);
        entity.add(physics);
        // Add entity
        engine.addEntity(entity);

        // Send in a key event.
        sms.inputProcessor.keyDown(Input.Keys.UP);
        // Update the engine and world four times.
        for (int i = 0; i < 4; i++) step(0.1f);
        // Stop key event
        sms.inputProcessor.keyUp(Input.Keys.UP);
        // Update the engine and world two times.
        for (int i = 0; i < 2; i++) step(0.1f);
        // More key events!
        sms.inputProcessor.keyDown(Input.Keys.UP);
        // Update the engine and world four times.
        for (int i = 0; i < 4; i++) step(0.1f);

        // Check to see that it has moved correctly.
        assertEquals(physics.getBottomLeft(), new Vector2(50, 58));
    }
}
