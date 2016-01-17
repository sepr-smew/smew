package sepr.smew.ecs.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.World;
import sepr.smew.display.EnemyActor;
import sepr.smew.ecs.components.DisplayComponent;
import sepr.smew.ecs.components.EnemyComponent;
import sepr.smew.ecs.components.PhysicsComponent;

/**
 * A baddie that follows the smew around.
 */
public class EnemyEntity extends Entity {
    public EnemyEntity(World world, int x, int y) {
        this.add(PhysicsComponent.dynamicBox(world, x, y, 50, 50));
        this.add(new EnemyComponent());
        this.add(new DisplayComponent(new EnemyActor(this)));
    }
}
