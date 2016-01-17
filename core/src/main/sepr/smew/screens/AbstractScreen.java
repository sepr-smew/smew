package sepr.smew.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.GL20;
import sepr.smew.SmewFighters;

/**
 * A Screen with an empty engine and a basic implementation to get things
 * rolling.
 */
abstract class AbstractScreen extends ScreenAdapter {
    /**
     * A reference to the main class for switching to another screen.
     */
    private final SmewFighters game;

    /**
     * The Engine manages the Entity Component system. It might seem difficult
     * to get into, but the ECS system is a godsend for multi-developer teams.
     */
    private final Engine engine;

    AbstractScreen(SmewFighters game) {
        this.game = game;
        engine = new Engine();
    }

    protected SmewFighters getGame() {
        return game;
    }

    protected Engine getEngine() {
        return engine;
    }

    @Override
    public void render(float deltaTime) {
        engine.update(deltaTime);
    }
}