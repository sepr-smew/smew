package com.sepr.smew.screens;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import com.sepr.smew.SmewFighters;
import com.sepr.smew.actors.*;
import com.badlogic.gdx.Gdx;
import com.sepr.smew.utils.WorldManager;

import com.sepr.smew.stage.PhysicsStage;

/**
 * A level in the game set in a given location.
 */
public class RoundScreen extends AbstractScreen {
    private final World world;
    private SmewFighters game;
    Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

    public RoundScreen(final SmewFighters game) {
        super(game, new PhysicsStage(game.screenViewport, game.batch));
        
        this.game=game;
        
        // The first parameter is gravity, which is 0 for a top-down game.
        // The second parameter apparently improves performance.
        world = new World(Vector2.Zero, true);
        WorldManager.setWorld(world);
        
        
        Stage stage = getStage();
        Gdx.input.setInputProcessor(stage);
        
        stage.addActor(new LogoObject());
        
        Actor smew = new SmewObject(500f, 500f);
        stage.addActor(smew);
        stage.setKeyboardFocus(smew);
        
    }
    
    @Override
    public void render(float deltaTime){
        super.render(deltaTime);
        debugRenderer.render(world, game.camera.combined); // rendered after to draw debugging information on top of normal drawings.
    }
}
