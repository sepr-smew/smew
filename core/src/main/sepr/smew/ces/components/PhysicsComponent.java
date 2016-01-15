package sepr.smew.ces.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.math.Vector2;

public class PhysicsComponent implements Component {
    /**
     * The physics body. Can be dynamic, static or kinematic. Remember to
     * attach a shape fixture when you have the body.
     */
    public Body body;

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
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(x, y);

        PolygonShape boundingBox = new PolygonShape();
        boundingBox.setAsBox(width, height);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = boundingBox;

        Body body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        boundingBox.dispose();

        PhysicsComponent pc = new PhysicsComponent();
        pc.body = body;
        return pc;
    }
}
