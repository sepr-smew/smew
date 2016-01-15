package sepr.smew.ces.components;

import com.badlogic.ashley.core.Component;

public class SmewMovementComponent implements Component {
    public float magnitude;

    public SmewMovementComponent(float magnitude){
        this.magnitude = magnitude;
    }
}
