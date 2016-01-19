package sepr.smew.ces.components;

import com.badlogic.ashley.core.Component;

/**
 * Contains information about the specific movement details and constraints of
 * our titular hero.
 */
public class RenderPriorityComponent implements Component {
    public int priority;

    public RenderPriorityComponent(int priority) {
        this.priority = priority;
    }
}
