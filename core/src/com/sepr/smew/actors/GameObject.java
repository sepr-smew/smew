package com.sepr.smew.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import com.sepr.smew.SpritesheetController;

abstract class GameObject extends Actor {
    private SpritesheetController sprController;
    private Vector2 position;

    public GameObject(SpritesheetController mySprController) {
        sprController = mySprController;
        position = new Vector2(0, 0);
    }

    protected SpritesheetController getSpritesheetController() {
        return sprController;
    }

    protected Vector2 getPosition() {
        return position;
    }

    @Override
    public void act(float deltaTime) {
        sprController.update(deltaTime);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(sprController.getCurrentSprite(), position.x, position.y);
    }
}
