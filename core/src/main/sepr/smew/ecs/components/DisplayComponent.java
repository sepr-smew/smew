package sepr.smew.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Any entity using this has a display representation.
 */
public class DisplayComponent implements Component {
    public Actor actor;

    public DisplayComponent(Actor actor) {
        this.actor = actor;
    }
}
