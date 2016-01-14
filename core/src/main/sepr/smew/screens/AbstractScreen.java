package sepr.smew.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.ashley.core.Engine;
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
     * The Engine manages the Entity Component system. It might seem difficult
     * to get into, but the CES system is a godsend for multi-developer teams.
     */
    protected final Engine engine;

    AbstractScreen(SmewFighters gam) {
        game = gam;
        engine = new Engine();
    }

    protected SmewFighters getGame() {
        return game;
    }

    @Override
    public void render(float deltaTime) {
        // First clear the screen...
        Gdx.gl.glClearColor(0.5f, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        engine.update(deltaTime);
    }
}
