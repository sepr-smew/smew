package com.sepr.smew.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.Texture;

public class LogoActor extends Actor {
    // NOTE(avinashbot): When and how the hell do I dispose this thing?
    private Texture img;

    public LogoActor() {
        img = new Texture("badlogic.jpg");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(img, 0, 0);
    }
}
