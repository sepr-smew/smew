package sepr.smew.ces.components;

import sepr.smew.map.*;
import com.badlogic.ashley.core.Component;


public class MapComponent implements Component {
    public Map map;
    
    public MapComponent(Map map){
        this.map=map;
    }
}

