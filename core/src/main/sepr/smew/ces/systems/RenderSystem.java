package sepr.smew.ces.systems;

import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.graphics.OrthographicCamera;

import sepr.smew.ces.components.*;
import sepr.smew.ces.entities.*;

/**
 * Takes a SpriteBatch and renders all the components with physics.
 */
public class RenderSystem extends IteratingSystem {
    private ComponentMapper<TextComponent> txm = ComponentMapper.getFor(TextComponent.class);
    private ComponentMapper<TextureComponent> tm = ComponentMapper.getFor(TextureComponent.class);
    private ComponentMapper<OverlayComponent> om = ComponentMapper.getFor(OverlayComponent.class);
    private ComponentMapper<PhysicsComponent> pm = ComponentMapper.getFor(PhysicsComponent.class);
    private ComponentMapper<MapComponent> mm = ComponentMapper.getFor(MapComponent.class);
    
    private final Batch batch;
    private final Batch UIBatch;
    
    private OrthographicCamera camera;
    private OrthographicCamera UICamera;

    public RenderSystem(int priority, Batch batch, Batch UIBatch, OrthographicCamera camera, OrthographicCamera UIcamera) {
        super(Family
            .one(MapComponent.class, TextureComponent.class, TextComponent.class)
            .one(MapComponent.class, PhysicsComponent.class, OverlayComponent.class) //NOT AN ERROR
            // If it has a map component, it does not need a phys or overlay one.
            .get(), priority);
        this.batch = batch;
        this.UIBatch = UIBatch;
        this.camera = camera;
        this.UICamera = UICamera;
    }

    @Override
	public void update(float deltaTime) {
        batch.begin();
        UIBatch.begin();
        super.update(deltaTime);
        batch.end();
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
            DrawableComponent dc = tm.has(entity) ? tm.get(entity) : txm.get(entity);
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
