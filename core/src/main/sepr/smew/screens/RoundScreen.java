package sepr.smew.screens;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.math.Vector2;

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
    }

    @Override
    public void render(float deltaTime) {
        world.step(deltaTime); // NOTE(avinashbot): Prefer a fixed step?
        super.update(deltaTime);
    }
}
