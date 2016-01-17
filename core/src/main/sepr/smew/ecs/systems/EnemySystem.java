package sepr.smew.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import sepr.smew.ecs.components.EnemyComponent;
import sepr.smew.ecs.components.PhysicsComponent;
import sepr.smew.ecs.entities.SmewEntity;

/**
 * Moves enemies towards the smew.
 */
public class EnemySystem extends IteratingSystem {
    private ComponentMapper<EnemyComponent>   em = ComponentMapper.getFor(EnemyComponent.class);
    private ComponentMapper<PhysicsComponent> pm = ComponentMapper.getFor(PhysicsComponent.class);

    private SmewEntity smew;

    public EnemySystem(int priority, SmewEntity smew) {
        super(Family.all(EnemyComponent.class, PhysicsComponent.class).get(), priority);
        this.smew = smew;
    }

    public void processEntity(Entity entity, float deltaTime) {
        EnemyComponent   enemy   = em.get(entity);
        PhysicsComponent physics = pm.get(entity);
        PhysicsComponent smewPhy = pm.get(smew);

        Vector2 dir = smewPhy.body.getPosition().sub(physics.body.getPosition()).nor().scl(enemy.speed).scl(deltaTime).sub(physics.body.getLinearVelocity());
        physics.body.applyLinearImpulse(dir, physics.body.getWorldCenter(), true);
    }
}
