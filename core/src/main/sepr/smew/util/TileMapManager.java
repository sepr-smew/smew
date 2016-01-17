package sepr.smew.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Let me tell you this in advance. Tile map physics is a concept brewed in
 * hell's deepest crevices. You have James to thank for this class.
 */
public class TileMapManager {
    private TiledMap map;
    private TiledMapTileLayer mapLayer;
    private MapLayer collisionLayer;
    private OrthogonalTiledMapRenderer renderer;
    private Batch batch;
    private OrthographicCamera camera;
    private float scale = 0.25f;

    public TileMapManager(String filename, Batch batch, OrthographicCamera camera) {
        this.batch     = batch;
        this.camera    = camera;
        map            = new TmxMapLoader().load(filename);
        mapLayer       = (TiledMapTileLayer) map.getLayers().get(0);
        collisionLayer = map.getLayers().get("collision");
        renderer       = new OrthogonalTiledMapRenderer(map, scale, batch);
    }

    public void generateTileCollision(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type    = BodyDef.BodyType.StaticBody;
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape    = new PolygonShape();

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

        shape.dispose();
    }

    public void render() {
        renderer.setView(camera);

        batch.begin();
        renderer.renderTileLayer(mapLayer);
        batch.end();
    }
}
