package com.sepr.smew;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.sepr.smew.screens.RoundScreen;

/**
  * The main entry point for all platforms.
  * This class should rarely be altered; most of the code is in the screens.
  */
public class SmewFighters extends Game {
    /**
     * A SpriteBatch manages sending commands to OpenGL. It is a rather costly
     * class, so one is created and stored in SmewFighters.
     */
    public SpriteBatch batch;

    /**
     * An OrthographicCamera keeps the sizes of sprites the same despite their
     * distance from the camera, which is what we want for a flat 2D game.
     */
    public OrthographicCamera camera;

    /**
     * Holds the dimensions of the actual window the game is being played at.
     * Unfortunately, CS monitors don't quite do 4K, so we had to run at 720p.
     */
    public ScreenViewport screenViewport;

    @Override
    public void create() {
        batch = new SpriteBatch();

        // FIXME(avinashbot): Magic Numbers! Find the difference between Camera, Viewport and Desktop!
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);

        screenViewport = new ScreenViewport();
        this.setScreen(new RoundScreen(this));
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
