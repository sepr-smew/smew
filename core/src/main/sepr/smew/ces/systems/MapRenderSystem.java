package sepr.smew.ces.systems;

import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import sepr.smew.ces.components.*;

import sepr.smew.util.*;

/**
 * Takes a SpriteBatch and renders all the components with physics.
 */
public class MapRenderSystem extends IteratingSystem {
    private ComponentMapper<MapComponent> mm = ComponentMapper.getFor(MapComponent.class);


    public MapRenderSystem(int priority) {
        super(Family
            .all(MapComponent.class)
            .get(), priority);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        MapComponent mc = mm.get(entity);
        TileMapManager map = mc.map;
        map.render();
    }
}
