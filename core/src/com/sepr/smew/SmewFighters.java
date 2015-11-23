package com.sepr.smew;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import com.sepr.smew.screens.MainMenuScreen;

// TODO(avinashbot): Use javadoc or whatever the documentation standard is.
public class SmewFighters extends Game {
    // NOTE(avinashbot): Do we need to make these private and write getters?
    public SpriteBatch batch;
    public ScreenViewport screenViewport;

    public void create() {
        batch = new SpriteBatch();
        screenViewport = new ScreenViewport();
        this.setScreen(new MainMenuScreen(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
    }
}
