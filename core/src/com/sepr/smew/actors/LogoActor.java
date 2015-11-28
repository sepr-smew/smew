package com.sepr.smew.actors;

import com.badlogic.gdx.graphics.Texture;

import com.sepr.smew.SpritesheetController;

public class LogoActor extends GameObject {
    // Remember now, IMAGE is actually a 1x1 spritesheet.
    private static final Texture IMAGE = new Texture("badlogic.jpg");

    public LogoActor() {
        super(new SpritesheetController(IMAGE, 1, 1));
    }
}
