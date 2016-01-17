package sepr.smew;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import sepr.smew.screens.BootstrapScreen;
import sepr.smew.screens.GameScreen;

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
     * Although the game is played at the window's resolution, a FitViewport
     * is easiest to work with.
     */
    public FitViewport viewport;
	
	@Override
	public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(512, 320, camera);

        this.setScreen(new GameScreen(this));
	}

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
        batch.setProjectionMatrix(camera.combined);
        viewport.apply(true);
	}

    @Override
    public void dispose() {
        batch.dispose();
    }
}
