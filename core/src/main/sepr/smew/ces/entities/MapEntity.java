package sepr.smew.ces.entities;

import com.badlogic.ashley.core.Entity;



import sepr.smew.ces.components.*;
import sepr.smew.util.*;

/**
 * The one and only Smew! Controled with the keyboard.
 */
public class MapEntity extends Entity {
    public MapEntity(TileMapManager map) {
        this.add(new MapComponent(map));
    }
}

