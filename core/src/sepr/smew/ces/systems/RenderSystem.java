package sepr.smew.ces.systems;

import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.ashley.utils.ImmutableArray;

import com.badlogic.gdx.graphics.OrthographicCamera;

import sepr.smew.ces.components.*;
import sepr.smew.ces.entities.*;

import java.util.*;

/**
 * Takes a SpriteBatch and renders all the components with physics.
 */
public class RenderSystem extends SortedIteratingSystem {
    private static ComponentMapper<TextComponent> txm = ComponentMapper.getFor(TextComponent.class);
    private static ComponentMapper<TextureComponent> tm = ComponentMapper.getFor(TextureComponent.class);
    private static ComponentMapper<OverlayComponent> om = ComponentMapper.getFor(OverlayComponent.class);
    private static ComponentMapper<PhysicsComponent> pm = ComponentMapper.getFor(PhysicsComponent.class);
    private static ComponentMapper<MapLayerComponent> mm = ComponentMapper.getFor(MapLayerComponent.class);
    private static ComponentMapper<RenderPriorityComponent> rpm = ComponentMapper.getFor(RenderPriorityComponent.class);
    private static ComponentMapper<CompositeDrawableComponent> cdm = ComponentMapper.getFor(CompositeDrawableComponent.class);
    
    private final Batch batch;
    private final Batch UIBatch;
    
    private OrthographicCamera camera;
    private OrthographicCamera UICamera;
    
    private static Comparator<Entity> comparator = new Comparator<Entity>(){
        public int compare(Entity a, Entity b){
            return ((Integer) rpm.get(a).priority).compareTo(rpm.get(b).priority);
            
        }
        
    };

    public RenderSystem(int priority, Batch batch, Batch UIBatch, OrthographicCamera camera, OrthographicCamera UICamera) {
        super(Family
            .all(RenderPriorityComponent.class)
            .one(MapLayerComponent.class, TextureComponent.class, TextComponent.class, CompositeDrawableComponent.class)
            .one(MapLayerComponent.class, PhysicsComponent.class, OverlayComponent.class) //NOT AN ERROR
            // If it has a map component, it does not need a phys or overlay one.
            .get(), comparator, priority);
        this.batch = batch;
        this.UIBatch = UIBatch;
        this.camera = camera;
        this.UICamera = UICamera;
    }

    @Override
	public void update(float deltaTime) {
        camera.update();
        UICamera.update();
        batch.setProjectionMatrix(camera.combined);
        UIBatch.setProjectionMatrix(UICamera.combined);
        ImmutableArray<Entity> sortedEntities = getEntities();
        
        batch.begin();
        
        int i = 0;
        int size = sortedEntities.size();
        for (; i < size; ++i) {
            Entity entity = sortedEntities.get(i);
            if (om.has(entity)){
                break;
            }
            processEntity(entity, deltaTime);
        }
        batch.end();
        
        UIBatch.begin();
        for (; i < size; ++i) {
            Entity entity = sortedEntities.get(i);
            processEntity(entity, deltaTime);
        }
        UIBatch.end();
    }
    
    @Override
    public void processEntity(Entity entity, float deltaTime) {
        // TODO(avinashbot): Don't subclass IteratingSystem and render all
        //                   physics components before overlay components.
        
        if (mm.has(entity)) {
            mm.get(entity).render(camera);
        }
        else {
            DrawableComponent dc = tm.has(entity)  ? tm.get(entity) :
                                  (txm.has(entity) ? txm.get(entity) : cdm.get(entity));
            Vector2 position;
            Batch b;
            if (pm.has(entity)){
                 position = pm.get(entity).getBottomLeft();
                 b = batch;
             }
             else {
                 position = om.get(entity).getPosition();
                 b = UIBatch;
             }
            dc.draw(b, position.x, position.y);
            }
    }
}
