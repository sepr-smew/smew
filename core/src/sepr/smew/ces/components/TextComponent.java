package sepr.smew.ces.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.freetype.*;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.*;


/**
 * Contains the actual texture that will be rendered. Add a texture if your
 * object won't be animated, otherwise use SpritesheetComponent alongside
 * TextureComponent.
 */ 
public class TextComponent implements DrawableComponent {
    private BitmapFont font;
    public String text;
    private float scale = 0.1f;
    
    public TextComponent(){        
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Lato-Regular.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 40;
        font = generator.generateFont(parameter);
        generator.dispose();
        font.getData().setScale(scale, scale);
        this.text="";
    }
    public TextComponent(String text){
        this.text=text;
    }
    
    public void draw(Batch batch, float x, float y){
        font.draw(batch, text, x, y);
    }
    
    public void draw(Batch batch, Vector2 position){
        draw(batch, position.x, position.y);
    }

}
