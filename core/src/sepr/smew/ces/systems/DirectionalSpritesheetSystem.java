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
 * Stores and animates the keyframes associated with the spritesheet.
 */
public class DirectionalSpritesheetSystem extends IteratingSystem {
    private static ComponentMapper<PhysicsComponent> pm = ComponentMapper.getFor(PhysicsComponent.class);
    private static ComponentMapper<DirectionalSpritesheetComponent> dsm = ComponentMapper.getFor(DirectionalSpritesheetComponent.class);
    private final Vector2 reference = new Vector2(0, -1);
    private final Vector2 bias = new Vector2(1.5f, 1);

    public DirectionalSpritesheetSystem(int priority) {
        super(Family.all(PhysicsComponent.class, DirectionalSpritesheetComponent.class).get(), priority);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        PhysicsComponent pc = pm.get(entity);
        DirectionalSpritesheetComponent dsc = dsm.get(entity);
    
        dsc.angle =  pc.body.getLinearVelocity().scl(bias).angle(reference);
    
    }
}
