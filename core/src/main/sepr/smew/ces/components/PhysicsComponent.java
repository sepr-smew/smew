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
    public float x;
    public float y;
    public float width;
    public float height;


    /**
     * Convenience function to create a kinematic box body. Remember to add
     * the body to the world, else it won't be affected by other objects.
     */
    
    public PhysicsComponent () {}
    
    public PhysicsComponent (float x, float y, float width, float height) {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }
    
    public static PhysicsComponent dynamicBox(World world, float x, float y, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        PolygonShape boundingBox = new PolygonShape();
        boundingBox.setAsBox(width, height);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = boundingBox;
        fixtureDef.density = 1f;

        Body body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        boundingBox.dispose();

        PhysicsComponent pc = new PhysicsComponent(x, y, width, height);
        pc.body = body;
        return pc;
    }
    
    public static PhysicsComponent kinematicBox(World world, float x, float y, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(x, y);

        PolygonShape boundingBox = new PolygonShape();
        boundingBox.setAsBox(width, height);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = boundingBox;

        Body body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        boundingBox.dispose();

        PhysicsComponent pc = new PhysicsComponent(x, y, width, height);
        pc.body = body;
        return pc;
    }
    
    public void update(){
        Vector2 bodyPos = body.getPosition();
        this.x=bodyPos.x;
        this.y=bodyPos.y;
    }
    
}
