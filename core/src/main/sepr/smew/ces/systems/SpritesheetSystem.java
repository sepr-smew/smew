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
public class SpritesheetSystem extends IteratingSystem {
    private ComponentMapper<TextureComponent> tm = ComponentMapper.getFor(TextureComponent.class);
    private ComponentMapper<SpritesheetComponent> sm = ComponentMapper.getFor(SpritesheetComponent.class);
    private ComponentMapper<DirectionalSpritesheetComponent> dsm = ComponentMapper.getFor(DirectionalSpritesheetComponent.class);

    public SpritesheetSystem(int priority) {
        super(Family.all(TextureComponent.class).one(DirectionalSpritesheetComponent.class, SpritesheetComponent.class).get(), priority);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        TextureComponent tc = tm.get(entity);
        SpritesheetComponentInterface sc;
        if (sm.has(entity)){
            sc = (SpritesheetComponentInterface)sm.get(entity);
        }
        else {  // dsm.has(entity)
            sc = (SpritesheetComponentInterface)dsm.get(entity);
        }
    
        sc.advance(deltaTime);
        tc.textureRegion = sc.currentFrame();
    
    }
}
