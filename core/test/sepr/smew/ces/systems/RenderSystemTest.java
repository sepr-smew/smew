package sepr.smew.ces.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;

import sepr.smew.ces.components.*;

/**
 * Test the Render System (look up Test Doubles on Wikipedia!)
 */
public class RenderSystemTest {
    private Engine engine;
    private Entity entity;

    private Batch batch;
    private Batch uiBatch;
    private OrthographicCamera camera;
    private OrthographicCamera uiCamera;

    private RenderSystem rs;

    @Before
    public void setUp() {
        engine   = new Engine();
        entity   = new Entity();
        batch    = mock(SpriteBatch.class); // We're mocking because we don't want to draw.
        uiBatch  = mock(SpriteBatch.class);
        camera   = mock(OrthographicCamera.class);
        uiCamera = mock(OrthographicCamera.class);
        rs       = new RenderSystem(1, batch, uiBatch, camera, uiCamera);

        engine.addSystem(rs);
    }

    @Test
    public void RendersTexturedPhysicsEntity() {
        PhysicsComponent pc = PhysicsComponent.dynamicBox(new World(Vector2.Zero, true), 15, 20, 25, 30);
        TextureComponent tc = new TextureComponent(35, 40);

        // Add the components to the entity.
        entity.add(pc);
        entity.add(tc);
        // Add the entity to the engine.
        engine.addEntity(entity);
        // Step the engine so that stuff happens.
        engine.update(0.1f);
        // Check that draw was called correctly.
        verify(batch).draw(tc.textureRegion, 15f, 20f, 35f, 40f);
    }

    @Test
    public void RendersTexturedOverlayEntity() {
        OverlayComponent oc = new OverlayComponent(15, 20);
        TextureComponent tc = new TextureComponent(25, 30);

        // Add the components to the entity.
        entity.add(oc);
        entity.add(tc);
        // Add the entity to the engine.
        engine.addEntity(entity);
        // Step the engine so that stuff happens.
        engine.update(0.1f);
        // Check that draw was called correctly.
        verify(uiBatch).draw(tc.textureRegion, 15f, 20f, 25f, 30f);
    }

    @Test
    public void RendersTextOverlayEntity() {
        // This one is basically a black box. You'll have to play the game to see whether text is rendered :)
    }
}
