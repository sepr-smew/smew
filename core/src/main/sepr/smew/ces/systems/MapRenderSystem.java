package sepr.smew.ces.systems;

import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.graphics.OrthographicCamera;
import sepr.smew.ces.components.*;

import sepr.smew.util.*;

/**
 * Takes a SpriteBatch and renders all the components with physics.
 */
public class MapRenderSystem extends IteratingSystem {
    private ComponentMapper<MapComponent> mm = ComponentMapper.getFor(MapComponent.class);
    OrthographicCamera camera;
    private final Batch batch;

    public MapRenderSystem(int priority, Batch batch, OrthographicCamera camera) {
        super(Family
            .all(MapComponent.class)
            .get(), priority);
        this.batch = batch;
        this.camera = camera;
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        MapComponent mc = mm.get(entity);
        batch.begin();
        mc.render(camera);
        batch.end();
    }
}
