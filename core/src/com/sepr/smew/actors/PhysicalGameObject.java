package com.sepr.smew.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sepr.smew.utils.WorldManager;

import com.sepr.smew.Animator;

abstract class PhysicalGameObject extends GameObject {
    protected World world;
    protected Body body;
    private Animator anim;

    // TODO(avinashbot): Move x and y upstream to GameObject.
    PhysicalGameObject(Animator anim, float x, float y){
        super(anim);
        setWidth(anim.spriteWidth);
        setHeight(anim.spriteHeight);

        setPosition(x, y);

        world = WorldManager.getWorld();
        body = world.createBody(buildBodyDef());

        PolygonShape shape = buildShape();
        body.createFixture(shape, 1f); // The 1f is the density.
        shape.dispose();
    }

    // FIXME(avinashbot): Ambiguous overloads and magic constants. Best to
    //                    remove.
    PhysicalGameObject(Animator anim) {
        this(anim, 300f, 300f);
    }

    /**
     * Create a BodyDef from the actor's default X and Y.
     * A BodyDef defines the object's type and position.
     */
    private BodyDef buildBodyDef() {
        BodyDef bodyDef       = new BodyDef();
        bodyDef.type          = BodyDef.BodyType.DynamicBody;
        bodyDef.linearDamping = 1f;
        bodyDef.fixedRotation = true;

        bodyDef.position.set(
            getX() + getWidth()/2,
            getY() + getHeight()/2
        );

        return bodyDef;
    }

    /**
     * Create a shape from the actor's bounding box.
     */
    private PolygonShape buildShape() {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth()/2, getHeight()/2);
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
        // Set the actor position according to the simulation.
        // The world is updated by PhysicsStage before any actors act.
        Vector2 bodyPos = body.getPosition();
        setPosition(bodyPos.x, bodyPos.y);
    }
}
