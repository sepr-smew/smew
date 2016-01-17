package sepr.smew.ces.components;

import com.badlogic.ashley.core.Component;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class CameraComponent implements Component {
    public OrthographicCamera camera;
    public int current_room_row;
    public int current_room_col;
    public boolean transitioning;
    public float roomSizeX;
    public float roomSizeY;
    
    private Vector2 interpolation_start;
    private Vector2 interpolation_end;
    private float interpolation_t;
    private float transition_duration;

    public CameraComponent(OrthographicCamera camera, float roomSizeX, float roomSizeY) {
        this.camera = camera;
        transitioning = false;
        current_room_row = 0;
        current_room_col = 0;
        this.roomSizeX=roomSizeX;
        this.roomSizeY=roomSizeY;
        setPosition(getCentreOfRoom(0, 0));
        
    }
    
    public void setPosition(Vector2 position){
        camera.position.set(position.x, position.y, 0);
        camera.update();
    }
    
    public Vector2 getPosition(){
        return new Vector2(camera.position.x, camera.position.y); //camera.position is a Vector3
    }
    
    private Vector2 getCentreOfRoom(int column, int row){
        return new Vector2(
            (column+0.5f)*roomSizeX,
            (row+0.5f)*roomSizeY
            );
        }
    
    public void centreOnRoom(int column, int row){
        centreOnRoom(column, row, 2f);
    }
    
    public void centreOnRoom(int column, int row, float transition_duration){
        current_room_row = row;
        current_room_col = column;
        if (transition_duration==0f){
            setPosition(getCentreOfRoom(column, row));
        }
        else {
            animatePositionTo(getCentreOfRoom(column, row), transition_duration);
            
        }
    }
    
    private void animatePositionTo(Vector2 position, float transition_duration){
        interpolation_start=getPosition();
        interpolation_end=position;
        transitioning = true;
        interpolation_t = 0f;
        this.transition_duration=transition_duration;
    }
    
    public void updateAnimation(float deltaTime){
        if (transitioning) {
                Vector2 target;
                if (interpolation_t<transition_duration){
                    target = interpolation_start.lerp(
                        interpolation_end,
                        interpolation_t/transition_duration
                        );
                    interpolation_t+=deltaTime;
                }
                else {
                    target = interpolation_end;
                    transitioning = false;
                }
                
                setPosition(target);
            }
        
    }
    
}
