package com.sepr.smew.screens;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import com.sepr.smew.SmewFighters;
import com.sepr.smew.actors.LogoObject;

/**
 * A level in the game set in a given location.
 */
public class RoundScreen extends AbstractScreen {
    private final World world;

    public RoundScreen(final SmewFighters gam) {
        super(gam);
        // The first parameter is gravity, which is 0 for a top-down game.
        // The second parameter apparently improves performance.
        world = new World(Vector2.Zero, true);
        getStage().addActor(new LogoObject(world));
    }
}
