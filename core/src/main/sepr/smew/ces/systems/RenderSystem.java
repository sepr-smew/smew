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
public class RenderSystem extends IteratingSystem {
    private ComponentMapper<TextureComponent> tm = ComponentMapper.getFor(TextureComponent.class);
    private ComponentMapper<PositionComponent> posm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<PhysicsComponent> phym = ComponentMapper.getFor(PhysicsComponent.class);

    private final SpriteBatch batch;

    public RenderSystem(SpriteBatch gameBatch) {
        super(Family.all(TextureComponent.class).one(PhysicsComponent.class, PositionComponent.class).get(), 10);
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
        TextureComponent tc = tm.get(entity);
        if (phym.has(entity)) {
            PhysicsComponent phyc = phym.get(entity);
            batch.draw(tc.textureRegion, phyc.x, phyc.y, phyc.width, phyc.height);
        }
        else {
            PositionComponent posc = posm.get(entity);
            batch.draw(tc.textureRegion, posc.x, posc.y, posc.width, posc.height);
        }
    }
}

