package sepr.smew.ces.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.utils.Array;

public class CompositeDrawableComponent implements DrawableComponent {
    private class SubDrawable {
        public DrawableComponent component;
        public Vector2 offset;
        public SubDrawable(DrawableComponent component, Vector2 offset){
            this.component = component;
            this.offset = offset;
            
        }
    }
    
    private Array<SubDrawable> children;
    
    public CompositeDrawableComponent() {
        children = new Array<SubDrawable>();
    }
    
    public void add(DrawableComponent component, Vector2 offset){
        children.add(new SubDrawable(component, offset));
    }
    
    public void add(DrawableComponent component, float x, float y){
        this.add(component, new Vector2(x, y));
    }
    
    public void draw(Batch batch, Vector2 position){
        for (SubDrawable sub : children){
            //System.out.println("HIII");
            sub.component.draw(batch, new Vector2(position).add(sub.offset));
        }
    }
    
    public void draw(Batch batch, float x, float y){
        draw(batch, new Vector2(x, y));
    }

}
