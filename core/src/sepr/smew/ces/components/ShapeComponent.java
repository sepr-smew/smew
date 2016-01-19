package sepr.smew.ces.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class ShapeComponent implements DrawableComponent {
    public ShapeRenderer shapeRenderer;
    private Color color;
    public float width;
    public float height;
    
    public ShapeComponent(Color color, float width, float height){
        this.shapeRenderer = new ShapeRenderer();
        this.width=width;
        this.height=height;
        
        setColor(color);
        
        
    }
    
    public ShapeComponent(float width, float height){
        this(new Color(0.5f, 0.5f, 0.5f, 0.5f), width, height);
    }
    
    public void setColor(Color color){
        shapeRenderer.setColor(color);
    }
    
    public void setColor(float r, float g, float b, float a){
        shapeRenderer.setColor(r, g, b, a);
    }
    
    public void draw(Batch batch, float x, float y){
        //if (batch.isDrawing()){
        batch.end();
        
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
        
        batch.begin();
    }
    
    public void draw(Batch batch, Vector2 position){
        draw(batch, position.x, position.y);
    }

}
