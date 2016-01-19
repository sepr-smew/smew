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

import sepr.smew.util.*;

import com.badlogic.gdx.ScreenAdapter;

import com.badlogic.ashley.core.Family;


import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.math.Matrix4;

/**
 * A level in the game set in a given location.
 * TODO(avinashbot): Manage pausing.
 */
public class RoundScreen extends AbstractScreen {
    public TileMapManager map;
    //public Engine engine;
    public Batch batch;
    public Batch UIBatch;
    public World world;
    public OrthographicCamera camera;
    public OrthographicCamera UICamera;
    
    Box2DDebugRenderer debugRenderer;
    Matrix4 debugMatrix;

    
    public RoundScreen(final SmewFighters game) {
        super(game);
        
        camera = new OrthographicCamera(game.viewport.getWorldWidth(), game.viewport.getWorldHeight());
        UICamera = new OrthographicCamera(game.viewport.getWorldWidth(), game.viewport.getWorldHeight());
        UICamera.setToOrtho(false, game.viewport.getWorldWidth(), game.viewport.getWorldHeight());
        //UICamera = game.camera;
        
        world = new World(Vector2.Zero, true);
        
        batch = new SpriteBatch();
        UIBatch = new SpriteBatch();
        
        //map = new Map("Maps/test1.tmx", world, batch, game.camera);
        //map = new TileMapManager("Maps/test1.tmx", batch);
        map = new TileMapManager("Maps/SmewMapEdited.tmx", batch);
        map.generateTileCollision(world);
        
        for (MapLayerEntity e : map.getEntities()){
            engine.addEntity(e);
        }
        
        for (EnemyEntity e : map.generateEnemies(world)){
            engine.addEntity(e);
        }
        
        //MapEntity mapEntity = map.entity();
        SmewEntity smew = new SmewEntity(world);
        StatsEntity stats = new StatsEntity();
        //EnemyEntity enemy = new EnemyEntity(world, 80f, 70f);
        //CameraEntity cameraEntity = new CameraEntity(camera, 128f, 80f);
        CameraEntity cameraEntity = new CameraEntity(camera);
        
        engine.addEntity(smew);
        engine.addEntity(stats);
        //engine.addEntity(enemy);
        engine.addEntity(cameraEntity);
        
        // graphics related systems
        SpritesheetSystem spritesheetSystem = new SpritesheetSystem(1);
        DirectionalSpritesheetSystem dSpritesheetSystem = new DirectionalSpritesheetSystem(1);
        CameraMovementSystem cameraMovementSystem = new CameraMovementSystem(2, map);
        StatsSystem statsSystem = new StatsSystem(2, stats);
        RenderSystem renderSystem = new RenderSystem(3, batch, UIBatch, camera, UICamera);
        
        engine.addSystem(spritesheetSystem);
        engine.addSystem(dSpritesheetSystem);
        engine.addSystem(cameraMovementSystem);
        engine.addSystem(statsSystem);
        engine.addSystem(renderSystem);
        
        // movement
        SmewMovementSystem smewMovementSystem = new SmewMovementSystem(2);
        Gdx.input.setInputProcessor(smewMovementSystem.inputProcessor);
        
        EnemySystem enemySystem = new EnemySystem(2, smew);
        
        engine.addSystem(smewMovementSystem);
        engine.addSystem(enemySystem);
        
        
        debugRenderer=new Box2DDebugRenderer();
        
    }

    @Override
    public void render(float deltaTime) {
        world.step(deltaTime, 6, 2); // NOTE(avinashbot): Prefer a fixed step?
        super.render(deltaTime);
        //debugMatrix=new Matrix4(camera.combined);
        //debugRenderer.render(world, debugMatrix);
    }
}
