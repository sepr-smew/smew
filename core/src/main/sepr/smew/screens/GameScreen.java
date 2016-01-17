package sepr.smew.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import sepr.smew.SmewFighters;
import sepr.smew.ecs.entities.PointsEntity;
import sepr.smew.ecs.entities.SmewEntity;
import sepr.smew.ecs.systems.DisplaySystem;
import sepr.smew.ecs.systems.SmewMovementSystem;
import sepr.smew.util.TileMapManager;

/**
 * Where the magic happens! A level in the game set in a given location.
 */
public class GameScreen extends AbstractScreen {
    private World world;
    private TileMapManager tmManager;
    private final Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

    public boolean paused = false;
    final SmewMovementSystem sms;

    public GameScreen(SmewFighters game) {
        super(game);
        world = new World(Vector2.Zero, true);
        tmManager = new TileMapManager("Maps/test1.tmx", game.batch, game.camera);
        tmManager.generateTileCollision(world);

        Engine e = getEngine();

        sms = new SmewMovementSystem(1);
        Gdx.input.setInputProcessor(sms.inputProcessor);
        e.addSystem(sms);

        e.addSystem(new DisplaySystem(2, game.viewport, game.batch));

        e.addEntity(new SmewEntity(world));

        e.addEntity(new PointsEntity());
    }

    @Override
    public void render(float deltaTime) {
        world.step(deltaTime, 6, 2);

        tmManager.render();                                                  // Render the tiles
        super.render(deltaTime);                                             // Render everything else
        debugRenderer.render(world, new Matrix4(getGame().camera.combined)); // Render debug squares
    }
}
