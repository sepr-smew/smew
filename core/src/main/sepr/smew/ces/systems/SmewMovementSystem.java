package sepr.smew.ces.systems;

import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input;

import java.lang.Integer;
import java.util.ArrayList;

import sepr.smew.ces.components.*;

/**
 * Deals with detecting keyboard input and moving the smew accordingly.
 */
public class SmewMovementSystem extends IteratingSystem {
    private ComponentMapper<SmewMovementComponent> kcm = ComponentMapper.getFor(SmewMovementComponent.class);
    private ComponentMapper<PhysicsComponent> phym = ComponentMapper.getFor(PhysicsComponent.class);

    private Vector2 direction = Vector2.Zero.cpy();
    private ArrayList<Integer> pressedKeys = new ArrayList<Integer>();

    public SmewMovementSystem() {
        super(Family.all(SmewMovementComponent.class, PhysicsComponent.class).get(), 7);
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

    /**
     * Get a vector force direction from a keyboard key. This looks ugly, but
     * is honestly the best way of storing them. If this does get too long,
     * consider a hashmap.
     */
    private Vector2 directionFromKey(int key) {
        switch (key) {
            case Input.Keys.UP:
            case Input.Keys.W:
                return new Vector2(0, 1);
            case Input.Keys.DOWN:
            case Input.Keys.S:
                return new Vector2(0, -1);
            case Input.Keys.RIGHT:
            case Input.Keys.D:
                return new Vector2(1, 0);
            case Input.Keys.LEFT:
            case Input.Keys.A:
                return new Vector2(-1, 0);
            default:
                return new Vector2(0, 0);
        }
    }

    @Override
	public void update(float deltaTime) {
        direction = Vector2.Zero.cpy();
        for (Integer keycode : pressedKeys){
            direction.add(directionFromKey(keycode));
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
        vector.scl(body.getMass()); // scaled by the mass of the body to get the required change in momentum
        body.applyLinearImpulse(vector, body.getWorldCenter(), true);
    }
}
