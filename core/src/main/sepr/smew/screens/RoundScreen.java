package sepr.smew.screens;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;

import com.badlogic.ashley.core.Engine;

import com.badlogic.gdx.graphics.OrthographicCamera;

import sepr.smew.SmewFighters;
import sepr.smew.ces.entities.*;
import sepr.smew.ces.systems.*;
import sepr.smew.ces.components.*;
import sepr.smew.map.*;

import com.badlogic.gdx.ScreenAdapter;

import com.badlogic.ashley.core.Family;

/**
 * A level in the game set in a given location.
 * TODO(avinashbot): Manage pausing.
 */
public class RoundScreen extends AbstractScreen {
    public Map map;
    //public Engine engine;
    public Batch batch;
    public World world;
    public OrthographicCamera camera;

    
    public RoundScreen(final SmewFighters game) {
        super(game);
        
        camera = game.camera;
        
        world = new World(Vector2.Zero, true);
        
        batch = new SpriteBatch();
        
        
        
        SmewEntity smew = new SmewEntity(world);
        CameraEntity cameraEntity = new CameraEntity(camera, 512f, 320f);
        
        engine.addEntity(smew);
        engine.addEntity(cameraEntity);
        
        SpritesheetSystem spritesheetSystem = new SpritesheetSystem(1);

        SmewMovementSystem smewMovementSystem = new SmewMovementSystem(2);
        Gdx.input.setInputProcessor(smewMovementSystem.inputProcessor);
        
        CameraMovementSystem cameraMovementSystem = new CameraMovementSystem(2);
        
        MapRenderSystem mapRenderSystem = new MapRenderSystem(2);
        
        RenderSystem renderSystem = new RenderSystem(3, batch);
        
        
        engine.addSystem(spritesheetSystem);
        engine.addSystem(smewMovementSystem);
        engine.addSystem(cameraMovementSystem);
        engine.addSystem(mapRenderSystem);
        engine.addSystem(renderSystem);
        

        map = new Map("Maps/test1.tmx", world, batch, game.camera);
        MapEntity mapEntity = map.entity();
        
        engine.addEntity(mapEntity);
    }

    @Override
    public void render(float deltaTime) {
        world.step(deltaTime, 6, 2); // NOTE(avinashbot): Prefer a fixed step?
        super.render(deltaTime);
    }
}
