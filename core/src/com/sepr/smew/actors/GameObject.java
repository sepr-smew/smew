package com.sepr.smew.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Batch;

import com.sepr.smew.SpritesheetController;

abstract class GameObject extends Actor {
    protected SpritesheetController sprController;

    protected int x = 0;
    protected int y = 0;

    public GameObject(SpritesheetController mySprController) {
        sprController = mySprController;
    }

    @Override
    public void act(float delta) {
        sprController.update(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(sprController.getCurrentSprite(), x, y);
    }
}
