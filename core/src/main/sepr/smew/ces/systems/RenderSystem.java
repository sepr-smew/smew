package sepr.smew.ces.systems;

import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import sepr.smew.ces.components.*;

/**
 * Takes a SpriteBatch and renders all the components with physics.
 */
public class RenderSystem extends IteratingSystem {
    private ComponentMapper<TextureComponent> tm = ComponentMapper.getFor(TextureComponent.class);
    private ComponentMapper<BoundsComponent>  bm = ComponentMapper.getFor(BoundsComponent.class);
    private ComponentMapper<OverlayComponent> om = ComponentMapper.getFor(OverlayComponent.class);
    private ComponentMapper<PhysicsComponent> pm = ComponentMapper.getFor(PhysicsComponent.class);

    private final Batch batch;

    public RenderSystem(int priority, Batch gameBatch) {
        super(Family
            .all(TextureComponent.class, BoundsComponent.class)
            .one(PhysicsComponent.class, OverlayComponent.class)
            .get(), priority);
        batch = gameBatch;
    }

    @Override
	public void update(float deltaTime) {
        batch.begin();
        super.update(deltaTime);
        batch.end();
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        // TODO(avinashbot): Don't subclass IteratingSystem and render all
        //                   physics components before overlay components.
        TextureComponent tc = tm.get(entity);
        BoundsComponent bc = bm.get(entity);
        if (pm.has(entity)) {
            PhysicsComponent pc = pm.get(entity);
            Vector2 pos = pc.getBottomLeft();
            batch.draw(tc.textureRegion, pos.x, pos.y, bc.width, bc.height);
        }
        else {
            OverlayComponent oc = om.get(entity);
            batch.draw(tc.textureRegion, oc.x, oc.y, bc.width, bc.height);
        }
    }
}
