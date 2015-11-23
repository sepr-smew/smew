package com.sepr.smew.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.GL20;

import com.sepr.smew.SmewFighters;

abstract class AbstractScreen implements Screen {
    protected final SmewFighters game;
    protected final Stage stage;

    public AbstractScreen(final SmewFighters gam) {
        game  = gam;
        stage = new Stage(game.screenViewport, game.batch);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        // First clear the screen...
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Then draw the stage AND all its children.
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // thrid argument moves the camera position accordingly.
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
