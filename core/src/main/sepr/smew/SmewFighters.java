package sepr.smew;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import sepr.smew.screens.*;

/**
  * The main entry point for all platforms.
  * This class should rarely be altered; most of the code is in the screens.
  * TODO(avinashbot): Use an AssetManager.
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
     * A screen viewport automatically scales to be the size of the screen.
     * Although the game is played at the window's resolution, a stage will
     * probably use FitViewport.
     */
    public ScreenViewport viewport;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);
        //this.setScreen(new StartupScreen(this));
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
