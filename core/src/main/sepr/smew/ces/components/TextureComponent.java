package sepr.smew.ces.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Contains the actual texture that will be rendered. Add a texture if your
 * object won't be animated, otherwise use SpritesheetComponent alongside
 * TextureComponent.
 */ 
public class TextureComponent implements Component {
    public TextureRegion textureRegion;

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

}
