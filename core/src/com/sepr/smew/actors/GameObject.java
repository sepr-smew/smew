package com.sepr.smew.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import com.sepr.smew.Animator;

/**
 * A GameObject is an Actor that is visible on-screen.
 * For a GameObject with movement and physics, see PhysicalGameObject.
 */
abstract class GameObject extends Actor {
    /**
     * An animator holds the appropriate textures for the object. The Animator
     * could animate, or stay at the same frame.
     */
    private Animator animator;

    /**
     * Creates a GameObject with the given animator.
     * @param anim a new animator (or an existing one, if you want to
     *        synchonize animations.)
     */
    GameObject(Animator anim) {
        setAnimator(anim);
    }

    /**
     * Get the GameObject Animator.
     */
    protected Animator getAnimator() {
        return animator;
    }

    /**
     * Replace the GameObject Animator and readjust the actor's dimensions.
     * Remember, this will replace the sprites, not just the behaviors.
     */
    protected void setAnimator(Animator anim) {
        animator = anim;
        TextureRegion region = getAnimator().getCurrentSprite();
        setWidth(region.getRegionWidth());
        setHeight(region.getRegionHeight());
    }

    @Override
    public void act(float deltaTime) {
        animator.update(deltaTime);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // FIXME(avinashbot): These use the actor's parameters, which we are
        //                    supposed to set manually.
        batch.draw(
            animator.getCurrentSprite(),
            getX() - animator.spriteWidth/2,
            getY() - animator.spriteHeight/2,
            getOriginX(),
            getOriginY(),
            getWidth(),
            getHeight(),
            getScaleX(),
            getScaleY(),
            getRotation()
        );
    }
}
