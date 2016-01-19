package sepr.smew.util;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.utils.Array;

import sepr.smew.ces.entities.*;

import java.util.ArrayList;
import java.lang.Math;

/**
 * Let me tell you this in advance. Tile map physics is concept brewed in
 * hell's deepest crevices. You have James to thank for this class.
 */
public class TileMapManager {
    private TiledMap map;
    private TiledMapTileLayer mapLayer;
    private MapLayer collisionLayer;
    private MapLayer roomsLayer;
    private MapLayer enemiesLayer;
    //private MapLayer ceilingLayer;
    private OrthogonalTiledMapRenderer renderer;
    private Batch batch;
    private float scale = 1/6f;
    public ArrayList<RoomBound> roomBounds = new ArrayList<RoomBound>();
    MapLayers layers;

    public TileMapManager(String filename, Batch batch) {
        // Godly class yayayayayayyayaayayyy
        this.batch     = batch;
        map            = new TmxMapLoader().load(filename);
        layers         = map.getLayers();
        collisionLayer = layers.get("collision");
        roomsLayer     = layers.get("rooms");
        enemiesLayer     = layers.get("enemy_spawn");
        //ceilingLayer     = layers.get("ceiling");
        renderer       = new OrthogonalTiledMapRenderer(map, scale, batch);
        
        if (roomsLayer != null){
            for (MapObject object : roomsLayer.getObjects()) {
                MapProperties props = object.getProperties();
                
                float x      = scale * props.get("x", Float.class);
                float y      = scale * props.get("y", Float.class);
                float width  = scale * props.get("width", Float.class);
                float height = scale * props.get("height", Float.class);
                
                int id       =        props.get("id", Integer.class);
                
                RoomBound rb = new RoomBound(id, new Vector2(x, y), new Vector2(x+width, y+height));
                roomBounds.add(rb);
            }
        }
        
    }
    
    public ArrayList<MapLayerEntity> getEntities(){
            ArrayList<MapLayerEntity> entities = new ArrayList<MapLayerEntity>();
            
            for (TiledMapTileLayer l : layers.getByType(TiledMapTileLayer.class)){
                    entities.add(new MapLayerEntity(renderer, l,
                    Integer.parseInt(l.getProperties().get("renderPriority", "0", String.class))
                    ));
            }
            return entities;
    }
    
    public Array<EnemyEntity> generateEnemies(World world){
        Array<EnemyEntity> enemies = new Array<EnemyEntity>();
        
        if (enemiesLayer != null){
            for (MapObject object : enemiesLayer.getObjects()) {
                MapProperties props = object.getProperties();
                
                float x      = scale * props.get("x", Float.class);
                float y      = scale * props.get("y", Float.class);
                float width  = scale * props.get("width", Float.class);
                float height = scale * props.get("height", Float.class);
                
                int enemyCount = new Integer((String)props.get("enemyCount"));
                
                
                for (int i = 0; i<enemyCount ; i++){
                    enemies.add(new EnemyEntity(world, x+(float)Math.random()*width, y+(float)Math.random()*height));
                }
            }
        }
        return enemies;
    }

    public void generateTileCollision(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type    = BodyDef.BodyType.StaticBody;
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape    = new PolygonShape();
        
        if (collisionLayer != null) {
            
            for (MapObject object : collisionLayer.getObjects()) {
                // Get object properties
                MapProperties props = object.getProperties();
                float x      = scale * props.get("x", Float.class);
                float y      = scale * props.get("y", Float.class);
                float width  = scale * props.get("width", Float.class);
                float height = scale * props.get("height", Float.class);
                // Assign the properties to the body.
                bodyDef.position.set(x + (width / 2), y + (height / 2));
                shape.setAsBox(width / 2, height / 2);
                fixtureDef.shape = shape;
                Body body = world.createBody(bodyDef);
                body.createFixture(fixtureDef);
            }
        }

        shape.dispose();
    }
}
