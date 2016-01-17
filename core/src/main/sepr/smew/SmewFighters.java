package sepr.smew;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.math.Vector2;

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
    public FitViewport viewport;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        //camera.setToOrtho(false);
        viewport = new FitViewport(128, 80, camera);
        //this.setScreen(new StartupScreen(this));
        this.setScreen(new RoundScreen(this));
    }
    
    @Override
    public void resize(int width, int height){
        viewport.update(width, height, false);
    }

    @Override
    public void render() {
        super.render();
        batch.setProjectionMatrix(camera.combined);
        viewport.apply(false);
        
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
