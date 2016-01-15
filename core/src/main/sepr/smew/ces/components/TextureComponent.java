package sepr.smew.ces.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
