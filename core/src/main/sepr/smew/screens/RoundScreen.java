package sepr.smew.screens;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;

import com.badlogic.ashley.core.Engine;

import sepr.smew.SmewFighters;
import sepr.smew.ces.entities.*;
import sepr.smew.ces.systems.*;
import sepr.smew.map.*;

/**
 * A level in the game set in a given location.
 * TODO(avinashbot): Manage pausing.
 */
public class RoundScreen extends AbstractScreen {
    private final World world;

    public RoundScreen(final SmewFighters game) {
        super(game);
        world = new World(Vector2.Zero, true);

        engine.addSystem(new SpritesheetSystem(1));

        SmewMovementSystem ks = new SmewMovementSystem(2);
        Gdx.input.setInputProcessor(ks.inputProcessor);
        engine.addSystem(ks);
        
        Batch batch = new SpriteBatch();
        engine.addSystem(new RenderSystem(3, batch));
        engine.addSystem(new MapRenderSystem(2));
        MapEntity mapEntity = new Map("Maps/test1.tmx", world, batch, game.camera).entity();
        engine.addEntity(mapEntity);
        
        
        SmewEntity smew = new SmewEntity(world);
        engine.addEntity(smew);
    }

    @Override
    public void render(float deltaTime) {
        world.step(deltaTime, 6, 2); // NOTE(avinashbot): Prefer a fixed step?
        super.render(deltaTime);
    }
}
