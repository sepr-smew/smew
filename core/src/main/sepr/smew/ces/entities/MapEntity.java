package sepr.smew.ces.entities;

import com.badlogic.ashley.core.Entity;


import sepr.smew.map.*;
import sepr.smew.ces.components.*;

/**
 * The one and only Smew! Controled with the keyboard.
 */
public class MapEntity extends Entity {
    public MapEntity(Map map) {
        this.add(new MapComponent(map));
    }
}

