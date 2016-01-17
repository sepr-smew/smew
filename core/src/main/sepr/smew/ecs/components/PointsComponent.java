package sepr.smew.ecs.components;

import com.badlogic.ashley.core.Component;

/**
 * Stores score points, health points, etc.
 */
public class PointsComponent implements Component {
    public int health = 5;
    public int score  = 0;
}
