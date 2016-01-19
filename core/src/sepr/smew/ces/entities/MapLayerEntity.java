package sepr.smew.ces.entities;

import com.badlogic.ashley.core.Entity;


import sepr.smew.ces.components.*;
import sepr.smew.util.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

/**
 * The one and only Smew! Controlled with the keyboard.
 */
public class MapLayerEntity extends Entity {
    public MapLayerEntity(OrthogonalTiledMapRenderer renderer, TiledMapTileLayer mapLayer, int renderPriority) {
        this.add(new MapLayerComponent(renderer, mapLayer));
        this.add(new RenderPriorityComponent(renderPriority));
    }
}

