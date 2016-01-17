package sepr.smew.ecs.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import sepr.smew.ecs.components.DisplayComponent;

import java.util.HashMap;
import java.util.Map;

/**
 * Synchronizes the display and the internal model.
 */
public class DisplaySystem extends EntitySystem implements EntityListener {
    private ComponentMapper<DisplayComponent> dm = ComponentMapper.getFor(DisplayComponent.class);

    private final Stage stage;
    private final Map<Entity, Actor> actorMap;

    public DisplaySystem(int priority, Viewport viewport, Batch batch) {
        super(priority);
        stage = new Stage(viewport, batch);
        actorMap = new HashMap<Entity, Actor>();
    }

    @Override
    public void addedToEngine(Engine engine) {
        Family family = Family.all(DisplayComponent.class).get();
        engine.addEntityListener(family, this);
    }

    @Override
    public void entityAdded(Entity entity) {
        // We asked the engine for only display entities, so we're safe here.
        DisplayComponent display = dm.get(entity);
        actorMap.put(entity, display.actor);
        stage.addActor(display.actor);
    }

    @Override
    public void entityRemoved(Entity entity) {
        actorMap.remove(entity);
    }

    @Override
    public void update(float deltaTime) {
        stage.act(deltaTime);
        stage.draw();
    }
}
