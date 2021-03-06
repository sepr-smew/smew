package sepr.smew.ces.components;

import com.badlogic.ashley.core.Component;

/**
 * Contains information about the specific movement details and constraints of
 * our titular hero.
 */
public class SmewMovementComponent implements Component {
    /**
     * The velocity at which the smew must travel when a key is pressed.
     */
    public float magnitude;

    public SmewMovementComponent(float magnitude) {
        this.magnitude = magnitude;
    }
}
