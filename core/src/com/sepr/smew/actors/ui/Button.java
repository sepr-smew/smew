package com.sepr.smew.actors.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.Texture;

// NOTE(avinashbot): If we ever need to inherit from this, make these final.
// TODO(avinashbot): Textures and Text.
public class Button extends Actor {
    private static Texture up = null;
    private static Texture down = null;

    public Button() {
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(up, 0, 0);
    }
}
