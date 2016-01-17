package sepr.smew.ces.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import sepr.smew.ces.components.EnemyComponent;
import sepr.smew.ces.components.PhysicsComponent;
import sepr.smew.ces.entities.SmewEntity;

/**
 * Moves enemies towards the smew.
 */
public class EnemySystem extends IteratingSystem { // not enemy movement system as this will handle collisions too.
    private ComponentMapper<EnemyComponent>   em = ComponentMapper.getFor(EnemyComponent.class);
    private ComponentMapper<PhysicsComponent> pm = ComponentMapper.getFor(PhysicsComponent.class);

    private SmewEntity smew;

    public EnemySystem(int priority, SmewEntity smew) {
        super(Family.all(EnemyComponent.class, PhysicsComponent.class).get(), priority);
        this.smew = smew;
    }

    public void processEntity(Entity entity, float deltaTime) {
        EnemyComponent   enemy   = em.get(entity);
        PhysicsComponent enemyPhy = pm.get(entity);
        PhysicsComponent smewPhy = pm.get(smew);
        
        Body smewBody = smewPhy.body;
        Body enemyBody = enemyPhy.body;
        
        Vector2 velocity = smewBody.getPosition()
                .sub(enemyBody.getPosition())
                .nor()
                .scl(enemy.speed);
        
        Vector2 impulse = velocity.sub(enemyBody.getLinearVelocity())
                                  .scl(enemyBody.getMass());
                                  
        enemyBody.applyLinearImpulse(impulse, enemyBody.getWorldCenter(), true);
    }
}

