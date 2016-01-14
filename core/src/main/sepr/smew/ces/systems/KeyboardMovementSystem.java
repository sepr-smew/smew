package sepr.smew.ces.systems;

import sepr.smew.ces.components.*;

import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.math.Vector2;


import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input;

import java.lang.Integer;

import java.util.ArrayList;
import java.util.List;


/**
 * Takes a SpriteBatch and renders all the components with physics.
 */
public class KeyboardMovementSystem extends IteratingSystem implements InputProcessor {
    private ComponentMapper<KeyboardMovementComponent> kcm = ComponentMapper.getFor(KeyboardMovementComponent.class);
    private ComponentMapper<PhysicsComponent> phym = ComponentMapper.getFor(PhysicsComponent.class);
    
    public static enum Direction {
        UP      (Input.Keys.UP,     new Vector2(0f, 1f)),
        DOWN    (Input.Keys.DOWN,   new Vector2(0f, -1f)),
        RIGHT   (Input.Keys.RIGHT,  new Vector2(1f, 0f)),
        LEFT    (Input.Keys.LEFT,   new Vector2(-1f, 0f)),
        NONE    (-1,                new Vector2(0f, 0f));
        
        private final int key;
        private final Vector2 vector;
        
        Direction(int key, Vector2 vector){
            this.key=key;
            this.vector=vector;
        }
        
        private int key(){return key;}
        Vector2 vector(){return new Vector2(vector);}
        
        static Direction fromKey(int key) {
            switch (key) {
                case Input.Keys.UP:     return UP;
                case Input.Keys.DOWN:   return DOWN;
                case Input.Keys.RIGHT:  return RIGHT;
                case Input.Keys.LEFT:   return LEFT;
                default:                return NONE;
            }
        }

    };
    
    private Vector2 direction=Direction.NONE.vector();
    private List<Integer> pressedKeys = new ArrayList<Integer>();

    public KeyboardMovementSystem() {
        super(Family.all(KeyboardMovementComponent.class, PhysicsComponent.class).get(), 7);
    }
    
    
    @Override
	public void update(float deltaTime) {
        direction=Direction.NONE.vector();
        for (Integer keycode : pressedKeys){
            direction.add(Direction.fromKey(keycode).vector());
        }
        super.update(deltaTime);
    }
    
    @Override
    public void processEntity(Entity entity, float deltaTime) {
        KeyboardMovementComponent kcc = kcm.get(entity);
        PhysicsComponent phyc = phym.get(entity);
        Body body = phyc.body;
        
        Vector2 vector=new Vector2(direction).scl(kcc.magnitude); // target velocity
        vector.sub(body.getLinearVelocity()); // minus the current velocity, giving velocity difference
        vector.scl(body.getMass()); // scaled by the mass of the body to get the required change in momentum
        body.applyLinearImpulse(vector, body.getWorldCenter(), true);
        phyc.update();
        
        
    }

    public boolean keyDown(int keycode) {pressedKeys.add(keycode); return true;}
    public boolean keyTyped(char character) {return false;}
    public boolean keyUp(int keycode) {pressedKeys.remove(new Integer(keycode)); return true;}
    public boolean mouseMoved(int screenX, int screenY) {return false;}
    public boolean scrolled(int amount) {return false;}
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {return false;}
    public boolean touchDragged(int screenX, int screenY, int pointer) {return false;}
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {return false;}

}



