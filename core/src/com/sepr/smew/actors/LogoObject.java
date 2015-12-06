package com.sepr.smew.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

import com.sepr.smew.Animator;

/**
 * The actor formed from a refactoring of the example project. Currently just
 * here for testing.
 */
public class LogoObject extends PhysicalGameObject {
    /**
     * Note that image is technically a 1x1 spritesheet.
     */
    private static final Texture image = new Texture("badlogic.jpg");

    public LogoObject() {
        super(new Animator(image, 1, 1));
    }
}
