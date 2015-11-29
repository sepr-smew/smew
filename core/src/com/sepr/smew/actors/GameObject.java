package com.sepr.smew.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import com.sepr.smew.SpritesheetController;

abstract class GameObject extends Actor {
    protected SpritesheetController sprController;
    protected Vector2 position;

    public GameObject(SpritesheetController mySprController) {
        sprController = mySprController;
        position = new Vector2(0, 0);
    }

    @Override
    public void act(float delta) {
        sprController.update(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(sprController.getCurrentSprite(), position.x, position.y);
    }
}
