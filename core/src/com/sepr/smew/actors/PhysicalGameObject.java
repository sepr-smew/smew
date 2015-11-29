package com.sepr.smew.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.sepr.smew.Animator;

abstract class PhysicalGameObject extends GameObject {
    private World world;
    private Body body;

    PhysicalGameObject(Animator anim, World screenWorld) {
        super(anim);

        world = screenWorld;
        body = world.createBody(buildBodyDef());

        PolygonShape shape = buildShape();
        body.createFixture(shape, 1f); // The 1f is the density.
        shape.dispose();
    }

    /**
     * Create a BodyDef from the actor's default X and Y.
     * A BodyDef defines the object's type and position.
     */
    private BodyDef buildBodyDef() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(getX(), getY());
        return bodyDef;
    }

    /**
     * Create a shape from the actor's bounding box.
     */
    private PolygonShape buildShape() {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth(), getHeight());
        return shape;
    }

    /**
     * Gets the Box2d Body.
     */
    protected Body getBody() {
        return body;
    }

    @Override
    public void act(float deltaTime) {
        super.act(deltaTime);
        // Step the world
        world.step(deltaTime, 6, 2);
        // Set the actor position according to the simulation.
        Vector2 bodyPos = body.getPosition();
        setPosition(bodyPos.x, bodyPos.y);
    }
}
