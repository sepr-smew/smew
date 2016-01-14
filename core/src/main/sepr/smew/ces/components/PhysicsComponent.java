package sepr.smew.ces.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class PhysicsComponent implements Component {
    /**
     * The physics body. Can be dynamic, static or kinematic. Remember to
     * attach a shape fixture when you have the body.
     */
    public Body body;

    /**
     * Convenience function to create a kinematic box body. Remember to add
     * the body to the world, else it won't be affected by other objects.
     */
    public static PhysicsComponent kinematicBox(World world, int x, int y, int hx, int hy) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(x, y);

        PolygonShape boundingBox = new PolygonShape();
        boundingBox.setAsBox(hx, hy);

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
