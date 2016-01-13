package sepr.smew.stage;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

/**
 * This stage simply updates the world physics every act call.
 */
public class PhysicsStage extends Stage {

    public final World world;

    public PhysicsStage(Viewport viewport, Batch batch) {
        super(viewport, batch);
        // Gravity is 0, and objects can sleep (to conserve CPU time).
        world = new World(Vector2.Zero, true);
    }

    @Override
    public void act(float deltaTime) {
        // A fixed physics step is recommended, but to annoying to implement.
        // 6, 2 are default values are recommended by libGDX.
        world.step(deltaTime, 6, 2);
        super.act(deltaTime);
    }
}
