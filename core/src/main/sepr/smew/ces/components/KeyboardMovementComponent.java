package sepr.smew.ces.components;

import com.badlogic.ashley.core.Component;


public class KeyboardMovementComponent implements Component {
    public float magnitude;
    public KeyboardMovementComponent(float magnitude){
        this.magnitude=magnitude;
    }
    public KeyboardMovementComponent(){
        this(10f);
    }
}
