package sepr.smew.screens;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;

import com.badlogic.ashley.core.Engine;

import sepr.smew.ces.entities.*;
import sepr.smew.ces.systems.*;


import sepr.smew.SmewFighters;

/**
 * A level in the game set in a given location.
 * TODO(avinashbot): Manage pausing.
 */
public class RoundScreen extends AbstractScreen {
    private final World world;

    public RoundScreen(final SmewFighters game) {
        super(game);
        world = new World(Vector2.Zero, true);
        
        KeyboardMovementSystem ks = new KeyboardMovementSystem();
        Gdx.input.setInputProcessor(ks);
        engine.addSystem(ks);
        
        RenderSystem rs = new RenderSystem(new SpriteBatch());
        engine.addSystem(rs);
        
        SpritesheetSystem ss = new SpritesheetSystem();
        engine.addSystem(ss);
        
        SmewEntity smew = new SmewEntity(world);
        engine.addEntity(smew);
        
        
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        world.step(deltaTime, 6, 2); // NOTE(avinashbot): Prefer a fixed step?
        //super.update(deltaTime);//Doesn't exist
    }
}
