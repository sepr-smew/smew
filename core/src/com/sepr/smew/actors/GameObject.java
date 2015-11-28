package com.sepr.smew.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Batch;

import com.sepr.smew.SpritesheetController;

abstract class GameObject extends Actor {
    protected SpritesheetController sprController;

    protected int x;
    protected int y;

    public GameObject(SpritesheetController mySprController) {
        sprController = mySprController;
        x = y = 0;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(sprController.getCurrentSprite(), x, y);
    }
}
