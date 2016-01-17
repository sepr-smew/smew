package sepr.smew.ecs.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import sepr.smew.ecs.components.PhysicsComponent;
import sepr.smew.ecs.components.SmewMovementComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Deals with detecting keyboard input and moving the smew accordingly.
 */
public class SmewMovementSystem extends IteratingSystem {
    private ComponentMapper<SmewMovementComponent> kcm = ComponentMapper.getFor(SmewMovementComponent.class);
    private ComponentMapper<PhysicsComponent> phym = ComponentMapper.getFor(PhysicsComponent.class);

    private final Vector2 UP    = new Vector2(0, 1);
    private final Vector2 DOWN  = new Vector2(0, -1);
    private final Vector2 LEFT  = new Vector2(-1, 0);
    private final Vector2 RIGHT = new Vector2(1, 0);
    private final Map<Integer, Vector2> keyMap = new HashMap<Integer, Vector2>();

    private Vector2 direction = Vector2.Zero.cpy();
    private ArrayList<Integer> pressedKeys = new ArrayList<Integer>();

    public SmewMovementSystem(int priority) {
        super(Family.all(SmewMovementComponent.class, PhysicsComponent.class).get(), priority);

        keyMap.put(Input.Keys.UP, UP);
        keyMap.put(Input.Keys.W, UP);
        keyMap.put(Input.Keys.DOWN, DOWN);
        keyMap.put(Input.Keys.S, DOWN);
        keyMap.put(Input.Keys.LEFT, LEFT);
        keyMap.put(Input.Keys.A, LEFT);
        keyMap.put(Input.Keys.RIGHT, RIGHT);
        keyMap.put(Input.Keys.D, RIGHT);
    }

    public final InputAdapter inputProcessor = new InputAdapter() {
        @Override
        public boolean keyDown(int keycode) {
            pressedKeys.add(keycode);
            return true;
        }

        @Override
        public boolean keyUp(int keycode) {
            pressedKeys.remove(new Integer(keycode));
            return true;
        }
    };

    @Override
    public void update(float deltaTime) {
        direction = Vector2.Zero.cpy();
        for (Integer keycode : pressedKeys) {
            direction.add(keyMap.getOrDefault(keycode, Vector2.Zero));
        }
        super.update(deltaTime);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        SmewMovementComponent kcc = kcm.get(entity);
        PhysicsComponent phyc = phym.get(entity);
        Body body = phyc.body;

        Vector2 vector = new Vector2(direction).scl(kcc.magnitude); // target velocity
        vector.sub(body.getLinearVelocity()); // minus the current velocity, giving velocity difference
        // vector.scl(body.getMass()); // scale by the mass of the body (not used because everything weighs the same)
        body.applyLinearImpulse(vector, body.getWorldCenter(), true);
    }
}
