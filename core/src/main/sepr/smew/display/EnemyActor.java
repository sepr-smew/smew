package sepr.smew.display;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import sepr.smew.ecs.components.PhysicsComponent;
import sepr.smew.ecs.entities.EnemyEntity;

/**
 * Spits out a baddie on to the screen.
 */
public class EnemyActor extends Actor {
    private EnemyEntity entity;
    private ComponentMapper<PhysicsComponent> pm = ComponentMapper.getFor(PhysicsComponent.class);
    private Texture tex;

    public EnemyActor(EnemyEntity entity) {
        this.entity = entity;
        this.tex = new Texture("Sprites/Goose/Goose_sprite.png");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        PhysicsComponent pc = pm.get(entity);
        Vector2 pos = pc.drawPos();
        batch.draw(tex, pos.x, pos.y, pc.width, pc.height);
    }
}