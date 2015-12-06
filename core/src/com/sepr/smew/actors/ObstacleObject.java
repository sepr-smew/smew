package com.sepr.smew.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.sepr.smew.Animator;

class ObstacleObject extends PhysicalGameObject {
    ObstacleObject(Animator anim) {
        super(anim);
    }

    /**
     * Create a BodyDef from the actor's default X and Y.
     * A BodyDef defines the object's type and position.
     * FIXME(avinashbot): This doesn't override the parent's buildBodyDef. ON
     *                   PURPOSE.
     */
    private BodyDef buildBodyDef() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(getX(), getY());
        return bodyDef;
    }

}
