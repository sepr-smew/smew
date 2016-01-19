package sepr.smew.util;

import com.badlogic.gdx.math.Vector2;

public class RoomBound {
    public int id;
    public Vector2 point0;
    public Vector2 point1;
    public Vector2 padded_point0;
    public Vector2 padded_point1;
    public Vector2 padding;
    
    public RoomBound(int id, Vector2 point0, Vector2 point1){
        this(id, point0, point1, new Vector2(64f, 40f));
    }
    
    public RoomBound(int id, Vector2 point0, Vector2 point1, Vector2 padding){
        this.id = id;
        this.point0 = point0;
        this.point1 = point1;
        setPadding(padding);
    }
    
    public void setPadding(Vector2 padding){
        this.padding = padding;
        padded_point0=new Vector2(point0).add(padding);
        padded_point1=new Vector2(point1).sub(padding);
    }
    
    public boolean collide(Vector2 point){
        return point0.x<point.x && point.x<point1.x &&
               point0.y<point.y && point.y<point1.y;
    }
    
    public boolean collidePadded(Vector2 point){
        return padded_point0.x<point.x && point.x<padded_point1.x &&
               padded_point0.y<point.y && point.y<padded_point1.y;
    }
    
    public Vector2 limit(Vector2 point){
        return new Vector2(
            Math.max(point0.x, Math.min(point.x, point1.x)),
            Math.max(point0.y, Math.min(point.y, point1.y))
            );
    }
    
    public Vector2 limitPadded(Vector2 point){
        float x = (padded_point0.x < padded_point1.x) ?
                   Math.max(padded_point0.x, Math.min(point.x, padded_point1.x)) :
                   ((padded_point0.x+padded_point1.x)/2);
        float y = (padded_point0.y < padded_point1.y) ?
                   Math.max(padded_point0.y, Math.min(point.y, padded_point1.y)) :
                   ((padded_point0.y+padded_point1.y)/2);
        return new Vector2(x, y);
    }
}
