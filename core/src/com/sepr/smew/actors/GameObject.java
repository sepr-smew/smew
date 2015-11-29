package com.sepr.smew.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import com.sepr.smew.Animator;

abstract class GameObject extends Actor {
    private Animator animator;
    private Vector2 position;

    public GameObject(Animator newAnimator) {
        animator = newAnimator;
        position = new Vector2(0, 0);
    }

    protected Animator getAnimator() {
        return animator;
    }

    protected Vector2 getPosition() {
        return position;
    }

    @Override
    public void act(float deltaTime) {
        animator.update(deltaTime);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(animator.getCurrentSprite(), position.x, position.y);
    }
}
