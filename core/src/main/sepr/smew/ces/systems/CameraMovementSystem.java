package sepr.smew.ces.systems;

import sepr.smew.ces.components.*;
import sepr.smew.ces.entities.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.ashley.utils.ImmutableArray;

import com.badlogic.gdx.graphics.OrthographicCamera;

import java.lang.Math;

/**
 * Stores and animates the keyframes associated with the spritesheet.
 */
public class CameraMovementSystem extends EntitySystem {
    private ComponentMapper<PhysicsComponent> pm = ComponentMapper.getFor(PhysicsComponent.class);
    private ComponentMapper<SmewMovementComponent> sm = ComponentMapper.getFor(SmewMovementComponent.class);
    private ComponentMapper<CameraComponent> cm = ComponentMapper.getFor(CameraComponent.class);
    private Family smewFamily;
    private Family cameraFamily;
    private CameraEntity cameraEntity;
    private SmewEntity smewEntity;
    private float windowSizeX;
    private float windowSizeY;
    protected Engine engine;

    public CameraMovementSystem(int priority) {
        super(priority);
        smewFamily = Family.all(SmewMovementComponent.class, PhysicsComponent.class).get();
        cameraFamily = Family.all(CameraComponent.class).get();
    }
    
    //@Override
	public void addedToEngine (Engine engine) {
        super.addedToEngine(engine);
        checkEntities();
    }
    private void checkEntities(){
		cameraEntity = (CameraEntity)getEntity(cameraFamily);
		smewEntity = (SmewEntity)getEntity(smewFamily);
	}
    
    private Entity getEntity(Family family){
        ImmutableArray<Entity> array = getEngine().getEntitiesFor(family);
        if (array.size()>0){
            return array.get(0);
        }
        return null;
    }

    @Override
    public void update(float deltaTime) {
        if (cameraEntity != null && smewEntity != null) {
            CameraComponent cameraComponent = cm.get(cameraEntity);
            PhysicsComponent physComponent = pm.get(smewEntity);
            OrthographicCamera camera = cameraComponent.camera;
            
            Vector2 smewPos = physComponent.getCentre();
            Vector2 cameraPos = cameraComponent.getPosition();
            
            int smew_row = (int)Math.floor(smewPos.y / cameraComponent.roomSizeY);
            int smew_col = (int)Math.floor(smewPos.x / cameraComponent.roomSizeX);
            
            int camera_row = cameraComponent.current_room_row;
            int camera_col = cameraComponent.current_room_col;
            
            
            if (smew_row != camera_row || smew_col != camera_col) {
                cameraComponent.centreOnRoom(smew_col, smew_row, 5f);
            }
            cameraComponent.updateAnimation(deltaTime);
            
        }
        else {
            checkEntities();
        }
    }
}
