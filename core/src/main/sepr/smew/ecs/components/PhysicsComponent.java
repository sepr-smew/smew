package sepr.smew.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Implements physics using a Box2D world.
 */
public class PhysicsComponent implements Component {
    /**
     * The physics body. Can be dynamic, static or kinematic. Remember to
     * attach a shape fixture when you have the body.
     */
    public Body body;

    public float width;
    public float height;

    /**
     * Drawing Box2D is difficult. This method returns the coords to use when
     * drawing.
     */
    public Vector2 drawPos() {
        return body.getPosition().sub(width / 2, height / 2);
    }

    public static PhysicsComponent staticBox(World world, float x, float y, float width, float height) {
        return box(world, x, y, width, height, BodyDef.BodyType.StaticBody);
    }

    public static PhysicsComponent dynamicBox(World world, float x, float y, float width, float height) {
        return box(world, x, y, width, height, BodyDef.BodyType.DynamicBody);
    }

    public static PhysicsComponent kinematicBox(World world, float x, float y, float width, float height) {
        return box(world, x, y, width, height, BodyDef.BodyType.KinematicBody);
    }

    private static PhysicsComponent box(World world, float x, float y, float width, float height, BodyDef.BodyType bodyType) {
        // Create a BodyDef with the box type and initial position.
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(x + (width / 2), y + (height / 2));
        // Create a shape to assign to the fixture. Only a box is used in this case.
        PolygonShape boundingBox = new PolygonShape();
        boundingBox.setAsBox(width / 2, height / 2);
        // Attach the shape to the fixture.
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = boundingBox;
        // Create the body using the world and dispose the shape.
        Body body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        boundingBox.dispose();

        PhysicsComponent pc = new PhysicsComponent();
        pc.body = body;
        pc.width = width;
        pc.height = height;
        return pc;
    }
}
