package sepr.smew.ces.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.ashley.core.Entity;

/**
 * An object that is affected by physics in a given Box2D world. Remember to
 * add a TextureComponent as well if you want it to show up.
 */
public class PhysicsComponent implements Component {
    /**
     * The physics body. Can be dynamic, static or kinematic. Remember to
     * attach a shape fixture when you have the body.
     */
    public Body body;
    private float width;
    private float height;

    public static PhysicsComponent staticBox(Entity entity, World world, float x, float y, float width, float height) {
        return box(entity, world, x, y, width, height, BodyDef.BodyType.StaticBody);
    }

    public static PhysicsComponent dynamicBox(Entity entity, World world, float x, float y, float width, float height) {
        return box(entity, world, x, y, width, height, BodyDef.BodyType.DynamicBody);
    }

    public static PhysicsComponent kinematicBox(Entity entity, World world, float x, float y, float width, float height) {
        return box(entity, world, x, y, width, height, BodyDef.BodyType.KinematicBody);
    }
    
    public static PhysicsComponent sensorBox(Entity entity, World world, float x, float y, float width, float height) {
        return box(entity, world, x, y, width, height, BodyDef.BodyType.StaticBody, true);
    }
    
    private static PhysicsComponent box(Entity entity, World world, float x, float y, float width, float height, BodyDef.BodyType bodyType) {
        return box(entity, world, x, y, width, height, bodyType, false);
    }
    
    private static PhysicsComponent box(Entity entity, World world, float x, float y, float width, float height, BodyDef.BodyType bodyType, boolean isSensor) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(x+(width/2), y+(height/2));

        PolygonShape boundingBox = new PolygonShape();
        boundingBox.setAsBox(width/2, height/2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = boundingBox;
        fixtureDef.isSensor = isSensor;

        Body body = world.createBody(bodyDef);
        body.setUserData(entity);
        body.createFixture(fixtureDef);
        boundingBox.dispose();

        PhysicsComponent pc = new PhysicsComponent();
        pc.body = body;
        pc.width=width;
        pc.height=height;
        return pc;
    }
    
    public Vector2 getBottomLeft(){
        return body.getPosition().sub(width/2, height/2);
    }
    
    public Vector2 getCentre(){
        return body.getPosition();
    }
    
}


