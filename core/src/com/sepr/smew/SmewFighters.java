package com.sepr.smew;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.sepr.smew.screens.MainMenuScreen;

// TODO(avinashbot): Use javadoc or whatever the documentation standard is.
// TODO(avinashbot): Use an AssetManager (see: https://github.com/libgdx/libgdx/wiki/Managing-your-assets)
public class SmewFighters extends Game {
    // NOTE(avinashbot): Do we need to make these private and write getters?
    public SpriteBatch batch;
    public OrthographicCamera camera;
    public ScreenViewport screenViewport;

    @Override
    public void create() {
        batch = new SpriteBatch();

        // FIXME(avinashbot): Magic Numbers! Find the difference between Camera, Viewport and Desktop!
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);

        screenViewport = new ScreenViewport();
        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
