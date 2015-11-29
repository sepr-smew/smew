package com.sepr.smew.actors;

import com.badlogic.gdx.graphics.Texture;

import com.sepr.smew.Animator;

/**
 * The actor formed from a refactoring of the example project. Currently just
 * here for testing.
 */
public class LogoActor extends GameObject {
    /**
     * Note that IMAGE is technically a 1x1 spritesheet.
     */
    private static final Texture IMAGE = new Texture("badlogic.jpg");

    public LogoActor() {
        super(new Animator(IMAGE, 1, 1));
    }
}
