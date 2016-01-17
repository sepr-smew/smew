package sepr.smew.ces.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

/**
 * Contains the actual texture that will be rendered. Add a texture if your
 * object won't be animated, otherwise use SpritesheetComponent alongside
 * TextureComponent.
 */ 
public class TextureComponent implements DrawableComponent {
    public TextureRegion textureRegion;
    public float width;
    public float height;

    public TextureComponent() {
    }

    public TextureComponent(TextureRegion tr) {
        textureRegion = tr;
    }

    public TextureComponent(Texture t) {
        textureRegion = new TextureRegion(t);
    }

    public TextureComponent(String s) {
        this(new Texture(s));
    }
    
    public TextureComponent(float width, float height){
        this.width=width;
        this.height=height;
    }
    
    public TextureComponent(String tex, float width, float height){
        this(tex);
        this.width=width;
        this.height=height;
    }
    
    public void draw(Batch batch, float x, float y){
        batch.draw(textureRegion, x, y, width, height);
    }
    
    public void draw(Batch batch, Vector2 position){
        draw(batch, position.x, position.y);
    }

}
