package sepr.smew.ces.systems;

import sepr.smew.ces.components.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


/**
 * Takes a SpriteBatch and renders all the components with physics.
 */
public class PhysicsRenderingSystem extends IteratingSystem {
    private ComponentMapper<PhysicsComponent> pm = ComponentMapper.getFor(PhysicsComponent.class);
    private ComponentMapper<TextureComponent> tm = ComponentMapper.getFor(TextureComponent.class);

    private final SpriteBatch batch;

    public PhysicsRenderingSystem(SpriteBatch gameBatch) {
        // Get all entities with ONE PhysicsComponent and ONE TextureComponent only.
        super(Family.one(PhysicsComponent.class, TextureComponent.class).get());
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
        Vector2 pos = pm.get(entity).body.getPosition();
        Texture tex = tm.get(entity).texture;
        batch.draw(tex, pos.x, pos.y);
    }
}
