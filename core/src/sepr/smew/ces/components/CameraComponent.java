package sepr.smew.ces.components;

import com.badlogic.ashley.core.Component;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;


public class CameraComponent implements Component {
    public OrthographicCamera camera;
    public boolean transitioning;
    
    private Vector2 interpolation_start;
    private Vector2 interpolation_end;
    private float interpolation_t;
    private float transition_duration;
    private float maxSpeed = 3f;

    public CameraComponent(OrthographicCamera camera) {
        this.camera = camera;
        transitioning = false;
        
    }
    
    public void setPosition(Vector2 position){
        camera.position.set(position.x, position.y, 0);
        camera.update();
    }
    
    public Vector2 getPosition(){
        return new Vector2(camera.position.x, camera.position.y); //camera.position is a Vector3
    }
    
    public void animatePositionTo(Vector2 position, float transition_duration){
        interpolation_start=getPosition();
        interpolation_end=position;
        transitioning = true;
        interpolation_t = 0f;
        this.transition_duration=transition_duration;
    }
    
    public void updateAnimation(float deltaTime){
        interpolation_t+=deltaTime;
        Vector2 velocity = new Vector2(interpolation_end).sub(getPosition()).scl(interpolation_t/transition_duration).clamp(0, maxSpeed);
        setPosition(velocity.add(getPosition()));
    }
    
}
