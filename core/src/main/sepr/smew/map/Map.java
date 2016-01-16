package sepr.smew.map;

import com.badlogic.gdx.maps.*;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import sepr.smew.ces.entities.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Body;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

import sepr.smew.ces.entities.*;

import com.badlogic.gdx.physics.box2d.World;


import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.math.Matrix4;


import java.util.ArrayList;

public class Map {
    private OrthogonalTiledMapRenderer renderer;
    private TiledMapTileLayer layer;
    private MapLayer object_layer;
    private TiledMap map;
    private Batch batch;
    private OrthographicCamera camera;
    private World world;
    
    private float tileWidth;
    private float tileHeight;
    private float scale= 0.25f;
    
    Box2DDebugRenderer debugRenderer;
    Matrix4 debugMatrix;
    
    public Map(String filename, World world, Batch batch, OrthographicCamera camera){
        this.batch = batch;
        this.world = world;
        map = new TmxMapLoader().load(filename);
        layer = (TiledMapTileLayer)map.getLayers().get(0);
        
        makeObstacleEntities();
        
        tileWidth = layer.getTileWidth();
        tileHeight = layer.getTileHeight();
        
        
        renderer = new OrthogonalTiledMapRenderer(map, scale, batch);
        this.camera = camera;
        
        int columns = layer.getWidth();
        int rows = layer.getHeight();
        for (int r = 0; r<rows; r++){
            for(int c = 0; c<columns; c++) {
                Cell cell = layer.getCell(c, r);
                if (cell.getTile().getProperties().get("wall")!=null){
                    
                }
                
            }
        }
        
        
        
        debugRenderer=new Box2DDebugRenderer();
        
        
    }
    
    
    private void makeObstacleEntities(){
        object_layer = (MapLayer)map.getLayers().get("collision");
        MapObjects objects = object_layer.getObjects();
        java.util.Iterator<MapObject> object_iterator = objects.iterator();
        while (object_iterator.hasNext()){
            makeObstacleEntity(object_iterator.next());
        }
    }
    
    private void makeObstacleEntity(MapObject object){
        MapProperties props = object.getProperties();
        float x = scale*props.get("x", Float.class);
        float y = scale*props.get("y", Float.class);
        float width = scale*props.get("width", Float.class);
        float height = scale*props.get("height", Float.class);
        
        new ObstacleEntity(world, x, y, width, height);
    }
    
    public MapEntity entity() {
        return new MapEntity(this);
    }
    public void render(){
        renderer.setView(camera);
        batch.begin();
        renderer.renderTileLayer(layer);
        //renderer.render();
        
        batch.end();
        debugMatrix=new Matrix4(camera.combined);
        debugRenderer.render(world, debugMatrix);
    }
    
}

