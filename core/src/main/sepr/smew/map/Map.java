package sepr.smew.map;

import com.badlogic.gdx.maps.*;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import sepr.smew.ces.entities.*;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Map {
    private OrthogonalTiledMapRenderer renderer;
    private TiledMapTileLayer layer;
    private TiledMap map;
    private Batch batch;
    private OrthographicCamera camera;
    
    public Map(String filename, Batch batch, OrthographicCamera camera){
        this.batch = batch;
        map = new TmxMapLoader().load(filename);
        layer = (TiledMapTileLayer)map.getLayers().get(0);
        renderer = new OrthogonalTiledMapRenderer(map, 0.25f, batch);
        this.camera = camera;
    }
    
    public MapEntity entity() {
        return new MapEntity(this);
    }
    public void render(){
        renderer.setView(camera);
        //batch.begin();
        //renderer.renderTileLayer(layer);
        renderer.render();
        //batch.end();
    }
    
}

