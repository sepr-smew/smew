package sepr.smew.ces.components;


import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.OrthographicCamera;
import sepr.smew.util.*;


public class MapComponent implements Component {
    public TileMapManager map;
    
    public MapComponent(TileMapManager map){
        this.map=map;
    }
    
    public void render(OrthographicCamera camera){
        map.render(camera);
    }
}

