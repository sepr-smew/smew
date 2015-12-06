package com.sepr.smew.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import com.sepr.smew.Animator;


/**
 * Provides the ability to map between the input keycodes and a correspondind direction vector.
 */
enum Direction {
    UP      (Input.Keys.UP,     new Vector2(0f, 1f)),
    DOWN    (Input.Keys.DOWN,   new Vector2(0f, -1f)),
    RIGHT   (Input.Keys.RIGHT,  new Vector2(1f, 0f)),
    LEFT    (Input.Keys.LEFT,   new Vector2(-1f, 0f)),
    NONE    (-1,                new Vector2(0f, 0f));
    
    
    float VELOCITY = 100f;
    
    private final int key;
    private final Vector2 vector;
    
    Direction(int key, Vector2 vector){
        this.key=key;
        this.vector=vector.scl(VELOCITY);
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

/**
 * Represents the Smew. Is controlled by the keyboard.
 */
public class SmewObject extends PhysicalGameObject {
    private Vector2 velocity=Direction.NONE.vector();
    private static final Texture image = new Texture("smew.jpg");
    
    public SmewObject(float x, float y) {
        super(new Animator(image, 1, 12), x, y);
        
        addListener(new InputListener(){ // Constructing an anonymous inner class
                    public boolean keyDown(InputEvent event, int keycode) { // with a method called on keys being pressed.
                        velocity.add(Direction.fromKey(keycode).vector()); // Adds the corresponding vector to the target velocity.
                        return true; // returning true means scene2d will consider the input handled and stop propagating it (confirm?).
                    }
                    public boolean keyUp(InputEvent event, int keycode) {
                        velocity.sub(Direction.fromKey(keycode).vector());
                        return true;
                    }
                    
            });
    }
    
    public SmewObject() {
        this(0, 0); // I hard coded some numbers in two places, so shoot me.
    }
    
    @Override
    public void act(float delta){
        /**
         * Calculates the impulse required to make the Smew travel with the target velocity, and applies it.
         */
        Vector2 vector = new Vector2(velocity); // target velocity
        vector.sub(body.getLinearVelocity()); // minus the current velocity, giving velocity difference
        vector.scl(body.getMass()); // scaled by the mass of the body to get the required change in momentum
        body.applyLinearImpulse(vector, body.getWorldCenter(), true);
        super.act(delta);
    }

}
