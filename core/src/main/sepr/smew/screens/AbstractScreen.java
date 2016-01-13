package sepr.smew.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.GL20;

import sepr.smew.SmewFighters;

/**
 * A Screen with an empty stage and a basic implementation to get things
 * rolling.
 */
abstract class AbstractScreen extends ScreenAdapter {
    /**
     * A reference to the main class for switching to another screen.
     */
    private final SmewFighters game;

    /**
     * The stage that holds and renders all of the objects in the screen.
     */
    private Stage stage;

    AbstractScreen(SmewFighters game, Stage stage) {
        this.game = game;
        this.stage = stage;
    }

    protected SmewFighters getGame() {
        return game;
    }

    protected Stage getStage() {
        return stage;
    }

    @Override
    public void render(float delta) {
        // First clear the screen...
        Gdx.gl.glClearColor(0.5f, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Now simulate the stage, then draw the stage and all its children.
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // The third argument moves the camera position accordingly.
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
