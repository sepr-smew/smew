package sepr.smew.ces.components;


import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.OrthographicCamera;
import sepr.smew.util.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;


public class MapLayerComponent implements Component {
    public OrthogonalTiledMapRenderer renderer;
    public TiledMapTileLayer mapLayer;
    
    public MapLayerComponent(OrthogonalTiledMapRenderer renderer, TiledMapTileLayer mapLayer){
        this.renderer = renderer;
        this.mapLayer = mapLayer;
    }
    
    public void render(OrthographicCamera camera){
        renderer.setView(camera);
        //renderer.render();
        renderer.renderTileLayer(mapLayer);
        //batch.end();
        //renderer.render();
        //batch.begin();
        
    }
}

