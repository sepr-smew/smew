package com.sepr.smew;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.sepr.smew.screens.MainMenuScreen;

/** The main entry point for all platforms.
  * This class should rarely be altered; most of the code is in the screens.
  */
public class SmewFighters extends Game {
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
